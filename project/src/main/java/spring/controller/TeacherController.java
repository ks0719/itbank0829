package spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
//	@Autowired
//	private TeacherDao teacherDao;
	
	@RequestMapping("/lecturer")
	public String lecturer(String page, Model m) {
		int pageNo;
		try {
			pageNo = Integer.parseInt(page);
		} catch(Exception e) {
			pageNo = 1;
		}
		
		m.addAttribute("page", pageNo);
		
		return "teacher/lecturer";
	}
	
	@RequestMapping("/lecturerArray")
	public String lecturerArray(String standard, Model m) throws Exception {
		String sub1, sub2;
		if (standard.equals("sort")) {
			sub1 = "grade";
			sub2 = "count";
		} else if (standard.equals("count")) {
			sub1 = "grade";
			sub2 = "sort";
		} else if (standard.equals("grade")) {
			sub1 = "count";
			sub2 = "sort";
		} else {
			throw new Exception("404");
		}
		
		if (standard.equals("no")) {
//			teacherDao.array(standard);
		} else {
//			teacherDao.array(standard, sub1, sub2);
		}
		
//		m.addAttribute(arg0)
		
		return "teacher/array";
	}
	
	@RequestMapping("/lecturerInfo")
	public String lecturerInfo(String no) {
		
		
		return "teacher/lecturerInfo";
	}

}
