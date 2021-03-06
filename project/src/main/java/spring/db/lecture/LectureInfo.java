package spring.db.lecture;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class LectureInfo {
	private int no;
	private String tag;
	private String title;
	private String teacher;
	private String time;
	private String type;
	private int price;
	private String state;
	private double kin_grade;
	private double price_grade;
	private double kind_grade;
	private String picture_name;
	private String picture_realname;
	private String picture_type;
	private long picture_size;
	private String intro;
	private String detail;
	private String accept;
	private String reg;
	private String period;
	private int teacherno;
	
	public LectureInfo(ResultSet rs) throws SQLException {
		setNo(rs.getInt("no"));
		setTag(rs.getString("tag"));
		setTitle(rs.getString("title"));
		setTeacher(rs.getString("teacher"));
		setTime(rs.getString("time"));
		setType(rs.getString("type"));
		setPrice(rs.getInt("price"));
		setState(rs.getString("state"));
		setKin_grade(rs.getDouble("kin_grade"));
		setPrice_grade(rs.getDouble("price_grade"));
		setKind_grade(rs.getDouble("kind_grade"));
		setPicture_name(rs.getString("picture_name"));
		setPicture_realname(rs.getString("picture_realname"));
		setPicture_type(rs.getString("picture_type"));
		setPicture_size(rs.getLong("picture_size"));
		setIntro(rs.getString("intro"));
		setDetail(rs.getString("detail"));
		setAccept(rs.getString("accept"));
		setReg(rs.getString("reg"));
		setPeriod(rs.getString("period"));
		setTeacherno(rs.getInt("teacherno"));
	}

	public LectureInfo(MultipartHttpServletRequest mRequest) {
		setNo(mRequest.getParameter("no") == null ? 0 : Integer.parseInt(mRequest.getParameter("no")));
		setTag(mRequest.getParameter("tag"));
		setTitle(mRequest.getParameter("title"));
		setTeacher(mRequest.getParameter("teacher"));
		setTime(mRequest.getParameter("time"));
		setType(mRequest.getParameter("type"));
		setPrice(Integer.parseInt(mRequest.getParameter("price")));
		
		MultipartFile file = mRequest.getFile("file");
		if (!file.isEmpty()) {
			setPicture_realname(file.getOriginalFilename());
			setPicture_type(file.getContentType());
			setPicture_size(file.getSize());
		} else {
			setPicture_name(mRequest.getParameter("picture_name"));
			setPicture_realname(mRequest.getParameter("picture_realname"));
			setPicture_type(mRequest.getParameter("picture_type"));
			setPicture_size(Long.parseLong(mRequest.getParameter("picture_size") == null ? "0" : mRequest.getParameter("picture_size")));
		}
		
		setIntro(mRequest.getParameter("intro"));
		setDetail(mRequest.getParameter("detail"));
		setPeriod(mRequest.getParameter("period"));
		setTeacherno(Integer.parseInt(mRequest.getParameter("teacherno")));
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getKin_grade() {
		return kin_grade;
	}

	public void setKin_grade(double kin_grade) {
		this.kin_grade = kin_grade;
	}

	public double getPrice_grade() {
		return price_grade;
	}

	public void setPrice_grade(double price_grade) {
		this.price_grade = price_grade;
	}

	public double getKind_grade() {
		return kind_grade;
	}

	public void setKind_grade(double kind_grade) {
		this.kind_grade = kind_grade;
	}

	public String getPicture_name() {
		return picture_name;
	}

	public void setPicture_name(String picture_name) {
		this.picture_name = picture_name;
	}

	public String getPicture_realname() {
		return picture_realname;
	}

	public void setPicture_realname(String picture_realname) {
		this.picture_realname = picture_realname;
	}

	public String getPicture_type() {
		return picture_type;
	}

	public void setPicture_type(String picture_type) {
		this.picture_type = picture_type;
	}

	public long getPicture_size() {
		return picture_size;
	}

	public void setPicture_size(long picture_size) {
		this.picture_size = picture_size;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
	
	public int getTeacherno() {
		return teacherno;
	}
	
	public void setTeacherno(int teacherno) {
		this.teacherno = teacherno;
	}
	
}
