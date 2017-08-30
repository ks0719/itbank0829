package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DataController {

@RequestMapping("data/edit")
public String edit() {
	
	return "data/edit";
}
@RequestMapping("data/exit")
public String exit() {
	
	return "data/exit";
}
@RequestMapping("data/maininfo")
public String maininfo() {
	
	return "data/maininfo";
}
@RequestMapping("data/note")
public String note() {
	
	return "data/note";
}
@RequestMapping("data/pay")
public String pay() {
	
	return "data/pay";
}
@RequestMapping("data/point")
public String point() {
	
	return "data/point";
}
}