package spring.controller;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.db.B2CDao;
import spring.db.B2CDto;
import spring.db.RB2CDto;

@Controller
public class CustomerController {
	private Logger log=LoggerFactory.getLogger(getClass());
	private String getNick(HttpServletRequest req) throws Exception {
		Cookie[] c=req.getCookies();
		if(c != null){
	        for(int i=0; i < c.length; i++){
	            Cookie ck = c[i] ;
	            // 저장된 쿠키 이름을 가져온다
	            String cName = ck.getName();
	            // 쿠키값을 가져온다
	            String cValue =  URLDecoder.decode((ck.getValue()),"utf-8");
//	            log.debug("쿠키값  :"+cValue);
	            if(cName.equals("mynick")) {
	            	return cValue;
	            }
	        }
		}
		throw new Exception("404");
	}
	@Autowired
	private B2CDao dao;
	private B2CDto dto;
	private RB2CDto rdto;
	@RequestMapping("/consumer/b2c")
	public String b2c(HttpServletRequest request,Model model) throws Exception {
		String nick=getNick(request);
		model.addAttribute("nick", nick);
		return "consumer/b2c";
	}
	@RequestMapping(value="/consumer/b2c",method=RequestMethod.POST)
	public String b2cpost(MultipartHttpServletRequest mRequest, Model model) throws Exception {
	String nick=getNick(mRequest);
	dto=new B2CDto(mRequest);
	log.debug("dto="+dto.toString());
	dao.insert(dto);
	List<B2CDto> list;
	list=dao.list(nick);
	model.addAttribute("list", list);
	return "redirect:/consumer/b2clist";
	}
	@RequestMapping(value="/consumer/b2clist")
	public String b2clist(Model model,HttpServletRequest request) throws Exception {
		List<B2CDto> list;
		String nick=getNick(request);
		list=dao.list(nick);
		model.addAttribute("list", list);
		return "consumer/b2clist";
	}
	@RequestMapping("/consumer/detail")
	public String detail(@RequestParam(required=true) int no,Model model) {
		dto=dao.detail(no);
		rdto=dao.rdetail(no);
		if(rdto!=null) {
			
			model.addAttribute("rdto", rdto);
		}
		model.addAttribute("dto", dto);
		return "consumer/detail";
	}
	
	@RequestMapping("/consumer/basic")
	public String QnA() {
		return "consumer/basic";
	}
	
	@RequestMapping("/consumer/rule")
	public String rule() {
		return "consumer/rule";
	}
	@RequestMapping("/consumer/reply")
	public String reply(@RequestParam(required=true) int no,Model model,HttpServletRequest request) throws Exception {
		String nick=getNick(request);
		dto=dao.detail(no);
		model.addAttribute("nick", nick);
		model.addAttribute("dto", dto);
		return "consumer/reply";
	}
	@RequestMapping(value="/consumer/reply",method=RequestMethod.POST)
	public String replypost(@RequestParam(required=true) int no,Model model,HttpServletRequest request) throws Exception {
		String nick=getNick(request);
		rdto= new RB2CDto(request);
		rdto.setNick(nick);
		dao.rinsert(rdto);
		log.debug("rdto"+rdto);
		return "consumer/detail";
	}
}