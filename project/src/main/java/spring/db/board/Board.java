package spring.db.board;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class Board {
	private int no;
	private String writer;
	private String path;
	private String head;
	private String title;
	private String detail;
	private int context;
	private int seq;
	private int depth;
	private String notice;
	private int read;
	private int reply;
	private int best;
	private String reg;
	private String filename;
	private String originfile;
	private String filetype;
	private long filesize;
	
	public Board(MultipartHttpServletRequest mRequest) {
		setWriter(mRequest.getParameter("writer"));
		setPath(mRequest.getParameter("path"));
		setHead(mRequest.getParameter("head"));
		setTitle(mRequest.getParameter("title"));
		setDetail(mRequest.getParameter("detail"));
		setNotice(mRequest.getParameter("notice"));
		setReg(mRequest.getParameter("reg"));

		MultipartFile file = mRequest.getFile("file");
		setOriginfile(file.getOriginalFilename());
		setFiletype(file.getContentType());
		setFilesize(file == null ? 0 : file.getSize());
	}
	public Board(ResultSet rs) throws SQLException {
		setNo(rs.getInt("no"));
		setWriter(rs.getString("writer"));
		setPath(rs.getString("path"));
		setHead(rs.getString("head"));
		setTitle(rs.getString("title"));
		setDetail(rs.getString("detail"));
		setContext(rs.getInt("context"));
		setSeq(rs.getInt("seq"));
		setDepth(rs.getInt("depth"));
		setNotice(rs.getString("notice"));
		setRead(rs.getInt("read"));
		setReply(rs.getInt("reply"));
		setBest(rs.getInt("best"));
		setReg(rs.getString("reg"));
		setFilename(rs.getString("filename"));
		setOriginfile(rs.getString("originfile"));
		setFiletype(rs.getString("filetype"));
		setFilesize(rs.getInt("filesize"));
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getOriginfile() {
		return originfile;
	}
	public void setOriginfile(String originfile) {
		this.originfile = originfile;
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

}
