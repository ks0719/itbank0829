package spring.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.CookieGenerator;

import spring.db.mail.Mail;
import spring.db.mail.MailDao;
import spring.db.member.Member;
import spring.db.member.MemberDao;
import spring.db.myinfo.MyDao;
import spring.db.myinfo.MyDto;
import spring.db.mylecture.MyLecture;
import spring.db.mylecture.MyLectureDao;

@Controller
public class DataController {
	private Logger log = LoggerFactory.getLogger(getClass());

	public static final int MY_LECTURE_PAGE = 10;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private MyLectureDao myLectureDao;
	@Autowired
	private MyDao myDao;
	@Autowired
	private MemberDao mbdao;
	@Autowired
	private MailDao mailDao;

	private String getNick(HttpServletRequest req) throws Exception {
		Cookie[] c = req.getCookies();
		if (c != null) {
			for (int i = 0; i < c.length; i++) {
				Cookie ck = c[i];
				// 저장된 쿠키 이름을 가져온다
				String cName = ck.getName();
				// 쿠키값을 가져온다
				String cValue = URLDecoder.decode((ck.getValue()), "utf-8");
				// log.debug("쿠키값 :"+cValue);
				if (cName.equals("mynick")) {
					return cValue;
				}
			}
		}
		throw new Exception("404");
	}

	@RequestMapping("/data/edit")
	public String editget(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		Cookie[] c = request.getCookies();
		if (c != null) {
			for (int i = 0; i < c.length; i++) {
				Cookie ck = c[i];
				// 저장된 쿠키 이름을 가져온다
				String cName = ck.getName();
				// 쿠키값을 가져온다
				String cValue = URLDecoder.decode((ck.getValue()), "utf-8");
				// log.debug("쿠키값 :"+cValue);
				if (ck.getName().equals("mynick")) {
					MyDto dto = myDao.select(cValue);
					model.addAttribute("dto", dto);
					break;
				}
			}
		}
		return "data/edit";
	}

	@RequestMapping(value = "/data/edit", method = RequestMethod.POST)
	public String editpost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Member mb = new Member(request);
		String nick = getNick(request);
		nick = mbdao.edit(mb, nick);
		// log.debug("최종 닉네임 : "+nick);
		CookieGenerator cookie = new CookieGenerator();

		cookie.setCookieName("mynick");
		cookie.setCookiePath("/");
		cookie.setCookieMaxAge(-1);
		cookie.addCookie(response, URLEncoder.encode(nick, "utf-8"));

