package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudyController {

	@RequestMapping("/lecture/teacher")
	public String teacher() {
		
		return "lecture/teacher";
	}
	
	@RequestMapping("/lecture/assess")
	public String assess() {
		
		return "lecture/assess";
	}
	
	@RequestMapping("/lecture/class")
	public String lesson() {
		
		return "lecture/class";
	}
	
	@RequestMapping("/lecture/req")
	public String req() {
		
		return "lecture/req";
	}
	
	@RequestMapping("/lecture/study")
	public String study(String type, String key) {
		
		return "lecture/study";
	}
}