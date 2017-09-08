package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
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

}
