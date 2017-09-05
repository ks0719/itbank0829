package spring.controller;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
//		System.out.println(request.getParameter("id"));
//		System.out.println(request.getParameter("pw"));
//		System.out.println(request.getParameter("name"));
//		System.out.println(request.getParameter("nickname"));
//		System.out.println(request.getParameter("phone"));
//		System.out.println(request.getParameter("post"));
//		System.out.println(request.getParameter("addr1"));
//		System.out.println(request.getParameter("addr2"));
//		System.out.println(request.getParameter("sort"));
//		System.out.println(request.getParameter("mileage"));
//		System.out.println(request.getParameter("lev"));
//		System.out.println(request.getParameter("reg"));
		
		Member m=new Member(request);
		memberDao.insert(m);
		return "redirect:/";
		
	}
	
	@RequestMapping("/member/login")
	public String login() {
		
		return "member/login";
	}
}
