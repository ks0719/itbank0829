package spring.controller;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.db.lecture.LectureDao;
import spring.db.lecture.LectureInfo;
import spring.db.mylecture.MyLectureDao;

@Controller
public class LectureController {
	
	private String getNick(HttpServletRequest req) throws Exception {
		Cookie[] c=req.getCookies();
		if(c != null){
	        for(int i=0; i < c.length; i++){
	            Cookie ck = c[i] ;
	            // 저장된 쿠키 이름을 가져온다
	            String cName = ck.getName();
	            // 쿠키값을 가져온다
	            String cValue =  URLDecoder.decode(ck.getValue(),"utf-8");
//	            log.debug("쿠키값  :"+cValue);
	            if(cName.equals("mynick")) {
	            	return cValue;
	            }
	        }
		}
		return "";
	}
	
	private Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LectureDao lectureDao;
	
	@Autowired
	private MyLectureDao myLectureDao;

	@RequestMapping("/lecture/teacher")
	public String teacher() {
		
		return "lecture/teacher";
	}
	
	@RequestMapping("/lecture/assess")
	public String assess() {
		
		return "lecture/assess";
	}
	
	@RequestMapping("/lecture/class")
	public String lesson(HttpServletRequest req, Model m) throws Exception {
		int noI, pageNo;
		try {
			noI = Integer.parseInt(req.getParameter("no"));
		} catch(Exception e) {
			throw new Exception("404");
		}
		try {
			pageNo = Integer.parseInt(req.getParameter("page"));
		} catch(Exception e) {
			pageNo = 1;
		}
		
		LectureInfo info = lectureDao.showOne(noI);
		
		String url = "?page=" + pageNo;
		if (req.getParameter("type") != null && req.getParameter("key") != null) url += "&type=" + req.getParameter("type") + "&key=" + req.getParameter("key");
		
		m.addAttribute("info", info);
		m.addAttribute("no", noI);
		m.addAttribute("url", url);
		
		return "lecture/class";
	}
	
	@RequestMapping("/lecture/wish")
	public String wish(HttpServletRequest req) throws Exception {
		int noI, pageNo;
		try {
			noI = Integer.parseInt(req.getParameter("no"));
		} catch(Exception e) {
			throw new Exception("404");
		}
		try {
			pageNo = Integer.parseInt(req.getParameter("page"));
		} catch(Exception e) {
			pageNo = 1;
		}
		
		LectureInfo info = lectureDao.showOne(noI);
		String nick = getNick(req);
		
		int result = myLectureDao.wish(nick, noI, info);
		if (result == 1) JOptionPane.showMessageDialog(null, "찜하기가 완료되었습니다.");
		else JOptionPane.showMessageDialog(null, "이미 찜이 되어있거나 할 수 없습니다.");
		
		return "lecture/class";
	}
	
	@RequestMapping("/lecture/req")
	public String req() {
		
		return "lecture/req";
	}
	
	@RequestMapping("/lecture/study")
	public String study(HttpServletRequest request, Model m) {
		String type = request.getParameter("type");
		String key = request.getParameter("key");
		int pageNo;
		try {
			pageNo = Integer.parseInt(request.getParameter("page"));
		} catch(Exception e) {
			pageNo = 1;
		}
		if (pageNo <= 0 ) pageNo = 1;
		
		int listCount = lectureDao.count(type, key);
		log.debug(String.valueOf(listCount));
		
		int boardSize = 10;
		int start = boardSize * pageNo - 9;
		int end = start + boardSize -1;
		if (end > listCount) end = listCount;
		
		List<LectureInfo> list = lectureDao.list(type, key, start, end);
		
		int blockSize = 10;
		int blockTotal = (listCount + boardSize - 1) / boardSize;
		int startBlock = (pageNo - 1) / blockSize * blockSize + 1;
		int endBlock = startBlock + blockSize - 1;
		if (endBlock > blockTotal) endBlock = blockTotal;
		
		String url = "study?";
		if (type != null && key != null) {
			url += "type=" + type + "&key=" + key + "&";
			m.addAttribute("type", type);
			m.addAttribute("key", key);
		}
		
		m.addAttribute("list", list);
		m.addAttribute("page", pageNo);
		m.addAttribute("startBlock", startBlock);
		m.addAttribute("endBlock", endBlock);
		m.addAttribute("url", url);
		
		return "lecture/study";
	}
}