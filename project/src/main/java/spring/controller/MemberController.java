package spring.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import org.springframework.web.servlet.ModelAndView;

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
	public String loginpost(HttpServletRequest request,Model model,HttpServletResponse response) throws UnsupportedEncodingException {
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		//log.debug("id="+id+",pw="+pw);
		String url=request.getParameter("page");
		//log.debug("url="+url);
		String param = request.getParameter("param");	
		param = param.replaceAll(", ", "&");
		param = param.substring(1, param.length()-1);
		log.debug(param);
		url=url.replaceAll("http://localhost:8080/project/WEB-INF/view", "").replaceAll(".jsp", "");
		url += "?"+param;
		log.debug("url="+url);
		String nick=memberDao.logincheck(id, pw);
		//log.debug("state="+state);
		if(nick!=null) {
			Cookie cookie=new Cookie("mynick", URLEncoder.encode(nick,"UTF-8"));
			cookie.setPath("/");
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
		return "redirect:"+url;
		}
		else {
			return "member/fail";
		}
	}
	
	@RequestMapping(value="/member/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Cookie[] c=request.getCookies();
		if(c!=null) {
			for(int i=0; i<c.length; i++) {
				Cookie ck=c[i];
				String cName=ck.getName();
				String cValue=URLDecoder.decode(ck.getValue(), "UTF-8");
				if(cName.equals("mynick")) {
					ck.setPath("/");
					ck.setMaxAge(0);
					response.addCookie(ck);
				}
			}
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/member/delmember")
	public void deletemember(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = (String)request.getSession().getAttribute("id");
		
		MemberDao mdao=new MemberDao();
		mdao.delmember(id);
		request.getSession().removeAttribute("id");
		request.removeAttribute("id");
		response.sendRedirect("redirec:/");
		
		return;
	}
}
