package spring.db.board;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class Comment {
	private int no;
	private String writer;
	private String detail;
	private int topcontext;
	private int context;
	private int seq;
	private int depth;
	private int best;
	private String reg;
	
	public Comment(ResultSet rs) throws SQLException {
		setNo(rs.getInt("no"));
		setWriter(rs.getString("writer"));
		setDetail(rs.getString("detail"));
		setTopcontext(rs.getInt("topcontext"));
		setContext(rs.getInt("context"));
		setSeq(rs.getInt("seq"));
		setDepth(rs.getInt("depth"));
		setBest(rs.getInt("best"));
		setReg(rs.getString("reg"));
	}
	public Comment(HttpServletRequest request, int context) {
		setWriter(request.getParameter("writer"));
		setDetail(request.getParameter("detail"));
		setTopcontext(Integer.parseInt(request.getParameter("topcontext")));
		setContext(context);
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
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getTopcontext() {
		return topcontext;
	}
	public void setTopcontext(int topcontext) {
		this.topcontext = topcontext;
	}
	public int getContext() {
		return context;
	}
	public void setContext(int context) {
		this.context = context;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
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
	
}
