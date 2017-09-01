package spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.db.LectureDao;
import spring.db.LectureInfo;

@Controller
public class StudyController {
	private Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LectureDao lectureDao;

	@RequestMapping("/lecture/teacher")
	public String teacher() {
		
		return "lecture/teacher";
	}
	
	@RequestMapping("/lecture/assess")
	public String assess() {
		
		return "lecture/assess";
	}
	
	@RequestMapping("/lecture/class")
	public String lesson(@RequestParam(required=true) int no, Model m) {
		LectureInfo info = lectureDao.showOne(no);
		
		m.addAttribute("info", info);
		
		return "lecture/class";
	}
	
	@RequestMapping("/lecture/req")
	public String req() {
		
		return "lecture/req";
	}
	
	@RequestMapping("/lecture/study")
	public String study(HttpServletRequest request, Model m) {
		String type = request.getParameter("type");
		String key = request.getParameter("key");
		int pageNo;
		try {
			pageNo = Integer.parseInt(request.getParameter("page"));
		} catch(Exception e) {
			pageNo = 1;
		}
		if (pageNo <= 0 ) pageNo = 1;
		
		int listCount = lectureDao.count(type, key);
		log.debug(String.valueOf(listCount));
		
		int boardSize = 10;
		int start = boardSize * pageNo - 9;
		int end = start + boardSize -1;
		if (end > listCount) end = listCount;
		
		List<LectureInfo> list = lectureDao.list(type, key, start, end);
		
		int blockSize = 10;
		int blockTotal = (listCount + boardSize - 1) / boardSize;
		int startBlock = (pageNo - 1) / blockSize * blockSize + 1;
		int endBlock = startBlock + blockSize - 1;
		if (endBlock > blockTotal) endBlock = blockTotal;
		
		String url = "study?";
		if (type != null && key != null)
			url += "type=" + type + "&key=" + key + "&";
		
		m.addAttribute("list", list);
		m.addAttribute("page", pageNo);
		m.addAttribute("startBlock", startBlock);
		m.addAttribute("endBlock", endBlock);
		m.addAttribute("url", url);
		
		return "lecture/study";
	}
}