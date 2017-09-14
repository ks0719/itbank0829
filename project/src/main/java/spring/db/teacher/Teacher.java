package spring.db.teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

}