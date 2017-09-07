package spring.controller;

import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.db.member.Member;
import spring.db.member.MemberDao;

@Controller
public class MemberController {
	private Logger log=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(value="/member/sign",method=RequestMethod.GET)
	public String signGet(Model m) {
		m.addAttribute("loginCheck", false);
		m.addAttribute("pwCheck", false);
		return "member/sign";
	}
	
	@RequestMapping(value="/member/sign", method=RequestMethod.POST)
	public String signPost(HttpServletRequest request) throws SQLException {
	
//		String id=request.getParameter("id");
//		System.out.println(id);
		
		Member m=new Member(request);
		memberDao.insert(m);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/member/idcheck", method = RequestMethod.POST)
	public String idcheck(@RequestParam String id) throws Exception {
		boolean result = memberDao.check("id",id);
		if(!result) return "member/sign";
		else return null;
	}
	
	@RequestMapping(value="/member/nickcheck", method = RequestMethod.POST)
	public String nickname(@RequestParam String nick) throws Exception {
		boolean result = memberDao.check("nick",nick);
		if(!result) return "member/sign";
		else return null;
	}
	
	@RequestMapping(value="/member/pcheck", method = RequestMethod.POST)
	public String pcheck(@RequestParam String phone) throws Exception {
		boolean result = memberDao.check("phone", phone);
		if(!result) return "member/sign";
		else return null;
	}
	
	
	@RequestMapping(value="/member/login",method=RequestMethod.POST)
	public String loginpost(HttpServletRequest request,Model model,HttpServletResponse response) {
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		//log.debug("id="+id+",pw="+pw);
		String url=request.getParameter("page");
		
		url=url.replaceAll("http://localhost:8080/project/WEB-INF/view", "").replaceAll(".jsp", "");
		log.debug("url="+url);
		boolean state=memberDao.logincheck(id, pw);
		//log.debug("state="+state);
		if(state) {
			Cookie cookie=new Cookie("myid", id);
			cookie.setPath("/");
			cookie.setComment("로그인시 얻어지는 나의 아이디입니다. 원래는 닉네임 아니었나?");
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
		return "redirect:"+url;
		}
		else {
			return "member/fail";
		}
	}

}
