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
		case "spam":
			sql+=" where mail_receiver=? and receiver_position=? order by mail_reg desc, no desc";
			return jdbcTemplate.query(sql, new Object[] {nick,box},mapper);
		case "sent":
			sql+=" where mail_writer=? and writer_position=? order by mail_reg desc, no desc";
			return jdbcTemplate.query(sql, new Object[] {nick, box},mapper);
		case "protect":
		case "garbage":
			sql += " where mail_receiver=? and receiver_position=? or mail_writer=? and writer_position=?";
			return jdbcTemplate.query(sql, new Object[] {nick, box, nick, box},mapper);
			//받는 사람이 나일때  or box가 없을때
		default:
			sql+=" where mail_receiver=? and receiver_position='index' order by mail_reg desc, no desc";
			return jdbcTemplate.query(sql, new Object[] {nick},mapper);
		}
	}
	
	public boolean delete(String mail_receiver, int no) {
		//먼저 지금 선택한 mail이 받은 메일인지 보낸 메일인지 확인해야함
		String sql = "select mail_receiver from mail where no=?";
		String receiver = jdbcTemplate.queryForObject(sql, new Object[] {no}, String.class);
		
		if(receiver.equals(mail_receiver)) {
			//선택한 메일이 받은 메일일 때
			//상대방이 삭제를 눌렀는지 안눌렀는지 알아야 함
			sql = "select writer_position from mail where no=?";
			String writer_position = jdbcTemplate.queryForObject(sql, new Object[] {no}, String.class);
			if(writer_position.equals("delete")) {
				sql = "delete mail where no=?";
				return jdbcTemplate.update(sql, new Object[] {no})>0;
			}else {
				sql = "update mail set receiver_position='delete' where no=?";
				return jdbcTemplate.update(sql, new Object[] {no})>0;
			}
		}else {
			//선택한 메일이 보낸 메일일 때
			//상대방이 삭제를 눌렀는지 안눌렀는지 알아야 함
			sql = "select receiver_position from mail where no=?";
			String receiver_position = jdbcTemplate.queryForObject(sql, new Object[] {no}, String.class);
			if(receiver_position.equals("delete")) {
				sql = "delete mail where no=?";
				return jdbcTemplate.update(sql, new Object[] {no})>0;
			}else {
				sql = "update mail set writer_position='delete' where no=?";
				return jdbcTemplate.update(sql, new Object[] {no})>0;
			}
		}
	}
	
	public boolean update(String mail_receiver, String location, int no) {
		//먼저 지금 선택한 mail이 받은 메일인지 보낸 메일인지 확인해야함
		String sql = "select mail_receiver from mail where no=?";
		String receiver = jdbcTemplate.queryForObject(sql, new Object[] {no}, String.class);
		
		if(receiver.equals(mail_receiver)) {
			//선택한 메일이 받은 메일일 때
			sql = "update mail set receiver_position=? where mail_receiver=? and no=?";
			return jdbcTemplate.update(sql, new Object[] {location, mail_receiver, no})>0;
		}else {
			//선택한 메일이 보낸 메일일 때
			sql = "update mail set writer_position=? where mail_writer=? and no=?";
			return jdbcTemplate.update(sql, new Object[] {location, mail_receiver, no})>0;
		}
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
		String receiver_position=(isSpam)?"spam":"index";
		
		sql = "insert into mail values(?,?,?,sysdate,?,?,?, mail_seq.nextval,'sent')";
		int res=jdbcTemplate.update(sql, new Object[] {
				mail.getMail_writer(), mail.getMail_title(), mail.getMail_content(), 
				mail.getMail_read(), mail.getMail_receiver(), receiver_position
		});
		return res>0;
	}
	
	public boolean read(Mail mail) {
		String sql = "select mail_receiver from mail where no=?";
		String receiver = jdbcTemplate.queryForObject(sql, new Object[] {mail.getNo()}, String.class);
		System.out.println(receiver);
		if(receiver.equals(mail.getMail_writer())) {
			sql = "update mail set MAIL_READ='읽음' where no=?";
			return jdbcTemplate.update(sql, new Object[] {mail.getNo()})>0;
		}
		
		return false;
	}
	
	public boolean isExist(String nick) {
		String sql = "select count(*) from member where nick=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {nick}, Integer.class)>0;
	}
	
}