		return "redirect:/data/maininfo";
	}

	@RequestMapping("/data/exit")
	public String exit() {

		return "data/exit";
	}
	
	
	@RequestMapping(value="/data/nickedit", method = RequestMethod.POST)
	public String editnick(@RequestParam String nick, HttpServletRequest request) {
		System.out.println(nick);
		
		boolean result=mbdao.check("nick", nick);
		if(!result) {
			
			return "data/edit";
		}else {
			
			return null;
		}
	}
	
	@RequestMapping(value="/data/phoneedit", method = RequestMethod.POST)
	public String editphone(@RequestParam String phone, HttpServletRequest request) throws Exception {
		System.out.println(phone);
		
		//내가 쓴거랑 디비안에 있는 정보랑 같은지 ?
		String nick=getNick(request);
		
		Member mb= mbdao.select(nick);
		if(mb.equals(phone)) return "data/edit";
		
		//중복체크
		boolean result=mbdao.check("phone", phone);
		
		if(!result) {
			
			return "data/edit";
		}else {
			
			return null;
		}
	}

	@RequestMapping("/data/maininfo")
	public String maininfo(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		Cookie[] c = request.getCookies();
		if (c != null) {
			for (int i = 0; i < c.length; i++) {
				Cookie ck = c[i];
				// 저장된 쿠키 이름을 가져온다
				// log.debug("쿠키값들 : "+ck.getName());
				String cName = ck.getName();
				// 쿠키값을 가져온다
				String cValue = URLDecoder.decode((ck.getValue()), "utf-8");
				// log.debug("쿠키값(maininfo) :"+cValue);
				if (ck.getName().equals("mynick")) {
					// log.debug("쿠키값 찾음");
					MyDto dto = myDao.select(cValue);
					model.addAttribute("dto", dto);
					break;
				}
				// log.debug("쿠키값 못찾음");
			}
		}
		return "data/maininfo";
	}

	@RequestMapping(value = "/data/mail", method = RequestMethod.GET)
	public String mailGet(Model m, HttpServletRequest req) throws Exception {
		String nick = getNick(req);

		String box = (req.getParameter("box") == null) ? "index" : req.getParameter("box");

		List<Mail> list = mailDao.list(nick, box);

		m.addAttribute("list", list);
		return "data/mail";
	}

	@RequestMapping(value = "/data/mail", method = RequestMethod.POST)
	public String mailPost(Model m, HttpServletRequest req) throws Exception {
		// garbage,no[] 또는 protect,no[] 이렇게 들어옴
		Map<String, String[]> map = req.getParameterMap();
		Set<String> params = map.keySet();
		String nick = getNick(req);
		for (String location : params) {
			for (String no : map.get(location)) {
				if (location.equals("protect")) {
					mailDao.update(nick, location, Integer.parseInt(no));
				} else if (location.equals("garbage")) {
					if (req.getParameter("box").equals("garbage")) {
						mailDao.delete(nick, Integer.parseInt(no));
					} else {
						mailDao.update(nick, location, Integer.parseInt(no));
					}
				}
			}
		}

		List<Mail> list = mailDao.list(nick, req.getParameter("box"));

		m.addAttribute("list", list);
		return "data/mail";
	}

	@RequestMapping("/data/pay")
	public String pay() {

		return "data/pay";
	}

	@RequestMapping("/data/point")
	public String point(HttpServletRequest request, Model model) throws Exception {
		String nick = getNick(request);
		int point = mbdao.mypoint(nick);
		model.addAttribute("point", point);
		return "data/point";
	}

	@RequestMapping(value = "/data/point", method = RequestMethod.POST)
	public String pointpost(HttpServletRequest request, Model model) throws Exception {
		String nick = getNick(request);
		int select = Integer.parseInt(request.getParameter("point"));
		String point = null;
		int money = 0;
		switch (select) {
		case 1:
			point = "1,000";
			money = 10000;
			break;
		case 2:
			point = "3,000";
			money = 30000;
			break;
		case 3:
			point = "5,000";
			money = 50000;
			break;
		case 4:
			point = "7,000";
			money = 70000;
			break;
		case 5:
			point = "10,000";
			money = 100000;

		}
		Member list = mbdao.list(nick);
		model.addAttribute("list", list);
		model.addAttribute("point", point);
		model.addAttribute("money", money);
		return "data/pay";
	}

	@RequestMapping("/data/manageLecture")
	public String manageLecture(Model m, HttpServletRequest req) throws Exception {
		// page 넘버 설정
		int page;
		try {
			page = Integer.parseInt(req.getParameter("page"));
		} catch (Exception e) {
			page = 1;
		}
		page = (page < 1) ? 1 : page;

		String box = (req.getParameter("box") == null) ? "index" : req.getParameter("box");

		String nick = getNick(req);

		List<MyLecture> list = myLectureDao.list(nick, box, page);

		int start = (page - 1) / MY_LECTURE_PAGE + 1;
		int end = start + MY_LECTURE_PAGE - 1;

		int maxPage = myLectureDao.maxPage(nick, box);
		end = (end > maxPage) ? maxPage : end;

		m.addAttribute("list", list);
		m.addAttribute("page", page);
		m.addAttribute("start", start);
		m.addAttribute("end", end);
		return "data/manageLecture";
	}

	@RequestMapping(value = "/data/mailDetail", method = RequestMethod.GET)
	public String mailDetailGet(Model m, HttpServletRequest req) throws Exception {
		int no;
		try {
			no = Integer.parseInt(req.getParameter("no"));
		} catch (Exception e) {
			throw new Exception("404");
		}

		String box = req.getParameter("box");

		String nick = getNick(req);

		Mail mail = mailDao.select(nick, no, box);
		if (mail == null)
			throw new Exception("404");

		m.addAttribute("mail", mail);

		mailDao.read(mail, nick);
		return "data/mailDetail";
	}

	@RequestMapping(value = "/data/mailDetail", method = RequestMethod.POST)
	public String mailDetailPost(Model m, HttpServletRequest req) throws Exception {
		String box = req.getParameter("box");

		String nick = getNick(req);

		if (box.equals("garbage")) {
			mailDao.delete(nick, Integer.parseInt(req.getParameter("no")));
		} else {
			mailDao.update(nick, "garbage", Integer.parseInt(req.getParameter("no")));
		}

		return "redirect:mail?box=" + box;
	}

	@RequestMapping(value = "data/mail/send", method = RequestMethod.GET)
	public String sendGet(Model m, HttpServletRequest req) {
		System.out.println(req.getParameter("nick"));
		m.addAttribute("nick", req.getParameter("nick"));
		return "data/send";
	}

	@RequestMapping(value = "data/mail/send", method = RequestMethod.POST)
	public String sendPost(HttpServletRequest req) throws Exception {
		// db 연결해서 mail 테이블에 정보 추가하기
		String nick = getNick(req);

		Mail mail = new Mail(req);
		mail.setMail_writer(nick);
		mail.setMail_read("안읽음");

		mailDao.insert(mail);

		return "redirect:/data/mail";
	}

	@RequestMapping(value = "/data/mail/nickcheck", method = RequestMethod.POST)
	public String idcheck(@RequestParam String nick) throws Exception {
		boolean result = mailDao.isExist(nick);

		if (result)
			return "data/send";
		else
			return null;
	}

	@RequestMapping("/data/changepw")
	public String changepw() {

		return "data/changepw";
	}

	@RequestMapping(value = "/data/changepw", method = RequestMethod.POST)
	public String changepwpost(HttpServletRequest request, Model model) throws Exception {
		String nick = getNick(request);
		// log.debug("nick="+nick);
		String pw = request.getParameter("pw");
		String spw = mbdao.mypwnick(nick);
		// log.debug("pw="+pw);
		String newpw = request.getParameter("newpw");
		// log.debug("newpw="+newpw);
		boolean same = passwordEncoder.matches(pw, spw);
		if (same) {
			newpw = passwordEncoder.encode(newpw);
			boolean state = mbdao.changepw(nick, newpw);
			if (state) {
				model.addAttribute("msg", "비밀번호가 정상적으로 변경되었습니다.");
				model.addAttribute("url", "/data/maininfo");
				return "data/redirect";
			}
		}
		model.addAttribute("msg", "현재 비밀번호가 일치하지 않습니다.");
		model.addAttribute("url", "/data/changepw");
		return "data/redirect";

	}

	@RequestMapping("/data/redirect")
	public String redirect() {

		return "data/redirect";
	}
	@RequestMapping("/data/complate")
	public String complate() {
		return "data/complate";
	}
}