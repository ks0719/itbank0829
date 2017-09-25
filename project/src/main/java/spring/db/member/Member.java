package spring.db.member;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class Member {

	private int no;
	private String id;
	private String pw;
	private String name;
	private String nick;
	private String phone;
	private String sort;
	private String post;
	private String addr1;
	private String addr2;
	private int mileage;
	private int lev;
	private String reg;
	private String power;
	private String friend;
	
	public Member() {
		super();
	}
	public Member(ResultSet rs) throws SQLException {
		setNo(rs.getInt("no"));
		setId(rs.getString("id"));
		setPw(rs.getString("pw"));
		setName(rs.getString("name"));
		setNick(rs.getString("nick"));
		setPhone(rs.getString("phone"));
		setSort(rs.getString("sort"));
		setPost(rs.getString("post"));
		setAddr1(rs.getString("addr1"));
		setAddr2(rs.getString("addr2"));
		setMileage(rs.getInt("mileage"));
		setLev(rs.getInt("lev"));
		setReg(rs.getString("reg"));
		setPower(rs.getString("power"));
		setFriend(rs.getString("friend"));
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public Member(HttpServletRequest request) throws SQLException {
		try {
			setNo(Integer.parseInt(request.getParameter("no")));
		}catch(Exception e) {
			setNo(0);
		}
		setId(request.getParameter("id"));
		setPw(request.getParameter("pw"));
		setName(request.getParameter("name"));
		setNick(request.getParameter("nick"));
//		setPhone(Integer.parseInt(request.getParameter("phone")));
		setPhone(request.getParameter("phone"));
		setSort(request.getParameter("sort"));
		setPost(request.getParameter("post"));
		setAddr1(request.getParameter("addr1"));
		setAddr2(request.getParameter("addr2"));
		setFriend(request.getParameter("friend"));
		try {
			setMileage(Integer.parseInt(request.getParameter("mileage")));
		}catch(Exception e){
			setMileage(0);
		}
		
		try {
			setLev(Integer.parseInt(request.getParameter("lev")));
			
		}catch(Exception e) {
			setLev(1);
		}
		setReg(request.getParameter("reg"));
		setPower(request.getParameter("power"));
	}

	@Override
	public String toString() {
		return "Member [no=" + no + ", id=" + id + ", pw=" + pw + ", name=" + name + ", nick=" + nick + ", phone="
				+ phone + ", sort=" + sort + ", post=" + post + ", addr1=" + addr1 + ", addr2=" + addr2 + ", mileage="
				+ mileage + ", lev=" + lev + ", reg=" + reg + ", power=" + power + ", friend=" + friend + "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
	public void setFriend(String friend) {
		this.friend = friend;
	}
	public String getFriend() {
		return friend;
	}
	
}
