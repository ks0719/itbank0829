package spring.db.teacher;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Assess {
	private int no;
	private int kin_grade;
	private int price_grade;
	private int kind_grade;
	private String detail;
	
	public Assess(ResultSet rs) throws SQLException {
		setNo(rs.getInt("no"));
		setKin_grade(rs.getInt("kin_grade"));
		setPrice_grade(rs.getInt("price_grade"));
		setKind_grade(rs.getInt("kind_grade"));
		setDetail(rs.getString("detail"));
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getKin_grade() {
		return kin_grade;
	}
	public void setKin_grade(int kin_grade) {
		this.kin_grade = kin_grade;
	}
	public int getPrice_grade() {
		return price_grade;
	}
	public void setPrice_grade(int price_grade) {
		this.price_grade = price_grade;
	}
	public int getKind_grade() {
		return kind_grade;
	}
	public void setKind_grade(int kind_grade) {
		this.kind_grade = kind_grade;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

}
