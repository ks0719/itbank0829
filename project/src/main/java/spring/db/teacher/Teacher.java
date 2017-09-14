package spring.db.teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class Teacher {
	private int no;
	private String name;
	private String sort;
	private String career;
	private String intro;
	private int best;
	private int count;
	private double grade;
	private String reg;
	private String picture_name;
	private String picture_realname;
	private String picture_type;
	private long picture_size;
	
	public Teacher(ResultSet rs) throws SQLException {
		setNo(rs.getInt("no"));
		setName(rs.getString("name"));
		setSort(rs.getString("sort"));
		setCareer(rs.getString("career"));
		setIntro(rs.getString("intro"));
		setBest(rs.getInt("best"));
		setCount(rs.getInt("count"));
		setGrade(rs.getDouble("grade"));
		setReg(rs.getString("reg"));
		setPicture_name(rs.getString("picture_name"));
		setPicture_realname(rs.getString("picture_realname"));
		setPicture_type(rs.getString("picture_type"));
		setPicture_size(rs.getInt("picture_size"));
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
			String originfile = mRequest.getParameter("originfile");
			setPicture_realname(originfile == null ? "" : originfile);
			String filetype = mRequest.getParameter("filetype");
			setPicture_type(filetype == null ? "" : filetype);
			String size = mRequest.getParameter("filesize");
			setPicture_size(Integer.parseInt(size == null ? "0" : size));
		}
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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

}