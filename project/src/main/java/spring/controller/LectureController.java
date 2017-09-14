package spring.controller;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.db.lecture.LectureDao;
import spring.db.lecture.LectureInfo;
import spring.db.member.Member;
import spring.db.member.MemberDao;
import spring.db.mylecture.MyLecture;
import spring.db.mylecture.MyLectureDao;

@Controller
@RequestMapping("/lecture")
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

	@RequestMapping("/teacher")
	public String teacher() {
		
		return "lecture/teacher";
	}
	
	@RequestMapping("/assess")
	public String assess() {
		
		return "lecture/assess";
	}
	
	@RequestMapping("/class")
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
		
		//회원이 등록한 강의인지 아닌지 확인 해야함
		String nick = getNick(req);
		
		MyLecture lecture = myLectureDao.select(noI,nick);
		boolean paid;
		try {
			String state = lecture.getState();
			paid = (state.equals("결제 완료")||state.equals("수료"))?true:false;
		}catch(Exception e) {
			paid = false;
		}
		
		m.addAttribute("paid", paid);
		
		return "lecture/class";
	}
	
	@RequestMapping(value="/wish", produces = "application/text; charset=utf8")
	@ResponseBody
	public String wish(HttpServletRequest req, int no, Model m) throws Exception {
		LectureInfo info = lectureDao.showOne(no);
		String nick = getNick(req);
		
		boolean result = myLectureDao.wish(nick, info);
		log.debug("result : " + result);
		
		String res = "";
		if (result) res = "찜하기가 완료되었습니다.";
		else res = "이미 찜이 되어있거나 수강중인 강의 입니다.";
		
		log.debug("res : " + res);
		
		return res;
	}
	
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(value="/req",method=RequestMethod.GET)
	public String reqGet(HttpServletRequest req, Model m) throws Exception {
		//강의번호(no)를 받아서 db에 접속한 뒤 정보를 빼와야함
		int no;
		try{
			no = Integer.parseInt(req.getParameter("no"));
		}catch(Exception e) {
			throw new Exception("404");
		}
		LectureInfo lecture = lectureDao.showOne(no);
		m.addAttribute("lecture", lecture);
		
		//현재 로그인한 회원의 보유 포인트를 가져와야함
		String nick = getNick(req);
		Member member = memberDao.select(nick);
		
		m.addAttribute("mileage",member.getMileage());
		
		return "lecture/req";
	}
	
	@RequestMapping(value="/req",method=RequestMethod.POST)
	public String reqPost(HttpServletRequest req) throws Exception {
		//[1] 회원 닉네임을 가져온다
		String nick = getNick(req);
		
		//[2] 강의 번호를 가져와 db에서 강의 정보를 꺼내온다
		int no = Integer.parseInt(req.getParameter("no"));
		LectureInfo lecture = lectureDao.showOne(no);
		
		boolean result = myLectureDao.insert(nick, lecture, "");
		if(!result) throw new Exception("LectureController-reqPost에서 오류");
		
		//[3] 회원 마일리지를 차감한다
		boolean result2= memberDao.update("mileage", req.getParameter("mileage"));
		if(!result2) throw new Exception("LectureController-reqPost에서 오류");
		
		return "redirect:/lecture/class?no="+no+"&page="+req.getParameter("page");
	}
	
	@RequestMapping("/study")
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
		int start = boardSize * (pageNo-1) +1;
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