package spring.controller;

import java.io.File;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.db.lecture.LectureDao;
import spring.db.lecture.LectureInfo;
import spring.db.member.MemberDao;
import spring.db.mylecture.MyLectureDao;
import spring.db.teacher.Teacher;
import spring.db.teacher.TeacherDao;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TeacherDao teacherDao;
	
	@Autowired
	private LectureDao lectureDao;
	
	@Autowired
	private MyLectureDao mylectureDao;
	
	@Autowired
	private MemberDao memberDao;
	
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
	
	@RequestMapping(value="/apply", method=RequestMethod.POST)
	public String apply(MultipartHttpServletRequest mRequest) throws Exception {
		MultipartFile file = mRequest.getFile("file");
		String savePath = mRequest.getServletContext().getRealPath("/resource/file");

		String[] extension = file.getContentType().split("/");
		teacherDao.apply(new Teacher(mRequest));
		String filename = getNick(mRequest) + "." + extension[extension.length - 1];
		File target = new File(savePath, filename);
		if(!target.exists()) target.mkdirs();
		file.transferTo(target);
		
		return "data/maininfo";
	}
	
	@RequestMapping("/apply")
	public String apply(HttpServletRequest request, Model m) throws Exception {
		String nick = getNick(request);
		
		m.addAttribute("nick", nick);
		
		return "teacher/apply";
	}
	
	@RequestMapping("/applycheck")
	@ResponseBody
	public String applycheck(HttpServletRequest request, Model m) throws Exception {
		String nick = getNick(request);
		
		boolean result = teacherDao.applycheck(nick);
		
		return String.valueOf(result);
	}
	
	@RequestMapping("/lecturer")
	public String lecturer(String page, String standard, HttpServletRequest request, Model m) throws Exception {
		String type = request.getParameter("type");
		String key = request.getParameter("key");
		
		int pageNo;
		try {
			pageNo = Integer.parseInt(page);
		} catch(Exception e) {
			pageNo = 1;
		}
		if (pageNo <= 0 ) pageNo = 1;
		
		String originStandard = standard;
		String sub1 = "", sub2 = "";
		if (standard != null) {
			switch (standard) {
			case "sort" :
				log.debug("분류");
				standard = "sort";
				sub1 = "grade desc";
				sub2 = "count desc";
				break;
			case "count" :
				log.debug("횟수");
				standard = "count desc";
				sub1 = "grade desc";
				sub2 = "sort";
				break;
			case "grade" :
				standard = "grade desc";
				log.debug("평점");
				sub1 = "count desc";
				sub2 = "sort";
				break;
			default :
				standard = "reg";
			}
		}
		log.debug("st : " + standard);
		log.debug("s1 : " + sub1);
		log.debug("s2 : " + sub2);

		int listCount = teacherDao.count(type, key);
		
		int boardSize = 10;
		int start = boardSize * pageNo - 9;
		int end = start + boardSize -1;
		if (end > listCount) end = listCount;
		
		List<Teacher> list = teacherDao.list(standard, sub1, sub2, type, key, start, end);
		
		int blockSize = 10;
		int blockTotal = (listCount + boardSize - 1) / boardSize;
		int startBlock = (pageNo - 1) / blockSize * blockSize + 1;
		int endBlock = startBlock + blockSize - 1;
		if (endBlock > blockTotal) endBlock = blockTotal;
		
		String url = "lecturer?";
		if (type != null && key != null) {
			url += "type=" + type + "&key=" + key + "&";
			m.addAttribute("type", type);
			m.addAttribute("key", key);
		}
		if (standard != null) {
			url += "standard=" + originStandard + "&";
		}
		log.debug("url : " + url);
		
		m.addAttribute("list", list);
		m.addAttribute("page", pageNo);
		m.addAttribute("type", type);
		m.addAttribute("key", key);
		m.addAttribute("start", start);
		m.addAttribute("end", end);
		m.addAttribute("startBlock", startBlock);
		m.addAttribute("endBlock", endBlock);
		m.addAttribute("url", url);
		
		return "teacher/lecturer";
	}
	
	@RequestMapping("/lecturerInfo")
	public String lecturerInfo(HttpServletRequest req, Model m) throws Exception {
		String name = req.getParameter("name");
		int pageNo;
		try {
			pageNo = Integer.parseInt(req.getParameter("page"));
		} catch(Exception e) {
			pageNo = 1;
		}
		
		Teacher info = teacherDao.showOne(name);
		
		String url = "?page=" + pageNo;
		if (req.getParameter("type") != null && req.getParameter("key") != null) url += "&type=" + req.getParameter("type") + "&key=" + req.getParameter("key");
		
		m.addAttribute("info", info);
		m.addAttribute("name", name);
		m.addAttribute("url", url);
		
		return "teacher/lecturerInfo";
	}
	
	@RequestMapping("/teacherMain")
	public String toMain() {
		return "teacher/teacherMain";
	}
	
	@RequestMapping("/profile")
	public String profile(HttpServletRequest req, Model m) throws Exception {
		String name = getNick(req);
		Teacher info = teacherDao.showOne(name);
		
		m.addAttribute("profile", info);
		
		return "teacher/profile";
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.POST)
	public String profile(MultipartHttpServletRequest mRequest) throws Exception {
		MultipartFile file = mRequest.getFile("file");
		String savePath = mRequest.getServletContext().getRealPath("/resource/file");
		
		
		
		if (!file.isEmpty()) {
			String[] extension = file.getContentType().split("/");
			String filename = getNick(mRequest) + "." + extension[extension.length - 1];
			File target = new File(savePath, filename);
			if(!target.exists()) target.mkdirs();
			file.transferTo(target);
		}
		teacherDao.edit(new Teacher(mRequest));
		
		return "teacher/teacherMain";
	}
	
	@RequestMapping("/resister")
	public String resister(HttpServletRequest req, Model m) throws Exception {
		String nick = getNick(req);
		
		m.addAttribute("nick", nick);
		
		return "teacher/resister";
	}
	
	@RequestMapping(value="/resister", method=RequestMethod.POST)
	public String resister(MultipartHttpServletRequest mRequest) {
		lectureDao.insert(new LectureInfo(mRequest));
		
		return "teacher/teacherMain";
	}
	
	@RequestMapping("/myLectures")
	public String lectures(HttpServletRequest req, Model m) throws Exception {
		List<LectureInfo> list = lectureDao.teacherList(getNick(req));
		
		m.addAttribute("list", list);
		
		return "teacher/lectures";
	}
	
	@RequestMapping("/myLecture")
	public String myLecture(Model m) {
		
		
		return "teacher/myLecture";
	}
	
	@RequestMapping("/students")
	public String students() {
//		List<>
		
		return "teacher/students";
	}
	
	@RequestMapping("/qna")
	public String qna() {
		return "teacher/qna";
	}
	
	@RequestMapping("/assessView")
	public String assessView() {
		return "teacher/assessView";
	}
	
	@RequestMapping("/withdrow")
	public String withdrow(HttpServletRequest req, Model m) throws Exception {
		int point = memberDao.mypoint(getNick(req));
		
		m.addAttribute("point", point);
		
		return "teacher/withdrow";
	}

}
