package spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.db.mail.Mail;
import spring.db.mail.MailDao;
import spring.db.mylecture.MyLecture;
import spring.db.mylecture.MyLectureDao;

@Controller
public class DataController {
	public static final int MY_LECTURE_PAGE = 10;
	
	@Autowired
	private MyLectureDao myLectureDao;
	
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
		
		System.out.println(mailDao);
		
		List<Mail>list=mailDao.list("회원(수신이)",req.getParameter("box"));
		
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
	public String manageLecture(Model m, HttpServletRequest req) throws Exception {
		String id = "테스트유저1";
		
		//page 넘버 설정
		int page;
		try {
			page = Integer.parseInt(req.getParameter("page"));
		}catch(Exception e) {
			page=1;
		}
		page=(page<1)?1:page;
		
		String box = (req.getParameter("box")==null)?"index":req.getParameter("box");
		
		List<MyLecture> list = myLectureDao.list(id, box, page);
		
		int start = (page-1)/MY_LECTURE_PAGE*MY_LECTURE_PAGE+1;
		int end = start + MY_LECTURE_PAGE-1;
		
		int maxPage = myLectureDao.maxPage(id, box);
		end = (end>maxPage)?maxPage:end;
		
		m.addAttribute("list", list);
		m.addAttribute("page", page);
		m.addAttribute("start", start);
		m.addAttribute("end", end);
		return "data/manageLecture";
	}
	
	@RequestMapping("/data/mail/mailDetail")
	public String mailDetail() {
		
		
	
		
		return "data/mailDetail";
	}
}