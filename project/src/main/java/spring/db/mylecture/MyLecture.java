package spring.db.mylecture;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyLecture {
	private String id;
	private int no;
	private String tag;
	private String title;
	private String teacher;
	private String time;
	private String type;
	private int price;
	private String state;
	private String reg;
	private String eval;
	private String wish;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public String getEval() {
		return eval;
	}
	public void setEval(String eval) {
		this.eval = eval;
	}
	public String getWish() {
		return wish;
	}
	public void setWish(String wish) {
		this.wish = wish;
	}
	public MyLecture() {
		super();
	}
	public MyLecture(ResultSet rs) throws SQLException {
		setEval(rs.getString("eval"));
		setId(rs.getString("id"));
		setNo(rs.getInt("no"));
		setPrice(rs.getInt("price"));
		setReg(rs.getString("reg"));
		setState(rs.getString("state"));
		setTag(rs.getString("tag"));
		setTeacher(rs.getString("teacher"));
		setTime(rs.getString("time"));
		setTitle(rs.getString("title"));
		setType(rs.getString("type"));
		setWish(rs.getString("wish"));
	}
}
