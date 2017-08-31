package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.db.mylecture.MyLecture;
import spring.db.mylecture.MyLectureDao;

@Controller
public class DataController {
	
	@Autowired
	private MyLectureDao myLectureDao;
	
	@RequestMapping("/data/edit")
	public String edit() {
		
		return "data/edit";
	}
	@RequestMapping("/data/exit")
	public String exit() {
		
		return "data/exit";
	}
	@RequestMapping("/data/maininfo")
	public String maininfo() {
		
		return "data/maininfo";
	}
	@RequestMapping("/data/mail")
	public String note() {
		
		return "data/mail";
	}
	@RequestMapping("/data/pay")
	public String pay() {
		
		return "data/pay";
	}
	@RequestMapping("/data/point")
	public String point() {
		
		return "data/point";
	}
	@RequestMapping("/data/manageLecture")
	public String manageLecture(Model m, @RequestParam String box) throws Exception {
		
		List<MyLecture> list = myLectureDao.list("운영자-회원", box, 1, 10);
		
		m.addAttribute("list", list);
		return "data/manageLecture";
	}
	@RequestMapping("/data/mail/mailDetail")
	public String mailDetail() {
		
		
		
		return "data/mailDetail";
	}
}