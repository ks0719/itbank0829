package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudyController {

	@RequestMapping("/study/teacher")
	public String teacher() {
		
		return "study/teacher";
	}
	
	@RequestMapping("/study/assess")
	public String assess() {
		
		return "study/assess";
	}
	
	@RequestMapping("/study/class")
	public String lesson() {
		
		return "study/class";
	}
	
	@RequestMapping("/study/req")
	public String req() {
		
		return "study/req";
		
	}
	
	@RequestMapping("/study/study")
	public String study(String type, String key) {
		
		return "study/study";
	}
}