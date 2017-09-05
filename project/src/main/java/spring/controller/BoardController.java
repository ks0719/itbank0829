package spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.db.board.Board;
import spring.db.board.BoardDao;

@Controller
@RequestMapping("/board")
public class BoardController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BoardDao boardDao;
	
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
		log.debug(String.valueOf(listCount));
		
		int boardSize = 10;
		int start = boardSize * pageNo - 9;
		int end = start + boardSize -1;
		if (end > listCount) end = listCount;
		
		log.debug(start + ", " + end);
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
		return "board/allBoard";
	}
	
	@RequestMapping(value="/{path}/write", method=RequestMethod.POST)
	public String write(@PathVariable String path, HttpServletRequest request, Model m) {
		int no = boardDao.write(path, new Board(request));
		
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
		
		log.debug(String.valueOf(noI));
		Board board = boardDao.detail(noI);
		
		m.addAttribute("unit", board);
		
		return "board/detail";
	}

	@RequestMapping(value="/{path}/edit", method=RequestMethod.POST)
	public String edit(@PathVariable String path, HttpServletRequest request, int no, Model m) {
		boardDao.edit(no, new Board(request));
		
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
	
}