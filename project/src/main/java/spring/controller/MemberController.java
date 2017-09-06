package spring.controller;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import spring.db.member.Member;
import spring.db.member.MemberDao;
import spring.db.mylecture.MyLectureDao;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(value="/member/sign",method=RequestMethod.GET)
	public String signGet() {
		
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
		System.out.println(id);
		boolean result = memberDao.idcheck(id);
	      
	      if(result) {
	         return "member/success";
	      }else {
	         throw new Exception("중복된 아이디가 있습니다.");
	      }      
	   }
	
	
	@RequestMapping("/member/login")
	public String login() {
		
		return "member/login";
	}

}
