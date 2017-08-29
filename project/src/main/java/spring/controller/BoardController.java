package spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class BoardController {
	
	@RequestMapping("/free")
	public String free() {
		return "board/free";
	}
	
	@RequestMapping("/info")
	public String info() {
		return "board/info";
	}
	
	@RequestMapping("/qna")
	public String qna() {
		return "board/qna";
	}
	
	@RequestMapping("require")
	public String require() {
		return "board/require";
	}
	
	@RequestMapping("store")
	public String store() {
		return "board/store";
	}
	
}