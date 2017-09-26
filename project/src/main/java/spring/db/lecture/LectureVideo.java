package spring.db.lecture;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LectureVideo {
	private int no;
	private String title;
	private String filename;
	private String filerealname;
	private String filetype;
	private long filesize;
	
	public LectureVideo(ResultSet rs) throws SQLException {
		setNo(rs.getInt("no"));
		setTitle(rs.getString("title"));
		setFilename(rs.getString("filename"));
		setFilerealname(rs.getString("filerealname"));
		setFiletype(rs.getString("filetype"));
		setFilesize(rs.getLong("filesize"));
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilerealname() {
		return filerealname;
	}
	public void setFilerealname(String filerealname) {
		this.filerealname = filerealname;
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
