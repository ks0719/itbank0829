package spring.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
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

import spring.db.member.Member;
import spring.db.member.MemberDao;

@Controller
public class MemberController {
	private static final String serveraddr="http://localhost:9080/project/WEB-INF/view";
	private String getNick(HttpServletRequest req) throws Exception {
		Cookie[] c=req.getCookies();
		if(c != null){
	        for(int i=0; i < c.length; i++){
	            Cookie ck = c[i] ;
	            // 저장된 쿠키 이름을 가져온다
	            String cName = ck.getName();
	            
	            // 쿠키값을 가져온다
	            String cValue =  URLDecoder.decode(ck.getValue(),"utf-8");
	            log.debug("쿠키값  :"+cValue);
	            if(cName.equals("mynick")) {
	            	return cValue;
	            }
	        }
		}
		return "";
	}
	

	
	private Logger log=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	
	@RequestMapping(value="/member/sign",method=RequestMethod.GET)
	public String signGet(Model m) {
		m.addAttribute("loginCheck", false);
		m.addAttribute("pwCheck", false);
		return "member/sign";
	}
	
	@RequestMapping(value="/member/sign", method=RequestMethod.POST)
	public String signPost(HttpServletRequest request) throws SQLException {
		
		
		Member m=new Member(request);
		String rawPassword=m.getPw();
//		String encodepw=new BCryptPasswordEncoder().encode(rawPassword);
		String encodepw=passwordEncoder.encode(rawPassword);
		m.setPw(encodepw);
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
		
		log.debug("id="+id+",pw="+pw);
		String url=request.getParameter("page");
		log.debug("url="+url);
		String param = request.getParameter("param");
		log.debug("param="+param);
		if(param!=null) {
		param = param.replaceAll(", ", "&");
		param = param.substring(1, param.length()-1);
		}
//		log.debug(param);
		
		url=url.replaceAll(serveraddr, "").replaceAll(".jsp", "");
		url += "?"+param;
//		log.debug("url="+url);
		
//		String encodepw=BCryptPasswordEncoder().encode(pw);
//		String encodepw=passwordEncoder.encode(pw);
//		System.out.println(encodepw);
//		System.out.println("BCrypt 비교: " + passwordEncoding.matches(password, passwordEncoding.encode(password)));
		String nick=memberDao.logincheck(id, pw);
//		log.debug("nick="+nick);
		//log.debug("state="+state);
		
		if(nick!=null) {
			CookieGenerator cookie=new CookieGenerator();
			cookie.setCookieName("mynick");
			cookie.setCookiePath("/");
			cookie.setCookieMaxAge(-1);
			cookie.addCookie(response, URLEncoder.encode(nick, "utf-8"));
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
	
	
	@RequestMapping(value="member/deletemember", method=RequestMethod.GET)
	public String deleteGet() {
		
		return "member/deletemember";
	}
	
	@RequestMapping(value="/member/deletemember", method = RequestMethod.POST)
	public void deletePost( HttpServletRequest req) throws Exception {
		
		String nick=getNick(req);
		String pw=req.getParameter("pw");
		boolean result=memberDao.delete(nick,pw);
		
		if(result) {
		}else {
			throw new Exception();
		}
	}
	
}
