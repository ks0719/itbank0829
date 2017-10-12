package spring.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import spring.db.lecture.LectureVideo;
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
	
	private String videoSavePath;
	
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
	
	private boolean isTeacher(String nick) {
		if (nick == "") return false;
		return memberDao.power(nick).equals("강사");
	}
	
	private int getTeacherNo(String nick) {
		if (nick == "") return 0;
		return memberDao.memberNo(nick);
	}
	
	private void deleteFile(String savePath, String filename) {
		File f = new File(savePath);
		     
		String fileList[] = f.list(new FilenameFilter() {
		 
		    @Override
		    public boolean accept(File dir, String name) {
		        return name.startsWith(filename);
		    }
		 
		});
		
		if (fileList != null) {
		    File file;
			for (int i = 0; i < fileList.length; i++) {
				file = new File(savePath, fileList[i]);
				file.delete();
			}
		}
	}
	
	@RequestMapping(value="/apply", method=RequestMethod.POST)
	public String apply(MultipartHttpServletRequest mRequest, HttpServletRequest request) throws Exception {
		int memberNo = Integer.parseInt(mRequest.getParameter("teacherno"));
		
		MultipartFile file = mRequest.getFile("file");
		
		if (!file.isEmpty()) {
			String savePath = mRequest.getServletContext().getRealPath("/resource/file/lecturer");
	
			String[] extension = file.getContentType().split("/");
			String filename = getNick(mRequest) + "." + extension[extension.length - 1];
			File target = new File(savePath, filename);
			if(!target.exists()) target.mkdirs();
			file.transferTo(target);
		}

		teacherDao.apply(new Teacher(mRequest), memberNo);
		
		return "data/maininfo";
	}
	
	@RequestMapping("/apply")
	public String apply(HttpServletRequest request, Model m) throws Exception {
		String nick = getNick(request);
		
		m.addAttribute("teacherNo", getTeacherNo(nick));
		m.addAttribute("apply", "apply");
		
		return "teacher/apply";
	}
	
	@RequestMapping("/applycheck")
	@ResponseBody
	public String applycheck(HttpServletRequest request, Model m) throws Exception {
		String nick = getNick(request);
		
		boolean result = teacherDao.applycheck(getTeacherNo(nick));
		
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
		int start = boardSize * pageNo - (boardSize - 1);
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
		m.addAttribute("blockTotal", blockTotal);
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
		
		Teacher info = teacherDao.showOne(getTeacherNo(name));
		
		String url = "?page=" + pageNo;
		if (req.getParameter("type") != null && req.getParameter("key") != null) url += "&type=" + req.getParameter("type") + "&key=" + req.getParameter("key");
		
		m.addAttribute("info", info);
		m.addAttribute("name", name);
		m.addAttribute("url", url);
		
		return "teacher/lecturerInfo";
	}
	
	@RequestMapping("/profile")
	public String profile(HttpServletRequest req, Model m) throws Exception {
		if (isTeacher(getNick(req)) == false) throw new Exception("404");
		
		int teacherNo = getTeacherNo(getNick(req));
		Teacher info = teacherDao.showOne(teacherNo);
		
		m.addAttribute("profile", info);
		
		return "teacher/profile";
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.POST)
	public String profile(MultipartHttpServletRequest mRequest) throws Exception {
		MultipartFile file = mRequest.getFile("file");
		if (!file.isEmpty()) {
			String savePath = mRequest.getServletContext().getRealPath("/resource/file/lecturer");

			deleteFile(savePath, getNick(mRequest));
		
			String[] extension = file.getContentType().split("/");
			String filename = getNick(mRequest) + "." + extension[extension.length - 1];
			File target = new File(savePath, filename);
			if(!target.exists()) target.mkdirs();
			file.transferTo(target);
		}
		int teacherNo = getTeacherNo(getNick(mRequest));
		teacherDao.edit(new Teacher(mRequest), teacherNo);
		
		return "redirect:/teacher/profile";
	}
	
	@RequestMapping("/resister")
	public String resister(HttpServletRequest req, Model m) throws Exception {
		String nick = getNick(req);
		if (isTeacher(nick) == false) throw new Exception("404");
		
		int teacherNo = getTeacherNo(nick);
		
		m.addAttribute("teacherNo", teacherNo);
		
		return "teacher/resister";
	}
	
	@RequestMapping(value="/resister", method=RequestMethod.POST)
	public String resister(MultipartHttpServletRequest mRequest) throws Exception {
		MultipartFile file = mRequest.getFile("file");
		String savePath = mRequest.getServletContext().getRealPath("/resource/file/lecture");
		
		int no = lectureDao.insert(new LectureInfo(mRequest));
		
		if (!file.isEmpty()) {			
			String[] extension = file.getContentType().split("/");
			String filename = no + "." + extension[extension.length - 1];
			File target = new File(savePath, filename);
			if(!target.exists()) target.mkdirs();
			file.transferTo(target);
		}

		List<MultipartFile> list = mRequest.getFiles("video");
		savePath = mRequest.getServletContext().getRealPath("/resource/file/lectureVideo");
		int count = 1;
		for(MultipartFile f : list) {
			String[] extension = f.getContentType().split("/");
			String filename = no + "(" + count + ")." + extension[extension.length - 1];
			
			lectureDao.video(no, mRequest.getParameter("title") + count, filename, f.getOriginalFilename(), f.getContentType(), f.getSize());
			
			File target = new File(savePath, filename);
			if(!target.exists()) target.mkdirs();
			f.transferTo(target);
			count++;
		}
		
		//여기 페이지 전달 오류
		return "teacher/myLectures?where=myLecture&page=1";
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
		if (isTeacher(nick) == false) throw new Exception("404");
		
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
		m.addAttribute("mylectures", "mylectures");
		
		return "teacher/myLectures";
	}
	
	@RequestMapping("/myLecture")
	public String myLecture(HttpServletRequest req, Model m) throws Exception {
		if (isTeacher(getNick(req)) == false) throw new Exception("404");
		
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
		if (isTeacher(getNick(request)) == false) throw new Exception("404");
		
		int no;
		try {
			no = Integer.parseInt(request.getParameter("no"));
		} catch(Exception e) {
			throw new Exception("404");
		}

		LectureInfo info = lectureDao.teacherShowOne(no, getNick(request));
		
		m.addAttribute("mylecture", info);
		m.addAttribute("teacherNo", getTeacherNo(getNick(request)));

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
		if (isTeacher(getNick(mRequest)) == false) throw new Exception("404");
		
		int no = Integer.parseInt(mRequest.getParameter("no"));
		MultipartFile file = mRequest.getFile("file");
		String savePath = mRequest.getServletContext().getRealPath("/resource/file/lecture");
		
		lectureDao.edit(new LectureInfo(mRequest));
		
		if (!file.isEmpty()) {
			deleteFile(savePath, String.valueOf(no));
			
			String[] extension = file.getContentType().split("/");
			String filename = no + "." + extension[extension.length - 1];
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

		log.debug(mRequest.getParameter("url"));
		return "redirect:/teacher/myLecture?" + mRequest.getParameter("url");
	}
	
	@RequestMapping("/videoList")
	public String videoList(HttpServletRequest request, Model m) throws Exception {
		if (isTeacher(getNick(request)) == false) throw new Exception("404");
		
		int no;
		try {
			no = Integer.parseInt(request.getParameter("no"));
		} catch(Exception e) {
			throw new Exception("404");
		}

		List<LectureVideo> videoList = lectureDao.videoList(no);
		
		log.debug("videoList: " + videoList.size());
		
		m.addAttribute("videoList", videoList);

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
		m.addAttribute("no", no);
		
		return "teacher/videoList";
	}
	
	@RequestMapping("/addForm")
	public String addForm(HttpServletRequest request, Model m) {
		m.addAttribute("no", request.getParameter("no"));
		m.addAttribute("url", request.getParameter("url"));
		
		return "teacher/addForm";
	}
	
	@RequestMapping("/addVideo")
	public String addVideo(MultipartHttpServletRequest mRequest, Model m) throws Exception {
		if (isTeacher(getNick(mRequest)) == false) throw new Exception("404");
		
		int no = Integer.parseInt(mRequest.getParameter("no"));
		String title = mRequest.getParameter("title");
		
		int count = lectureDao.videoCount(no);

		MultipartFile file = mRequest.getFile("video");
		videoSavePath = mRequest.getServletContext().getRealPath("/resource/file/lectureVideo");
		
		log.debug("savePath: " + videoSavePath);

		String[] extension = file.getContentType().split("/");
		String filename = no + "(" + (count + 1) + ")." + extension[extension.length - 1];
		File target = new File(videoSavePath, filename);
		if(!target.exists()) target.mkdirs();
		file.transferTo(target);
		
		lectureDao.addVideo(no, title, filename, file.getOriginalFilename(), file.getContentType(), file.getSize());
		
		return "redirect:/teacher/videoList?no=" + no + "&" + mRequest.getParameter("url");
	}
	
	@RequestMapping("/editVideo")
	public void editVideo(String filename, String title) {
		lectureDao.editVideo(filename, title);
	}
	
	@RequestMapping("/deleteVideo")
	public void deleteVideo(int no, String filename) {
		lectureDao.deleteVideo(no, videoSavePath, filename);
	}
	
	@RequestMapping("/students")
	public String students(HttpServletRequest request, Model m) throws Exception {
		if (isTeacher(getNick(request)) == false) throw new Exception("404");
		
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
		if (isTeacher(getNick(request)) == false) throw new Exception("404");
		
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
		if (isTeacher(getNick(request)) == false) throw new Exception("404");
		
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
		if (isTeacher(getNick(req)) == false) throw new Exception("404");
		
		int point = memberDao.mypoint(getNick(req));
		
		m.addAttribute("point", point);
		
		return "teacher/withdrow";
	}
	
	
	@RequestMapping("/applynot")
	public String notapply(HttpServletRequest request, Model model, HttpSession session) throws Exception {
		String type = request.getParameter("type");
		String key = request.getParameter("key");
		System.out.println("type~~~~"+type);
		System.out.println("key~~~~"+key);
		
		String name=(String) session.getAttribute("member");
		if(name.equals("관리자")) {
			
	
		int pageNo;
		try {
			pageNo = Integer.parseInt(request.getParameter("page"));
		} catch(Exception e) {
			pageNo = 1;
		}
		if (pageNo <= 0 ) pageNo = 1;
		
		int listCount=teacherDao.count2(type, key);
		
		int boardSize = 10;
		int start = boardSize * pageNo - 9;
		int end = start + boardSize -1;
		if (end > listCount) end = listCount;
		
		List<Teacher>list2=teacherDao.list2(type, key, start, end);
		
		int blockSize = 10;
		int blockTotal = (listCount + boardSize - 1) / boardSize;
		int startBlock = (pageNo - 1) / blockSize * blockSize + 1;
		int endBlock = startBlock + blockSize - 1;
		if (endBlock > blockTotal) endBlock = blockTotal;
		
		
		String url="applynot?";
		if (type != null && key != null) {
			url += "type=" + type + "&key=" + key + "&";
			model.addAttribute("type", type);
			model.addAttribute("key", key);
		}
		
		
		model.addAttribute("list", list2);
		model.addAttribute("page",pageNo);
		model.addAttribute("startBlock", startBlock);
		model.addAttribute("endBlock", endBlock);
		model.addAttribute("url", url);
		
		
		
		
		return "teacher/applynot";
		}else {
			throw new Exception("일반 접근 제한");
		}
	}
	
	
	//여러개 승인
	@RequestMapping(value="/checkapply", method=RequestMethod.POST)
	public String apply(@RequestParam String teacherid) {
		
		teacherDao.stateedit(teacherid);
		
		return "redirect:teacher/applynot";
		
	}
	
	//상세보기 승인
	@RequestMapping(value="/accept", method=RequestMethod.POST)
	public String accept(@RequestParam String acceptteacher) throws Exception {
		System.out.println(acceptteacher);
		int no;
		try {
			no=Integer.parseInt(acceptteacher);
		}catch(Exception e) {
			throw new Exception("404");
		}
		
		teacherDao.stateedit2(no);
		
		return "redirect:teacher/applynot";
	}
	
	//여러개 거절
	@RequestMapping(value="/checkdelete", method=RequestMethod.POST)
	public String applydelete(@RequestParam String teacherid) {
		
		teacherDao.teachernotapply(teacherid);
		return "redirect:teacher/applynot";
	}
	
	
	//상세보기 거절
	@RequestMapping(value="/acceptnot", method=RequestMethod.POST)
	public String acceptnot(@RequestParam String notaccept) throws Exception {
		System.out.println(notaccept);
		int no;
		try {
			no=Integer.parseInt(notaccept);
		}catch(Exception e) {
			throw new Exception("404");
		}
		
		teacherDao.notaccept(no);
		
		return "redirect:teacher/applynot";
	}
	
	
	@RequestMapping("/applynotdetail")
	public String detail(HttpServletRequest request, String teacherno, Model model, HttpSession session) throws Exception {
		
		String name=(String) session.getAttribute("member");
		if(name.equals("관리자")) {
			
			
		
		int noI;
		try {
			noI=Integer.parseInt(teacherno);
		}catch(Exception e) {
			throw new Exception("404");
		}
		
		List<Teacher>teacher=teacherDao.detail(noI);
		if(teacher.size()==0) throw new Exception("404");
		
		model.addAttribute("teacherno",teacherno);
		model.addAttribute("teacherList",teacher);
		
		return "teacher/applynotdetail";
		}else {
			throw new Exception("일반 접근 제한");
		}
	}
}
