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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.db.lecture.LectureDao;
import spring.db.lecture.LectureInfo;
import spring.db.member.Member;
import spring.db.member.MemberDao;
import spring.db.mylecture.MyLecture;
import spring.db.mylecture.MyLectureDao;
import spring.db.teacher.Assess;
import spring.db.teacher.Qna;
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
	
	private int getTeacherNo(String nick) {
		if (nick == "") return 0;
		return memberDao.memberNo(nick);
	}
//	
//	private boolean isTeacher(HttpServletRequest req) throws Exception {
//		String nick = getNick(req);
//		if (nick == "") return false;
//		
//		int memberNo = getTeacherNo(nick);
//		int no = Integer.parseInt(req.getParameter("no"));
//		
//		boolean result = teacherDao.isRight(no, memberNo);
//		return result;
//	}
	
	@RequestMapping(value="/apply", method=RequestMethod.POST)
	public String apply(MultipartHttpServletRequest mRequest) throws Exception {
		MultipartFile file = mRequest.getFile("file");
		if (!file.isEmpty()) {
			String savePath = mRequest.getServletContext().getRealPath("/resource/file");
	
			String[] extension = file.getContentType().split("/");
			String filename = getNick(mRequest) + "." + extension[extension.length - 1];
			File target = new File(savePath, filename);
			if(!target.exists()) target.mkdirs();
			file.transferTo(target);
		}

//		teacherDao.apply(new Teacher(mRequest));
		
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
		if (!file.isEmpty()) {
			String savePath = mRequest.getServletContext().getRealPath("/resource/file");
		
			
		
			String[] extension = file.getContentType().split("/");
			String filename = "lecturer/" + getNick(mRequest) + "." + extension[extension.length - 1];
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
		int teacherNo = getTeacherNo(nick);
		
		m.addAttribute("teacherNo", teacherNo);
		
		return "teacher/resister";
	}
	
	@RequestMapping(value="/resister", method=RequestMethod.POST)
	public String resister(MultipartHttpServletRequest mRequest) throws Exception {
		MultipartFile file = mRequest.getFile("file");
		String savePath = mRequest.getServletContext().getRealPath("/resource/file");
		
		int no = lectureDao.insert(new LectureInfo(mRequest));
		
		if (!file.isEmpty()) {
			String[] extension = file.getContentType().split("/");
			String filename = "lecture/" + no + "." + extension[extension.length - 1];
			File target = new File(savePath, filename);
			if(!target.exists()) target.mkdirs();
			file.transferTo(target);
		}
		
		return "teacher/teacherMain";
	}
	
	@RequestMapping("/myLectures")
	public String lectures(HttpServletRequest req, Model m) throws Exception {
		String where = req.getParameter("where");
		int page;
		try {
			page = Integer.parseInt(req.getParameter("page"));
		} catch (Exception e) {
			page = 1;
		}
		
		String nick = getNick(req);
		String search = req.getParameter("search");
		String key = req.getParameter("key");
		
		int listCount = lectureDao.teacherCount(nick, search, key);
	
		int boardSize = 10;
		int start = boardSize * page - 9;
		int end = start + boardSize -1;
		if (end > listCount) end = listCount;

		List<LectureInfo> list = lectureDao.teacherList(nick, search, key, start, end);
		
		int blockSize = 10;
		int blockTotal = (listCount + boardSize - 1) / boardSize;
		int startBlock = (page - 1) / blockSize * blockSize + 1;
		int endBlock = startBlock + blockSize - 1;
		if (endBlock > blockTotal) endBlock = blockTotal;
		
		String url = "myLectures?where=" + where;
		if (search != null && key != null) {
			url += "&search=" + search + "&key=" + key + "&";
			m.addAttribute("search", search);
			m.addAttribute("key", key);
		}
		
		m.addAttribute("list", list);
		m.addAttribute("page", page);
		m.addAttribute("search", search);
		m.addAttribute("key", key);
		m.addAttribute("start", start);
		m.addAttribute("end", end);
		m.addAttribute("startBlock", startBlock);
		m.addAttribute("endBlock", endBlock);
		m.addAttribute("url", url);
		m.addAttribute("where", where);
		
		return "teacher/myLectures";
	}
	
	@RequestMapping("/myLecture")
	public String myLecture(HttpServletRequest req, Model m) throws Exception {
		String where = req.getParameter("where");
		String page = req.getParameter("page");
		String search = req.getParameter("search");
		String key = req.getParameter("key");
		int no;
		try {
			no = Integer.parseInt(req.getParameter("no"));
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		LectureInfo info = lectureDao.teacherShowOne(no, getNick(req));
		
		m.addAttribute("mylecture", info);

		String url = "where=" + where + "&page=" + page;
		if (search != null && key != null) {
			url += "&search=" + search + "&key=" + key;
		}
		m.addAttribute("url", url);
		
		return "teacher/myLecture";
	}
	
	@RequestMapping("/lectureEdit")
	public String lectureEdit(HttpServletRequest request, Model m) throws Exception {
		int no;
		try {
			no = Integer.parseInt(request.getParameter("no"));
		} catch(Exception e) {
			throw new Exception("404");
		}

		LectureInfo info = lectureDao.teacherShowOne(no, getNick(request));
		
		m.addAttribute("mylecture", info);

		String where = request.getParameter("where");
		String page = request.getParameter("page");
		String search = request.getParameter("type");
		String key = request.getParameter("key");

		if (search != "" && key != null) {
			m.addAttribute("search", search);
			m.addAttribute("key", key);
		}
		String url = "where=" + where + "&page=" + page;
		if (search != null && key != null) {
			url += "&search=" + search + "&key=" + key;
		}
		m.addAttribute("url", url);
		
		return "teacher/lectureEdit";
	}
	
	@RequestMapping(value="/lectureEdit", method=RequestMethod.POST)
	public String lectureEdit(MultipartHttpServletRequest mRequest, Model m) throws Exception {
		int no = Integer.parseInt(mRequest.getParameter("no"));
		MultipartFile file = mRequest.getFile("file");
		String savePath = mRequest.getServletContext().getRealPath("/resource/file");
		
		lectureDao.edit(new LectureInfo(mRequest));
		
		if (!file.isEmpty()) {
			String[] extension = file.getContentType().split("/");
			String filename = "lecture/" + no + "." + extension[extension.length - 1];
			File target = new File(savePath, filename);
			if(!target.exists()) target.mkdirs();
			file.transferTo(target);
		}

		m.addAttribute("no", no);
		m.addAttribute("where", mRequest.getParameter("whrer"));
		m.addAttribute("page", mRequest.getParameter("page"));
		String search = mRequest.getParameter("search");
		String key = mRequest.getParameter("key");
		if (search != "" && key != null) {
			m.addAttribute("search", search);
			m.addAttribute("key", key);
		}

		return "redirect:/teacher/myLecture";
	}
	
	@RequestMapping("/students")
	public String students(HttpServletRequest request, Model m) throws Exception {
		int no;
		try {
			no = Integer.parseInt(request.getParameter("no"));
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		List<MyLecture> list = mylectureDao.getStudents(no);
		
		// 회원정보 가져와야 함
		List<Member> mList = memberDao.getInfo(list);
		
		m.addAttribute("list", mList);

		String where = request.getParameter("where");
		String page = request.getParameter("page");
		String search = request.getParameter("type");
		String key = request.getParameter("key");

		if (search != "" && key != null) {
			m.addAttribute("search", search);
			m.addAttribute("key", key);
		}
		String url = "?no=" + no + "&where=" + where + "&page=" + page;
		if (search != null && key != null) {
			url += "&search=" + search + "&key=" + key;
		}
		m.addAttribute("url", url);
		
		return "teacher/students";
	}
	
	@RequestMapping("/qnaView")
	public String qnaView(HttpServletRequest request, Model m) throws Exception {
		int no;
		try {
			no = Integer.parseInt(request.getParameter("no"));
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		List<Qna> list = teacherDao.qnaList(no);
		
		m.addAttribute("list", list);

		String where = request.getParameter("where");
		String page = request.getParameter("page");
		String search = request.getParameter("type");
		String key = request.getParameter("key");

		if (search != "" && key != null) {
			m.addAttribute("search", search);
			m.addAttribute("key", key);
		}
		String url = "?no=" + no + "&where=" + where + "&page=" + page;
		if (search != null && key != null) {
			url += "&search=" + search + "&key=" + key;
		}
		m.addAttribute("url", url);
		
		return "teacher/qnaView";
	}
	
	@RequestMapping("/assessView")
	public String assessView(HttpServletRequest request, Model m) throws Exception {
		int no;
		try {
			no = Integer.parseInt(request.getParameter("no"));
		} catch(Exception e) {
			throw new Exception("404");
		}
		
		List<Assess> list = teacherDao.assessList(no);
		
		m.addAttribute("list", list);

		String where = request.getParameter("where");
		String page = request.getParameter("page");
		String search = request.getParameter("type");
		String key = request.getParameter("key");

		if (search != "" && key != null) {
			m.addAttribute("search", search);
			m.addAttribute("key", key);
		}
		String url = "?no=" + no + "&where=" + where + "&page=" + page;
		if (search != null && key != null) {
			url += "&search=" + search + "&key=" + key;
		}
		m.addAttribute("url", url);
		
		return "teacher/assessView";
	}
	
	@RequestMapping("/withdrow")
	public String withdrow(HttpServletRequest req, Model m) throws Exception {
		int point = memberDao.mypoint(getNick(req));
		
		m.addAttribute("point", point);
		
		return "teacher/withdrow";
	}
	
	
	@RequestMapping("/applynot")
	public String notapply(HttpServletRequest request, Model model) {
		
		List<Teacher>list=teacherDao.list();
		model.addAttribute("list", list);
		
		return "teacher/applynot";
	}
	
	@RequestMapping(value="/checkapply", method=RequestMethod.POST)
	public String apply(@RequestParam String teacherid) {
		
		teacherDao.stateedit(teacherid);
		
		return "teacher/applynot";
		
	}
}
