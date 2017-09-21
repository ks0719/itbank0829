package spring.db.teacher;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Qna {
	private int no;
	private String student;
	private String detail;
	private String filename;
	
	public Qna(ResultSet rs) throws SQLException {
		setNo(rs.getInt("no"));
		setStudent(rs.getString("student"));
		setDetail(rs.getString("detail"));
		setFilename(rs.getString("filename"));
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

}
