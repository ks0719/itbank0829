package spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.db.B2CDao;
import spring.db.B2CDto;

@Controller
public class CustomerController {
	@Autowired
	private B2CDao dao;
	private B2CDto dto;
	@RequestMapping("/consumer/b2c")
	public String b2c() {
		return "consumer/b2c";
	}
	@RequestMapping(value="/consumer/b2c",method=RequestMethod.POST)
	public String b2cpost(HttpServletRequest request, Model model) {
	dto=new B2CDto(request);
	dao.insert(dto);
	List<B2CDto> list;
	list=dao.list("aaaa");
	model.addAttribute("list", list);
	return "redirect:/consumer/b2clist";
	}
	@RequestMapping(value="/consumer/b2clist")
	public String b2clist(Model model) {
		List<B2CDto> list;
		list=dao.list("aaaa");
		model.addAttribute("list", list);
		return "consumer/b2clist";
	}
	
	
	@RequestMapping("/consumer/basic")
	public String QnA() {
		return "consumer/basic";
	}
	
	@RequestMapping("/consumer/rule")
	public String rule() {
		return "consumer/rule";
	}
}