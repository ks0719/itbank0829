package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {
	@RequestMapping("/consumer/b2c")
	public String inquire() {
		return "consumer/b2c";
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