package spring.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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

import spring.db.member.Member;
import spring.db.member.MemberDao;

@Controller
public class MemberController {
	private static final String serveraddr="http://localhost:9080/project/WEB-INF/view";
	private Logger log=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
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
		String nick = null;
		//log.debug("id="+id+",pw="+pw);
		String url=request.getParameter("page");
		//log.debug("url="+url);
		String param = request.getParameter("param");
		//log.debug("param="+param);
		if(param!=null) {
		param = param.replaceAll(", ", "&");
		param = param.substring(1, param.length()-1);
		}
//		log.debug(param);
		
		url=url.replaceAll(serveraddr, "").replaceAll(".jsp", "");
		url += "?"+param;
//		log.debug("url="+url);6
		
//		String encodepw=BCryptPasswordEncoder().encode(pw);
//		String encodepw=passwordEncoder.encode(pw);
//		log.debug("비번======"+encodepw);
//		System.out.println(encodepw);
//		Member m=new Member();
//		System.out.println("BCrypt 비교: " + passwordEncoder.matches(pw,m.getPw()));
//		String nick=memberDao.logincheck(id, pw);
//		log.debug("nick="+nick);
//		log.debug("url="+url);
		String encodepw=memberDao.mypwid(id);
		//log.debug("일치하냐? : "+passwordEncoder.matches(pw, encodepw));
		if(passwordEncoder.matches(pw, encodepw))
		nick=memberDao.logincheck(id, encodepw);
		//log.debug("nick="+nick);
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
	
	@RequestMapping("/member/memberlist")
	public String list(HttpServletRequest request, Model model) {
		String type = request.getParameter("type");
		String key = request.getParameter("key");
		
		
		int pageNo;
		try {
			pageNo = Integer.parseInt(request.getParameter("page"));
		} catch(Exception e) {
			pageNo = 1;
		}
		if (pageNo <= 0 ) pageNo = 1;
		
		int listCount=memberDao.count(type, key);
		log.debug(String.valueOf(listCount));
		
		int boardSize = 10;
		int start = boardSize * pageNo - 9;
		int end = start + boardSize -1;
		if (end > listCount) end = listCount;
		
		List<Member>list=memberDao.list(type, key, start, end);
		
		int blockSize = 10;
		int blockTotal = (listCount + boardSize - 1) / boardSize;
		int startBlock = (pageNo - 1) / blockSize * blockSize + 1;
		int endBlock = startBlock + blockSize - 1;
		if (endBlock > blockTotal) endBlock = blockTotal;
		
		String url = "memberlist?";
		if (type != null && key != null) {
			url += "type=" + type + "&key=" + key + "&";
			model.addAttribute("type", type);
			model.addAttribute("key", key);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("page",pageNo);
		model.addAttribute("startBlock", startBlock);
		model.addAttribute("endBlock", endBlock);
		model.addAttribute("url", url);
		
		return "member/memberlist";
	}
	
	@RequestMapping("/member/memberdetail")
	public String detail(HttpServletRequest req, String no, Model m) throws Exception {
	
		
		int noI;
		try {
			noI = Integer.parseInt(no);
		} catch(Exception e) {
			throw new Exception("404");
		}
		List<Member>member=memberDao.detail(noI);
		if(member.size()==0) throw new Exception("404");
		
		m.addAttribute("no",no);
		m.addAttribute("memberList",member);
		
		return "member/memberdetail";
	}
	
	
	@RequestMapping(value="/member/checkBox", method = RequestMethod.POST)
	public String unsignPost(@RequestParam String[] userid) {
		log.debug("뭐 넘어옴?:"+Arrays.toString(userid));
		for(int i =0; i < userid.length; i++) {
	         memberDao.delete(userid[i]);
	         
	      }
	      return "member/memberlist";   
	   }
}
