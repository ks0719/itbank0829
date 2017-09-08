package spring.db.member;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class Member {

	private int no;
	private String id;
	private String pw;
	private String name;
	private String nickname;
	private String phone;
	private String sort;
	private String post;
	private String addr1;
	private String addr2;
	private int mileage;
	private int lev;
	private String reg;
	
	public Member() {
		super();
	}
	public Member(ResultSet rs) throws SQLException {
		setNo(rs.getInt("no"));
		setId(rs.getString("id"));
		setPw(rs.getString("pw"));
		setName(rs.getString("name"));
		setNickname(rs.getString("nickname"));
		setPhone(rs.getString("phone"));
		setSort(rs.getString("sort"));
		setPost(rs.getString("post"));
		setAddr1(rs.getString("addr1"));
		setAddr2(rs.getString("addr2"));
		setMileage(rs.getInt("mileage"));
		setLev(rs.getInt("lev"));
		setReg(rs.getString("reg"));
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
		setNickname(request.getParameter("nick"));
//		setPhone(Integer.parseInt(request.getParameter("phone")));
		setPhone(request.getParameter("phone"));
		setSort(request.getParameter("sort"));
		setPost(request.getParameter("post"));
		setAddr1(request.getParameter("addr1"));
		setAddr2(request.getParameter("addr2"));
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
	}
	public Member(int no, String id, String pw, String name, String nickname, String phone, String sort, String post,
			String addr1, String addr2, int mileage, int lev, String reg) {
		super();
		this.no = no;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nickname = nickname;
		this.phone = phone;
		this.sort = sort;
		this.post = post;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.mileage = mileage;
		this.lev = lev;
		this.reg = reg;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	
	
}
