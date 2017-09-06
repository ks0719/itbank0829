package spring.db.board;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Reply {
	private int no;
	private String writer;
	private String detail;
	private String context;
	private int seq;
	private int depth;
	private int best;
	private String date;
	
	public Reply(ResultSet rs) throws SQLException {
		setNo(rs.getInt("no"));
		setWriter(rs.getString("writer"));
		setDetail(rs.getString("detail"));
		setContext(rs.getString("context"));
		setSeq(rs.getInt("seq"));
		setDepth(rs.getInt("depth"));
		setBest(rs.getInt("best"));
		setDate(rs.getString("date"));
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
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
