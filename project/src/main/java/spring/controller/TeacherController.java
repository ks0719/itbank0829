package spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.db.lecture.LectureInfo;
import spring.db.teacher.Teacher;
import spring.db.teacher.TeacherDao;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TeacherDao teacherDao;
	
	@RequestMapping("/lecturer")
	public String lecturer(String page, HttpServletRequest request, Model m) {
		String type = request.getParameter("type");
		String key = request.getParameter("key");
		
		int pageNo;
		try {
			pageNo = Integer.parseInt(page);
		} catch(Exception e) {
			pageNo = 1;
		}
		if (pageNo <= 0 ) pageNo = 1;

		int listCount = teacherDao.count(type, key);
		
		int boardSize = 10;
		int start = boardSize * pageNo - 9;
		int end = start + boardSize -1;
		if (end > listCount) end = listCount;
		
		List<Teacher> list = teacherDao.list(type, key, start, end);
		
		int blockSize = 10;
		int blockTotal = (listCount + boardSize - 1) / boardSize;
		int startBlock = (pageNo - 1) / blockSize * blockSize + 1;
		int endBlock = startBlock + blockSize - 1;
		if (endBlock > blockTotal) endBlock = blockTotal;
		
		String url = "lecturer?";
		if (type != null && key != null) {
			url += "type=" + type + "&key=" + key + "&";
			m.addAttribute("type", type);
			m.addAttribute("key", key);
		}
		
		m.addAttribute("list", list);
		m.addAttribute("page", pageNo);
		m.addAttribute("start", start);
		m.addAttribute("end", end);
		m.addAttribute("startBlock", startBlock);
		m.addAttribute("endBlock", endBlock);
		m.addAttribute("url", url);
		
		return "teacher/lecturer";
	}
	
	@RequestMapping("/lecturerArray")
	public String lecturerArray(String standard, String page, String type, String key, int start, int end, Model m) throws Exception {
		log.debug("standard : " + standard);
		String sub1, sub2;
		if (standard.equals("sort")) {
			standard = "sort";
			sub1 = "grade desc";
			sub2 = "count desc";
		} else if (standard.equals("count")) {
			standard = "count desc";
			sub1 = "grade desc";
			sub2 = "sort";
		} else if (standard.equals("grade")) {
			standard = "grade desc";
			sub1 = "count desc";
			sub2 = "sort";
		} else {
			throw new Exception("404");
		}
		
		List<Teacher> list = teacherDao.list(standard, sub1, sub2, type, key, start, end);
		
		m.addAttribute("list", list);
		
		return "teacher/array";
	}
	
	@RequestMapping("/lecturerInfo")
	public String lecturerInfo(HttpServletRequest req, Model m) throws Exception {
		int noI, pageNo;
		try {
			noI = Integer.parseInt(req.getParameter("no"));
		} catch(Exception e) {
			throw new Exception("404");
		}
		try {
			pageNo = Integer.parseInt(req.getParameter("page"));
		} catch(Exception e) {
			pageNo = 1;
		}
		
		Teacher info = teacherDao.showOne(noI);
		
		String url = "?page=" + pageNo;
		if (req.getParameter("type") != null && req.getParameter("key") != null) url += "&type=" + req.getParameter("type") + "&key=" + req.getParameter("key");
		
		m.addAttribute("info", info);
		m.addAttribute("no", noI);
		m.addAttribute("url", url);
		
		return "teacher/lecturerInfo";
	}

}
