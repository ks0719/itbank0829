package spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.db.board.Board;
import spring.db.board.BoardDao;
import spring.db.board.Reply;
import spring.db.board.ReplyDao;

@Controller
@RequestMapping("/board")
public class BoardController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private ReplyDao replyDao;
	
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
	public String write(@PathVariable String path, MultipartHttpServletRequest mRequest, Model m) throws IllegalStateException, IOException {
		MultipartFile file = mRequest.getFile("file");
		String savePath = mRequest.getServletContext().getRealPath("/resource/file");

		String[] extension = file.getContentType().split("/");
		int no = boardDao.write(path, new Board(mRequest));
		String filename = no + "." + extension[extension.length - 1];
		File target = new File(savePath, filename);
		file.transferTo(target);		
		
		return "redirect:/board/" + path + "/detail?no=" + no;
	}
	
	@RequestMapping("/{path}/write")
	public String write(@PathVariable String path, Model m) {
		return "board/write";
	}
	
	@RequestMapping("/{path}/detail")
	public String write(@PathVariable String path, String no, Model m) throws Exception {
		int noI;
		try {
			noI = Integer.parseInt(no);
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		Board board = boardDao.detail(noI);
		boardDao.readUp(noI);
		List<Reply> list = replyDao.list(noI);
		
		m.addAttribute("unit", board);
		m.addAttribute("list", list);
		
		return "board/detail";
	}
	
	@RequestMapping("/{path}/best")
	public String best(@PathVariable String path, String no) throws Exception {
		int noI;
		try {
			noI = Integer.parseInt(no);
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		boardDao.best(noI);
		
		return "redirect:/board/" + path + "/detail?no=" + no;
	}

	@RequestMapping(value="/{path}/edit", method=RequestMethod.POST)
	public String edit(@PathVariable String path, MultipartHttpServletRequest mRequest, int no, Model m) throws IllegalStateException, IOException {
		MultipartFile file = mRequest.getFile("file");
		String savePath = mRequest.getServletContext().getRealPath("/resource/file");

		String[] extension = file.getContentType().split("/");
		boardDao.edit(no, new Board(mRequest));
		String filename = no + "." + extension[extension.length - 1];
		File target = new File(savePath, filename);
		file.transferTo(target);		
		
		return "redirect:/board/" + path + "/detail?no=" + no;
	}
	
	@RequestMapping("/{path}/edit")
	public String edit(@PathVariable String path, String no, Model m) throws Exception {
		int noI;
		try {
			noI = Integer.parseInt(no);
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		Board board = boardDao.detail(noI);
		
		m.addAttribute("no", no);
		m.addAttribute("unit", board);
		
		return "board/edit";
	}
	
	@RequestMapping("/{path}/delete")
	public String delete(@PathVariable String path, String no) throws Exception {
		int noI;
		try {
			noI = Integer.parseInt(no);
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		boardDao.delete(noI);
		
		return "redirect:/board/" + path;
	}
	
	@Autowired
	private ServletContext context;
	
	@RequestMapping("/{path}/download/{no}")
	public String download(@PathVariable String path, @PathVariable String no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int noI;
		try {
			noI = Integer.parseInt(no);
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		String savePath = context.getRealPath("/resource/file");
		
		Board board = boardDao.detail(noI);
		
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

		return "redirect:/board/" + path + "/detail?no=" + no;
	}
	
}