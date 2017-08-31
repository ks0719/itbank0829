package spring.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LectureInfo {
	private int no;
	private String tag;
	private String title;
	private String teacher;
	private String picture_name;
	private String picture_realname;
	private String picture_type;
	private long picture_size;
	private String intro;
	private String detail;
	private String possible;
	private int price;
	private String accept;
	private String reg;
	
	public LectureInfo(ResultSet rs) throws SQLException {
		setNo(rs.getInt("no"));
		setTag(rs.getString("tag"));
		setTitle(rs.getString("title"));
		setTeacher(rs.getString("teacher"));
		setPicture_name(rs.getString("picture_name"));
		setPicture_realname(rs.getString("picture_realname"));
		setPicture_type(rs.getString("picture_type"));
		setPicture_size(rs.getLong("picture_size"));
		setIntro(rs.getString("intro"));
		setDetail(rs.getString("detail"));
		setPossible(rs.getString("possible"));
		setPrice(rs.getInt("price"));
		setAccept(rs.getString("accept"));
		setReg(rs.getString("reg"));
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
	public String getPossible() {
		return possible;
	}
	public void setPossible(String possible) {
		this.possible = possible;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
	

}
