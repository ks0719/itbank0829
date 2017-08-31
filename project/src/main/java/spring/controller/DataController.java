package spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.db.mail.Mail;
import spring.db.mail.MailDao;

@Controller
public class DataController {
	
	@Autowired
	private MailDao mailDao;
	
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
	public String note(Model m, HttpServletRequest req) {
		
		List<Mail>list=mailDao.list("회원(수신이)",req.getParameterMap().get("box")[0]);
		m.addAttribute("list", list);
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
	public String manageLecture() {
		
		return "data/manageLecture";
	}
	@RequestMapping("/data/mail/mailDetail")
	public String mailDetail() {
	
		
		return "data/mailDetail";
	}
}