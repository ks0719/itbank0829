package spring.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

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
		boolean result = memberDao.idcheck(id);
		if(!result)  return "member/sign";
		else return null;
	}
	
	@RequestMapping(value="/member/nicknamecheck", method = RequestMethod.POST)
	   public String nickname(@RequestParam String nickname) throws Exception {
		boolean result = memberDao.nickcheck(nickname);
	      if(!result)  return "member/sign";
	      else return null;
	   }
	
	
	@RequestMapping("/member/login")
	public String login() {
		
		return "member/login";
	}

}
