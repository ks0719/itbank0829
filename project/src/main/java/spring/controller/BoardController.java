package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	
	@RequestMapping("/board/free")
	public String free() {
		return "board/free";
	}
	
	@RequestMapping("/board/info")
	public String info() {
		return "board/info";
	}
	
	@RequestMapping("/board/qna")
	public String qna() {
		return "board/qna";
	}
	
	@RequestMapping("/board/require")
	public String require() {
		return "board/require";
	}
	
	@RequestMapping("/board/store")
	public String store() {
		return "board/store";
	}
	
}