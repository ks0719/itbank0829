package spring.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.db.board.Board;
import spring.db.board.BoardDao;
import spring.db.board.Comment;
import spring.db.board.CommentDao;
import spring.db.member.MemberDao;

@Controller
@RequestMapping("/board")
public class BoardController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private MemberDao memberDao;
	
	private String getNick(HttpServletRequest req) throws Exception {
		Cookie[] c=req.getCookies();
		if(c != null){
	        for(int i=0; i < c.length; i++){
	            Cookie ck = c[i] ;
	            // 저장된 쿠키 이름을 가져온다
	            String cName = ck.getName();
	            // 쿠키값을 가져온다
	            String cValue =  URLDecoder.decode(ck.getValue(),"utf-8");
//	            log.debug("쿠키값  :"+cValue);
	            if(cName.equals("mynick")) {
	            	return cValue;
	            }
	        }
		}
		return "";
	}
	
	private int getMemberNo(String nick) {
		if (nick == "") return 0;
//		log.debug("nick : " + nick);
		return memberDao.memberNo(nick);
	}
	
	private boolean isWriter(HttpServletRequest req) throws Exception {
		String nick = getNick(req);
		if (nick == "") return false;
		
		int memberNo = getMemberNo(nick);
		int no = Integer.parseInt(req.getParameter("no"));
		
		boolean result = boardDao.isWriter(no, memberNo);
		return result;
	}
	
	private boolean isCommentWriter(HttpServletRequest req) throws Exception {
		String nick = getNick(req);
		if (nick == "") return false;
		
		int memberNo = getMemberNo(nick);
		int no = Integer.parseInt(req.getParameter("commentNo"));
		
		boolean result = commentDao.isWriter(no, memberNo);
		return result;
	}
	
	private void deleteFile(String savePath, String filename) {
		File f = new File(savePath);
		     
		String fileList[] = f.list(new FilenameFilter() {
		 
		    @Override
		    public boolean accept(File dir, String name) {
		        return name.startsWith(filename);
		    }
		 
		});
		
	    File file;
		for (int i = 0; i < fileList.length; i++) {
			file = new File(savePath, fileList[i]);
			file.delete();
		}
	}
	
	@RequestMapping("/{path}")
	public String board(@PathVariable String path, HttpServletRequest request, Model m) {	
		String search = request.getParameter("search");
		String key = request.getParameter("key");
		
//		log.debug("path : " + path);
		
		int pageNo;
		try {
			pageNo = Integer.parseInt(request.getParameter("page"));
		} catch(Exception e) {
			pageNo = 1;
		}
		if (pageNo <= 0 ) pageNo = 1;
//		log.debug("pageNo : " + pageNo);

		int listCount = boardDao.count(path, search, key);
//		log.debug(String.valueOf(listCount));
		
		int boardSize = 10;
		int start = boardSize * pageNo - (boardSize - 1);
		int end = start + boardSize -1;
		if (end > listCount) end = listCount;
		
//		log.debug(start + ", " + end);
		List<Board> list = boardDao.list(path, search, key, start, end);
		
		int blockSize = 10;
		int blockTotal = (listCount + boardSize - 1) / boardSize;
		int startBlock = (pageNo - 1) / blockSize * blockSize + 1;
		int endBlock = startBlock + blockSize - 1;
		if (endBlock > blockTotal) endBlock = blockTotal;
		
		String url = path + "?";
		if (search != null && key != null) {
			url += "search=" + search + "&key=" + key + "&";
			m.addAttribute("search", search);
			m.addAttribute("key", key);
		}
		
		m.addAttribute("list", list);
		m.addAttribute("page", pageNo);
		m.addAttribute("startBlock", startBlock);
		m.addAttribute("endBlock", endBlock);
		m.addAttribute("blockTotal", blockTotal);
		m.addAttribute("url", url);
		m.addAttribute("path", path);
		return "board/list";
	}
	
	@RequestMapping(value="/{path}/write", method=RequestMethod.POST)
	public String write(@PathVariable String path, String context, MultipartHttpServletRequest mRequest, Model m) throws Exception {
		int contextI;
		try {
			contextI = Integer.parseInt(context);
		} catch(Exception e) {
			contextI = 0;
		}

		String nick = getNick(mRequest);
		int no = boardDao.write(path, getMemberNo(nick), nick, new Board(mRequest), contextI);
		
		MultipartFile file = mRequest.getFile("file");
		if (!file.isEmpty()) {
			String savePath = mRequest.getServletContext().getRealPath("/resource/file/board");
	
			String[] extension = file.getContentType().split("/");
			String filename = no + "." + extension[extension.length - 1];
			File target = new File(savePath, filename);
			if(!target.exists()) target.mkdirs();
			file.transferTo(target);
		}
		
		if (contextI != 0) return "redirect:/board/" + path + "/detail?no=" + context;
		return "redirect:/board/" + path + "/detail?no=" + no;
	}
	
	@RequestMapping("/{path}/write")
	public String write(@PathVariable String path, String context, Model m) {
		m.addAttribute("context", context);
		
		return "board/write";
	}
	
	@RequestMapping("/{path}/detail")
	public String detail(@PathVariable String path, HttpServletRequest req, String no, Model m) throws Exception {
		String page = req.getParameter("page");
		String search = req.getParameter("search");
		String key = req.getParameter("key");
		
		int noI;
		try {
			noI = Integer.parseInt(no);
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		List<Board> board = boardDao.detail(noI);
		if (board.size() == 0) throw new Exception("404");
		boardDao.readUp(noI);
		List<Comment> list = commentDao.list(noI);
		
		int memberNo = getMemberNo(getNick(req));
		
		String url = path + "?page=" + page;
		if (search != null && key != null) {
			url += "&search=" + search + "&key=" + key;
			m.addAttribute("search", search);
			m.addAttribute("key", key);
		}
		
		String url2 = "?page=" + page;
		if (search != null && key != null) {
			url += "&search=" + search + "&key=" + key;
			m.addAttribute("search", search);
			m.addAttribute("key", key);
		}
		
		m.addAttribute("url", url);
		m.addAttribute("url2", url2);
		m.addAttribute("memberNo", memberNo);
		m.addAttribute("boardList", board);
		m.addAttribute("list", list);
		m.addAttribute("no", no);
		
		return "board/detail";
	}
	
	@RequestMapping("/{path}/best")
	@ResponseBody
	public String best(String no, HttpServletRequest req) throws Exception {
		int noI;
		try {
			noI = Integer.parseInt(no);
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		boolean result = boardDao.best(noI, getMemberNo(getNick(req)));

		if (result) {
			boardDao.best(noI);
			return "true";
		}
		return "false";
	}

	@RequestMapping(value="/{path}/edit", method=RequestMethod.POST)
	public String edit(@PathVariable String path, String context, MultipartHttpServletRequest mRequest, int no, Model m) throws Exception {
		MultipartFile file = mRequest.getFile("file");
		if (!file.isEmpty()) {
			String savePath = mRequest.getServletContext().getRealPath("/resource/file/board");
			
			deleteFile(savePath, String.valueOf(no));
	
			String[] extension = file.getContentType().split("/");
			String filename = no + "." + extension[extension.length - 1];
			File target = new File(savePath, filename);
			file.transferTo(target);
		}
		
		boardDao.edit(no, getMemberNo(getNick(mRequest)), new Board(mRequest));

		return "redirect:/board/" + path + "/detail?no=" + context;
	}
	
	@RequestMapping("/{path}/edit")
	public String edit(@PathVariable String path, HttpServletRequest req, String no, String context, Model m) throws Exception {
		if (isWriter(req) == false) throw new Exception("404"); 
		
		int noI;
		try {
			noI = Integer.parseInt(no);
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		Board board = boardDao.detailOne(noI);
		
		m.addAttribute("no", no);
		m.addAttribute("context", context);
		m.addAttribute("unit", board);
		
		return "board/edit";
	}
	
	@RequestMapping("/{path}/delete")
	public String delete(@PathVariable String path, HttpServletRequest req, String no, String context) throws Exception {
		if (isWriter(req) == false) throw new Exception("404"); 
		
		int noI;
		try {
			noI = Integer.parseInt(no);
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		String savePath = req.getServletContext().getRealPath("/resource/file/board");
		deleteFile(savePath, String.valueOf(no));
		
		boardDao.delete(noI);
		commentDao.delete(noI);

		if (no.equals(context)) {
			return "redirect:/board/" + path;
		} else {
			return "redirect:/board/" + path + "/detail?no=" + context;
		}
	}
	
	@RequestMapping("/{path}/download/{no}")
	public String download(@PathVariable String path, @PathVariable String no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int noI;
		try {
			noI = Integer.parseInt(no);
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		String savePath = request.getServletContext().getRealPath("/resource/file/board");
		
		Board board = boardDao.detailOne(noI);
		
		File target = new File(savePath, board.getFilename());
		byte[] data = FileUtils.readFileToByteArray(target);

		String originname = new String(board.getOriginfile().getBytes("UTF-8"), "ISO-8859-1");
		
		response.setContentType("application/octet-stream");//전송할 유형
		response.setContentLength(data.length);//전송할 크기
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Disposition", 
											"attachment; fileName=\""+ originname +"\";");
		
		OutputStream out = response.getOutputStream();
		out.write(data);
		out.close();
		
		String boardContext = String.valueOf(board.getContext());
		if (boardContext != null) return "redirect:/board/" + path + "/detail?no=" + boardContext;
		return "redirect:/board/" + path + "/detail?no=" + no;
	}
	
	@RequestMapping("/{path}/reply")
	public String reply(@PathVariable String path, String no, Model m) throws Exception {
		int noI;
		try {
			noI = Integer.parseInt(no);
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		m.addAttribute("context", no);
		
		return "redirect:/board/" + path + "/write";
	}
	
	@RequestMapping("/{path}/comment")
	public String comment(@PathVariable String path, HttpServletRequest request, Model m) throws Exception {
		if (request.getParameter("detail").equals("")) return null;
		String nick = getNick(request);
		int memberNo = getMemberNo(nick);

		Comment comment = commentDao.insert(nick, memberNo, new Comment(request));
		m.addAttribute("comment", comment);
		
		return "board/comment";
	}
	
	@RequestMapping("/{path}/commentBest")
	@ResponseBody
	public String commentBest(HttpServletRequest req, int commentNo) throws Exception {
		if (isCommentWriter(req)) throw new Exception("404");
		Comment comment = commentDao.best(commentNo);
		int best = comment.getBest();
		
		return String.valueOf(best);
	}
	
	@RequestMapping("/{path}/commentDelete")
	@ResponseBody
	public void commentDelete(HttpServletRequest req, int commentNo, boolean result) throws Exception {
		if (isCommentWriter(req) == false) throw new Exception("404");
		
		if (result) commentDao.deleteOne(commentNo);
	}
	
}