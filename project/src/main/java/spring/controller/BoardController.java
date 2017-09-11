package spring.controller;

import java.io.File;
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

@Controller
@RequestMapping("/board")
public class BoardController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private CommentDao commentDao;
	
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
	
	@RequestMapping("/{path}")
	public String board(@PathVariable String path, HttpServletRequest request, Model m) {	
		String type = request.getParameter("type");
		String key = request.getParameter("key");
		
		int pageNo;
		try {
			pageNo = Integer.parseInt("page");
		} catch(Exception e) {
			pageNo = 1;
		}
		if (pageNo <= 0 ) pageNo = 1;

		int listCount = boardDao.count(path, type, key);
//		log.debug(String.valueOf(listCount));
		
		int boardSize = 10;
		int start = boardSize * pageNo - 9;
		int end = start + boardSize -1;
		if (end > listCount) end = listCount;
		
//		log.debug(start + ", " + end);
		List<Board> list = boardDao.list(path, type, key, start, end);
		
		int blockSize = 10;
		int blockTotal = (listCount + boardSize - 1) / boardSize;
		int startBlock = (pageNo - 1) / blockSize * blockSize + 1;
		int endBlock = startBlock + blockSize - 1;
		if (endBlock > blockTotal) endBlock = blockTotal;
		
		String url = path + "?";
		if (type != null && key != null) {
			url += "type=" + type + "&key=" + key + "&";
			m.addAttribute("type", type);
			m.addAttribute("key", key);
		}
		
		m.addAttribute("list", list);
		m.addAttribute("page", pageNo);
		m.addAttribute("startBlock", startBlock);
		m.addAttribute("endBlock", endBlock);
		m.addAttribute("url", url);
		m.addAttribute("path", path);
		return "board/" + path;
	}
	
	@RequestMapping(value="/{path}/write", method=RequestMethod.POST)
	public String write(@PathVariable String path, String context, MultipartHttpServletRequest mRequest, Model m) throws Exception {
		int contextI;
		try {
			contextI = Integer.parseInt(context);
		} catch(Exception e) {
			contextI = 0;
		}
		
		MultipartFile file = mRequest.getFile("file");
		String savePath = mRequest.getServletContext().getRealPath("/resource/file");

		String[] extension = file.getContentType().split("/");
		String nick = getNick(mRequest);
		log.debug("write nick : " + nick);
		int no = boardDao.write(path, nick, new Board(mRequest), contextI);
		String filename = no + "." + extension[extension.length - 1];
		File target = new File(savePath, filename);
		if(!target.exists()) target.mkdirs();
		file.transferTo(target);		
		
		if (contextI != 0) return "redirect:/board/" + path + "/detail?no=" + context;
		return "redirect:/board/" + path + "/detail?no=" + no;
	}
	
	@RequestMapping("/{path}/write")
	public String write(@PathVariable String path, String context, Model m) {
		m.addAttribute("context", context);
		
		return "board/write";
	}
	
	@RequestMapping("/{path}/detail")
	public String detail(@PathVariable String path, String no, Model m) throws Exception {
		int noI;
		try {
			noI = Integer.parseInt(no);
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		List<Board> board = boardDao.detail(noI);
		log.debug("갯수 : " + board.size());
		boardDao.readUp(noI);
		List<Comment> list = commentDao.list(noI);
		
		m.addAttribute("no", no);
		m.addAttribute("boardList", board);
		m.addAttribute("list", list);
		
		return "board/detail";
	}
	
	@RequestMapping("/{path}/best")
	public String best(@PathVariable String path, String no, String context) throws Exception {
		int noI;
		try {
			noI = Integer.parseInt(no);
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		boardDao.best(noI);

		if (context != null) return "redirect:/board/" + path + "/detail?no=" + context;
		return "redirect:/board/" + path + "/detail?no=" + no;
	}

	@RequestMapping(value="/{path}/edit", method=RequestMethod.POST)
	public String edit(@PathVariable String path, String context, MultipartHttpServletRequest mRequest, int no, Model m) throws IllegalStateException, IOException {
		MultipartFile file = mRequest.getFile("file");
		String savePath = mRequest.getServletContext().getRealPath("/resource/file");

		String[] extension = file.getContentType().split("/");
		boardDao.edit(no, new Board(mRequest));
		String filename = no + "." + extension[extension.length - 1];
		File target = new File(savePath, filename);
		file.transferTo(target);		

		return "redirect:/board/" + path + "/detail?no=" + context;
	}
	
	@RequestMapping("/{path}/edit")
	public String edit(@PathVariable String path, String no, String context, Model m) throws Exception {
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
	public String delete(@PathVariable String path, String no, String context) throws Exception {
		int noI;
		try {
			noI = Integer.parseInt(no);
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		boardDao.delete(noI);
		commentDao.delete(noI);
		
		log.debug("no : " + no + ", context : " + context);
		log.debug(String.valueOf(context.equals(no)));
		if (context.equals(no)) return "redirect:/board/" + path;
		return "redirect:/board/" + path + "/detail?no=" + context;
	}
	
	@RequestMapping("/{path}/download/{no}")
	public String download(@PathVariable String path, @PathVariable String no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int noI;
		try {
			noI = Integer.parseInt(no);
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		String savePath = request.getServletContext().getRealPath("/resource/file");
		
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
		String nick = getNick(request);
		log.debug("comment nick : " + nick);
		Comment comment = commentDao.insert(nick, new Comment(request));
		m.addAttribute("comment", comment);
		
		return "board/comment";
	}
	
	@RequestMapping("/{path}/commentBest")
	@ResponseBody
	public String commentBest(int commentNo) {
		Comment comment = commentDao.best(commentNo);
		int best = comment.getBest();
		
		return String.valueOf(best);
	}
	
	@RequestMapping("/{path}/commentDelete")
	@ResponseBody
	public void commentDelete(int commentNo) {
		commentDao.deleteOne(commentNo);
	}
	
}