package spring.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public class B2CDto {
private int rn;
private int no;
private String id;
private String type;
private String title;
private String detail;
private String filename;
private String realname;
private String filetype;
private long filesize;
private String reg;
private String state;

public B2CDto() {
	super();
}
public B2CDto(HttpServletRequest request) {
	
	setId(request.getParameter("id"));
	setType(request.getParameter("type"));
	setTitle(request.getParameter("title"));
	setDetail(request.getParameter("detail"));
//	setFilename(request.getParameter("filename"));
//	setFiletype(request.getParameter("filetype"));
//	setFilesize(Long.parseLong(request.getParameter("filesize")));
	setFilename("이름");
	setFiletype("타입");
	setFilesize(1245);
	
}
public B2CDto(MultipartHttpServletRequest mRequest) {
	
	setId(mRequest.getParameter("id"));
	setType(mRequest.getParameter("type"));
	setTitle(mRequest.getParameter("title"));
	setDetail(mRequest.getParameter("detail"));
//	setFilename(request.getParameter("filename"));
//	setFiletype(request.getParameter("filetype"));
//	setFilesize(Long.parseLong(request.getParameter("filesize")));
	setFilename("이름");
	setFiletype("타입");
	setFilesize(1245);
	
}
public B2CDto(ResultSet rs) throws SQLException {
	
	setRn(rs.getInt("rn"));
	setNo(rs.getInt("no"));
	setId(rs.getString("id"));
	setType(rs.getString("type"));
	setTitle(rs.getString("title"));
	setDetail(rs.getString("title"));
//	setFilename(rs.getString("filename"));
//	setRealname(rs.getString("realname"));
//	setFiletype(rs.getString("filetype"));
//	setFilesize(rs.getLong("filesize"));
	setFilename("이름");
	setRealname("진짜이름");
	setFiletype("사진");
	setFilesize(1245);
	setReg(rs.getString("reg"));
	setState(rs.getString("state"));
}
public void setRn(int rn) {
	this.rn = rn;
}
public int getRn() {
	return rn;
}
public void setNo(int no) {
	this.no = no;
}
public int getNo() {
	return no;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
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
public String getRealname() {
	return realname;
}
public void setRealname(String realname) {
	this.realname = realname;
}
public String getFiletype() {
	return filetype;
}
public void setFiletype(String filetype) {
	this.filetype = filetype;
}
public long getFilesize() {
	return filesize;
}
public void setFilesize(long filesize) {
	this.filesize = filesize;
}
public String getReg() {
	return reg.substring(0,11);
}
public void setReg(String reg) {
	this.reg = reg;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
@Override
public String toString() {
	return "B2CDto [" + ", id=" + id + ", type=" + type + ", title=" + title + ", detail=" + detail
			+ ", filename=" + filename + ", realname=" + realname + ", filetype=" + filetype + ", filesize=" + filesize
			+ ", reg=" + reg + ", state=" + state + "]";
}


}
