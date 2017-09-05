package spring.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value="/data/mail", method=RequestMethod.GET)
	public String mailGet(Model m, HttpServletRequest req) {
		String id = "회원(수신이)";
		String box = (req.getParameter("box")==null)?"index":req.getParameter("box");
		
		List<Mail>list=mailDao.list(id,box);
		
		m.addAttribute("list", list);
		return "data/mail";
	}
	
	@RequestMapping(value="/data/mail", method=RequestMethod.POST)
	public String mailPost(Model m, HttpServletRequest req) {
		String id = "회원(수신이)";
		
		//delete,no[] 또는 protect,no[] 이렇게 들어옴
		Map<String, String[]> map = req.getParameterMap();
		Set<String> keys = map.keySet();
		
		for(String location:keys) {
			for(String no : map.get(location)) {
				if(location.equals("protect")) {
					mailDao.update(id, location, Integer.parseInt(no));
				}else if(location.equals("garbage")) {
					if(req.getParameter("box").equals("garbage")) {
						mailDao.delete(id, Integer.parseInt(no));
					}else {
						mailDao.update(id, location, Integer.parseInt(no));
					}
				}
			}
		}
		
		List<Mail>list=mailDao.list(id,req.getParameter("box"));
		
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
	
	@RequestMapping(value="/data/mailDetail", method=RequestMethod.GET)
	public String mailDetailGet(Model m, HttpServletRequest req) throws Exception {
		String id = "회원(수신이)";//사용자 아이디
		int no;
		try {
			no = Integer.parseInt(req.getParameter("no"));
		}catch(Exception e) {
			throw new Exception("404");
		}
		
		Mail mail = mailDao.select(id, no);
		if(mail==null) throw new Exception("404");
		
		String box = mailDao.location(Integer.parseInt(req.getParameter("no")));
		
		m.addAttribute("mail", mail);
		m.addAttribute("box", box);
		
		return "data/mailDetail";
	}
	
	@RequestMapping(value="/data/mailDetail", method=RequestMethod.POST)
	public String mailDetailPost(Model m,HttpServletRequest req) throws Exception {
		String id = "회원(수신이)";
		
		String box = req.getParameter("box");
		
		if(box.equals("garbage")) {
			mailDao.delete(id, Integer.parseInt(req.getParameter("no")));
		}else {
			mailDao.update(id, "garbage", Integer.parseInt(req.getParameter("no")));
		}
		
		return "redirect:mail?box="+box;
	}
}