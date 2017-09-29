package spring.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.CookieGenerator;

import spring.db.member.Member;
import spring.db.member.MemberDao;

@Controller
public class MemberController {
	private static final String serveraddr="http://localhost:8080/project/WEB-INF/view";
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
	public String loginpost(HttpServletRequest request,Model model,HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String nick = null;
		String power=null;
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
		power=memberDao.powercheck(id, encodepw);
		//log.debug("nick="+nick);
		//log.debug("state="+state);
		
		if(nick!=null) {
			CookieGenerator cookie=new CookieGenerator();
			cookie.setCookieName("mynick");
			cookie.setCookiePath("/");
			cookie.setCookieMaxAge(-1);
			cookie.addCookie(response, URLEncoder.encode(nick, "utf-8"));
			
			 session.setAttribute("member", power);
			 
		return "redirect:"+url;
		}
		else {
			return "member/fail";
		}
	}
	
	
	@RequestMapping(value="/member/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
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
					session.setAttribute("member", null);
					session.setAttribute("mynick", null);
				}
			}
		}
		return "redirect:/";
	}
	
	
	private boolean isTeacher(String nick) {
		if (nick == "") return false;
		return memberDao.power(nick).equals("강사");
	}
	
	@RequestMapping(value="member/deletemember", method=RequestMethod.GET)
	public String deleteGet(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute("deletemember", "deletemember");
		boolean isTeacher = isTeacher(getNick(request));
		model.addAttribute("isTeacher", isTeacher);
		return "member/deletemember";
	}
	
	@RequestMapping(value="/member/deletemember", method = RequestMethod.POST)
	public void deletePost( HttpServletRequest req) throws Exception {
		
		String nick=getNick(req);
		String pw=req.getParameter("pw");
		String spw=memberDao.mypwnick(nick);
		boolean same=passwordEncoder.matches(pw, spw);
		boolean result=memberDao.delete(nick,spw);
		if(result==same) {
		}else {
			throw new Exception();
		}
	}
	
	@RequestMapping("/member/memberlist")
	public String list(HttpServletRequest request, Model model, HttpSession session) throws Exception {
		String type = request.getParameter("type");
		String key = request.getParameter("key");
		System.out.println("type~~~~"+type);
		System.out.println("key~~~~"+key);
		
		String name=(String) session.getAttribute("member");
		log.debug("권한 ? "+name);
		if(name.equals("관리자")) {
		
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
		}else {
			throw new Exception("일반 접근 제한");
		}
	}
	
	@RequestMapping("/member/memberdetail")
	public String detail(HttpServletRequest req, String no, Model m, HttpSession session) throws Exception {
	
		String name=(String) session.getAttribute("member");
		if(name.equals("관리자")) {
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
		}else {
			throw new Exception("일반 접근 제한");
		}
	}
	
	
	@RequestMapping(value="/member/checkBox", method = RequestMethod.POST)
	public String unsignPost(@RequestParam String userid) {
//		log.debug("뭐 넘어옴?:"+Arrays.toString(userid));
//		System.out.println(userid);
		memberDao.delete(userid);
	      return "member/memberlist";   
	   }
	
	@RequestMapping(value="/chatadd",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String chatadd(String mynick,String getnick) throws Exception {
		log.debug("넘어온값은 ? :"+mynick+"/"+getnick);
		List<String> list=new ArrayList<>();
		boolean result=memberDao.isfriend(getnick);
		if(result) {
			list=memberDao.myfriendlist(mynick);
			log.debug("친구 목록 : "+list.toString());
		if(list!=null) {
			for(String i:list) {
				if(i.equals(getnick)) {
					return "해당 회원이 친구 목록에 존재합니다.";
				}
			}
		}
		memberDao.myfriend(mynick, getnick);
		return "친구가 추가되었습니다.";
		
		}
			log.debug("추가:닉네임이 없어?");
			return "해당 닉네임을 가진 회원이 없습니다.";
		
	}
	@RequestMapping(value="/chatdel",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String chatdel(String mynick,String getnick) {
		log.debug("넘어온 값은 ? : "+mynick+"/"+getnick);
		List<String> list=new ArrayList<>();
		boolean result=memberDao.isfriend(getnick);
		if(result) {
			list=memberDao.myfriendlist(mynick);
			log.debug("친구 목록 : "+list.toString());
			if(list!=null) {
				for(int i=0;i<list.size();i++) {
					if(list.get(i).equals(getnick)) {
						log.debug("삭제합니다.");
						log.debug("삭제전 목록 : "+list.toString());
						list.remove(i);
						log.debug("삭제후 목록 : "+list.toString());
						String newlist=list.toString().replaceAll(",", "/").replaceAll("\\[", "").replaceAll("\\]", "").trim().replaceAll(" ", "");
						log.debug("새 친구목록 : "+newlist);
						memberDao.friendrenew(mynick, newlist);
						return "해당 친구가 삭제되었습니다.";
					}
				}
			}
		}
		log.debug("삭제:해당 닉네임이 없어?");
		return "해당 닉네임을 가진 회원이 없습니다.";
	}
	
	
	
	
	@RequestMapping(value="/member/findid", method=RequestMethod.GET)
	public String findidGET() {
		
		return "member/findid";
	}
	
	@RequestMapping(value="/member/findid",  method=RequestMethod.POST)
	public String findidPOST(HttpServletRequest request, Model model) {
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		
		String findidcheck=memberDao.findid(name, phone);
		
		if(findidcheck!=null) {
			
			model.addAttribute("findidcheck", findidcheck);
			return "member/findidresult"; 
		}
		
		return "아이디 찾기 싫어 ?";
	}
	
	@RequestMapping(value="/member/findpw", method=RequestMethod.GET)
	public String findpwGET() {
		
		return "member/findpw";
	}
	
	@RequestMapping(value="/member/findpw", method=RequestMethod.POST)
	public String findpwPOST(HttpServletRequest request, Model model) {
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		
		
		String findpwcheck=memberDao.findpw(id, name, phone);
		
		if(findpwcheck!=null) {
			model.addAttribute("id",id);
			return "member/findpwresult";
		}
		
		return"member/findpw";
	}
	
	
	@RequestMapping(value="/member/findpwresult", method=RequestMethod.GET)
	public String findpwresultGET() {
		
		return "member/findpwresult";
	}
	
	@RequestMapping(value="/member/findpwresult", method=RequestMethod.POST)
	public String findpwresultPOST(HttpServletRequest request, Model model) throws Exception {
		String id=request.getParameter("id");
		
		if(id=="") throw new Exception("일반 접근 제한!");
		if(id==null) throw new Exception("일반 접근 제한!");
		
		String findnewpw=request.getParameter("findnewpw");
			findnewpw=passwordEncoder.encode(findnewpw);
			boolean state=memberDao.changenewpw(id, findnewpw);
			
			if(state) return "redirect:/";
			else return "member/findpwresult";
	}
	
	@RequestMapping(value="/member/idFind", method=RequestMethod.POST)
	public String idFind(@RequestParam String name, @RequestParam String phone) {
		String result=memberDao.findid(name, phone);
		if(result!=null) return "member/findid";
		else return "member/findidresult";
	}
	
	@RequestMapping(value="/member/passwordFind", method=RequestMethod.POST)
	public String findpwchange(@RequestParam String id, @RequestParam String name, @RequestParam String phone) {
		
		String result=memberDao.findpw(id, name, phone);
		
		if(result!=null) return "member/findpw";
		else return "member/findpwresult";
	}
}
