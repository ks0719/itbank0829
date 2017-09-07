package spring.db.mail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("mailDao")
public class MailDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<Mail> mapper=(rs,index)->{
		
		return new Mail(rs);
	};

	public List<Mail> list(String nick, String box){
		String sql="select * from mail";
		switch(box) {
		case "protect":
		case "garbage":
		case "spam":
			sql+=" where mail_receiver=? and mail_position=? order by mail_reg desc, no desc";
			return jdbcTemplate.query(sql, new Object[] {nick,box},mapper);
		case "sent":
			sql+=" where mail_writer=? order by mail_reg desc, no desc";
			return jdbcTemplate.query(sql, new Object[] {nick},mapper);
			//받는 사람이 나일때  or box가 없을때
		default:
			sql+=" where mail_receiver=? and mail_position='index' order by mail_reg desc, no desc";
			return jdbcTemplate.query(sql, new Object[] {nick},mapper);
		}
	}
	
	public boolean protect(String mail_receiver, int no) {
		String sql = "update mail set mail_position='protect' where mail_receiver=? and no=?";
		int res=jdbcTemplate.update(sql, new Object[] {mail_receiver, no});
		return res>0;
	}
	
	public boolean delete(String mail_receiver, int no) {
		String sql="delete from mail where mail_receiver=? and no=?";
		int res=jdbcTemplate.update(sql, new Object[] {mail_receiver, no});
		return res>0;
	}
	
	public boolean update(String mail_receiver, String location, int no) {
		String sql = "update mail set mail_position=? where mail_receiver=? and no=?";
		int res=jdbcTemplate.update(sql, new Object[] {location, mail_receiver, no});
		return res>0;
		
	}
	
	public Mail select(String id, int no, String location) {
		String sql;
		if(location.equals("sent")) {
			sql = "select * from mail where mail_writer=? and no=?";
		}else {
			sql = "select * from mail where mail_receiver=? and no=?";
		}
		
		List<Mail> list = jdbcTemplate.query(sql, new Object[] {id, no}, mapper);
		if(list.size()<1) return null;
		return jdbcTemplate.query(sql, new Object[] {id, no}, mapper).get(0);
	}
	
	public boolean insert(Mail mail) {
		//mail_receiver, mail_writer을 차단했는지 확인해야됨
		//[1] 일단 회원 테이블에서 mail_receiver이 차단한 사람들의 명단을 가져오자
		String sql = "select spam from member where nick=?";
		String spam_string = jdbcTemplate.queryForObject(sql,new Object[] {mail.getMail_receiver()}, String.class);
		boolean isSpam = false;
		if(spam_string!=null) {
			String[] spams = spam_string.split("-");
			for(String spam:spams) {
				if(spam.equals(mail.getMail_writer())) {
					isSpam=true;
					break;
				}
			}
		}
		String location=(isSpam)?"spam":"index";
		
		sql = "insert into mail values(?,?,?,sysdate,?,?,?, mail_seq.nextval)";
		int res=jdbcTemplate.update(sql, new Object[] {
				mail.getMail_writer(), mail.getMail_title(), mail.getMail_content(), 
				mail.getMail_read(), mail.getMail_receiver(), location
		});
		return res>0;
	}
	
	public boolean read(int no) {
		String sql = "update mail set MAIL_READ='읽음' where no=?";
		return jdbcTemplate.update(sql, new Object[] {no})>0;
	}
	
	public boolean isExist(String nick) {
		String sql = "select count(*) from member where nick=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {nick}, Integer.class)>0;
	}
	
}
