package spring.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class RB2CDto {
private int no;
private String nick;
private String detail;
private String reg;

public RB2CDto() {
	super();
}
public RB2CDto(HttpServletRequest request) {
	
	setNo(Integer.parseInt(request.getParameter("no")));
	setNick(request.getParameter("nick"));
	setDetail(request.getParameter("detail"));
	
}
public RB2CDto(ResultSet rs) throws SQLException {
	
	setNo(rs.getInt("no"));
	setDetail(rs.getString("detail"));
	setReg(rs.getString("reg"));
}
public int getNo() {
	return no;
}
public void setNo(int no) {
	this.no = no;
}
public String getNick() {
	return nick;
}
public void setNick(String nick) {
	this.nick = nick;
}
public String getDetail() {
	return detail;
}
public void setDetail(String detail) {
	this.detail = detail;
}
public String getReg() {
	return reg;
}
public void setReg(String reg) {
	this.reg = reg;
}
@Override
public String toString() {
	return "RB2CDto [no=" + no + ", nick=" + nick + ", detail=" + detail + ", reg=" + reg + "]";
}



}
