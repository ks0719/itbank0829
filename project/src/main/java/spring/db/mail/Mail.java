package spring.db.mail;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class Mail {
	
	private int no;
	private String mail_writer;
	private String mail_title;
	private String mail_content;
	private String mail_receiver;
	private String mail_read;
	private String mail_reg;
	
	private String receiver_position;
	private String writer_position;
	
	public String getReceiver_position() {
		return receiver_position;
	}

	public void setReceiver_position(String receiver_position) {
		this.receiver_position = receiver_position;
	}

	public String getWriter_position() {
		return writer_position;
	}

	public void setWriter_position(String writer_position) {
		this.writer_position = writer_position;
	}

	public Mail(HttpServletRequest req) {
		setMail_content(req.getParameter("mail_content"));
		setMail_receiver(req.getParameter("mail_receiver"));
		setMail_title(req.getParameter("mail_title"));
		setMail_writer(req.getParameter("mail_writer"));
	}
	
	public Mail(ResultSet rs) throws SQLException {
		super();
		setNo(rs.getInt("no"));
		setMail_content(rs.getString("mail_content"));
		setMail_read(rs.getString("mail_read"));
		setMail_receiver(rs.getString("mail_receiver"));
		setMail_reg(rs.getString("mail_reg"));
		setMail_title(rs.getString("mail_title"));
		setMail_writer(rs.getString("mail_writer"));
		setReceiver_position(rs.getString("receiver_position"));
		setWriter_position(rs.getString("writer_position"));
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	public Mail() {
		super();
	}
	public String getMail_writer() {
		return mail_writer;
	}
	public void setMail_writer(String mail_writer) {
		this.mail_writer = mail_writer;
	}
	public String getMail_title() {
		return mail_title;
	}
	public void setMail_title(String mail_title) {
		this.mail_title = mail_title;
	}
	public String getMail_content() {
		return mail_content;
	}
	public void setMail_content(String mail_content) {
		this.mail_content = mail_content;
	}
	public String getMail_receiver() {
		return mail_receiver;
	}
	public void setMail_receiver(String mail_receiver) {
		this.mail_receiver = mail_receiver;
	}
	public String getMail_read() {
		return mail_read;
	}
	public void setMail_read(String mail_read) {
		this.mail_read = mail_read;
	}
	public String getMail_reg() {
		return mail_reg;
	}
	public void setMail_reg(String mail_reg) {
		this.mail_reg = mail_reg;
	}
	
}
