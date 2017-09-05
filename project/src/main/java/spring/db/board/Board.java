package spring.db.board;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class Board {
	private int no;
	private String writer;
	private String path;
	private String head;
	private String title;
	private String detail;
	private String notice;
	private int read;
	private int reply;
	private int best;
	private String reg;
	
	public Board(HttpServletRequest request) {
//		setNo(Integer.parseInt(request.getParameter("no")));
		setWriter(request.getParameter("writer"));
		setPath(request.getParameter("path"));
		setHead(request.getParameter("head"));
		setTitle(request.getParameter("title"));
		setDetail(request.getParameter("detail"));
		setNotice(request.getParameter("notice"));
//		setRead(Integer.parseInt(request.getParameter("read")));
//		setReply(Integer.parseInt(request.getParameter("reply")));
//		setBest(Integer.parseInt(request.getParameter("best")));
		setReg(request.getParameter("reg"));
	}
	public Board(ResultSet rs) throws SQLException {
		setNo(rs.getInt("no"));
		setWriter(rs.getString("writer"));
		setPath(rs.getString("path"));
		setHead(rs.getString("head"));
		setTitle(rs.getString("title"));
		setDetail(rs.getString("detail"));
		setNotice(rs.getString("notice"));
		setRead(rs.getInt("read"));
		setReply(rs.getInt("reply"));
		setBest(rs.getInt("best"));
		setReg(rs.getString("reg"));
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
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
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public int getRead() {
		return read;
	}
	public void setRead(int read) {
		this.read = read;
	}
	public int getReply() {
		return reply;
	}
	public void setReply(int reply) {
		this.reply = reply;
	}
	public int getBest() {
		return best;
	}
	public void setBest(int best) {
		this.best = best;
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
