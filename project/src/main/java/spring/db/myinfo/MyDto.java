package spring.db.myinfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyDto {
	private String id;
	private String pw;
	private String name;
	private String nick;
	private String phone;
	private String post;
	private String addr1;
	private String addr2;
	private String sort;
	private int mileage;
	private int lev;
	private String reg;
	private String spam;
	public MyDto() {
		super();
	}
	public MyDto(ResultSet rs) throws SQLException {
		setId(rs.getString("id"));
		setPw(rs.getString("pw"));
		setName(rs.getString("name"));
		setNick(rs.getString("nick"));
		setPhone(rs.getString("phone"));
		setPost(rs.getString("post"));
		setAddr1(rs.getString("addr1"));
		setAddr2(rs.getString("addr2"));
		setSort(rs.getString("sort"));
		setMileage(rs.getInt("mileage"));
		setLev(rs.getInt("lev"));
		setReg(rs.getString("reg"));
		setSpam(rs.getString("spam"));
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public String getSpam() {
		return spam;
	}
	public void setSpam(String spam) {
		this.spam = spam;
	}
	@Override
	public String toString() {
		return "MyDto [id=" + id + ", pw=" + pw + ", name=" + name + ", nick=" + nick + ", phone=" + phone + ", post="
				+ post + ", addr1=" + addr1 + ", addr2=" + addr2 + ", sort=" + sort + ", mileage=" + mileage + ", lev="
				+ lev + ", reg=" + reg + ", spam=" + spam + "]";
	}
	
	

}
