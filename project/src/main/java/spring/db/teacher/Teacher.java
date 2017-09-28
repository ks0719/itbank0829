package spring.db.teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class Teacher {
	private String name;
	private String sort;
	private String career;
	private String intro;
	private String picture_name;
	private String picture_realname;
	private String picture_type;
	private long picture_size;
	private int best;
	private int count;
	private double grade;
	private String reg;
	private String state;
	private int teacherno;
	private int students;
	
	public Teacher(ResultSet rs) throws SQLException {
		setName(rs.getString("name"));
		setSort(rs.getString("sort"));
		setCareer(rs.getString("career"));
		setIntro(rs.getString("intro"));
		setPicture_name(rs.getString("picture_name"));
		setPicture_realname(rs.getString("picture_realname"));
		setPicture_type(rs.getString("picture_type"));
		setPicture_size(rs.getInt("picture_size"));
		setBest(rs.getInt("best"));
		setCount(rs.getInt("count"));
		setGrade(rs.getDouble("grade"));
		setReg(rs.getString("reg"));
		setState(rs.getString("state"));
		setTeacherno(rs.getInt("teacherno"));
		setStudents(rs.getInt("students"));
	}
	
	public Teacher(MultipartHttpServletRequest mRequest) {
		setName(mRequest.getParameter("name"));
		setSort(mRequest.getParameter("sort"));
		setCareer(mRequest.getParameter("career"));
		setIntro(mRequest.getParameter("intro"));
		
		MultipartFile file = mRequest.getFile("file");
		if (file.getOriginalFilename() != "") {
			setPicture_realname(file.getOriginalFilename());
			setPicture_type(file.getContentType());
			setPicture_size(file == null ? 0 : file.getSize());
		} else {
			String picture_realname = mRequest.getParameter("picture_realname");
			setPicture_realname(picture_realname == null ? "" : picture_realname);
			String picture_type = mRequest.getParameter("picture_type");
			setPicture_type(picture_type == null ? "" : picture_type);
			String picture_size = mRequest.getParameter("picture_size");
			setPicture_size(Integer.parseInt(picture_size == null ? "0" : picture_size));
		}
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
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
	public void setPicture_size(long l) {
		this.picture_size = l;
	}
	public int getBest() {
		return best;
	}
	public void setBest(int best) {
		this.best = best;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public String getDate() {
		return reg.substring(0, 10);
	}
	public String getTime() {
		return reg.substring(11, 16);
	}
	public String getAuto() {
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if (today.equals(getDate()))
			return getTime();
		else
			return getDate();
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getTeacherno() {
		return teacherno;
	}
	public void setTeacherno(int teacherno) {
		this.teacherno = teacherno;
	}
	public int getStudents() {
		return students;
	}
	public void setStudents(int students) {
		this.students = students;
	}

}