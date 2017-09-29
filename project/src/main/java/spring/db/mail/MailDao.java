package spring.db.mail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("mailDao")
public class MailDao {

	public static final int MAIL_HEIGHT = 15;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<Mail> mapper=(rs,index)->{
		
		return new Mail(rs);
	};
	
	public List<Mail> defaultList(String nick, int pageno){
		int start, end;
		if(pageno==0) {
			start=1;
			end = Integer.MAX_VALUE;
		}else {
			start = (pageno-1)*MAIL_HEIGHT+1;
			end = start+MAIL_HEIGHT-1;
		}
		
		String sql = "select * from ("
				+ "select rownum rn, TMP.* from("
				
				+ "select * from mail "
				
				+"where mail_receiver=? and receiver_position='index'"
				+ " order by mail_reg desc, no desc"
				+ ")TMP) "
				+ "where rn between ? and ?";
		
		return jdbcTemplate.query(sql,new Object[]{nick, start, end},mapper);
	}
	
	public List<Mail> list(String nick, String box, int pageno) throws Exception {
		int start = (pageno-1)*MAIL_HEIGHT+1;
		int end = start+MAIL_HEIGHT-1;
		
		String sql = "select * from ("
				+ "select rownum rn, TMP.* from("
				+ "select * from mail ";
		
		
		switch(box) {
		case "spam":
			sql+= "where mail_receiver=? and receiver_position=?";
			break;
		case "sent":
			sql+="where mail_writer=? and writer_position=?";
			break;
		case "protect":
		case "garbage":
			sql+="where mail_receiver=? and receiver_position=? or mail_writer=? and writer_position=?";
			sql += 	" order by mail_reg desc, no desc"
					+ ")TMP) "
					+ "where rn between ? and ?";
			return jdbcTemplate.query(sql, new Object[] {nick, box, nick, box, start, end},mapper);
		default:
			return defaultList(nick, pageno);
		}
				
		sql += 	" order by mail_reg desc, no desc"
				+ ")TMP) "
				+ "where rn between ? and ?";
		
		return jdbcTemplate.query(sql,new Object[]{nick, box, start, end},mapper);
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
		if(spam_string!=null&&spam_string.indexOf(mail.getMail_writer())>=0) {
			isSpam=true;
		}
		String receiver_position=(isSpam)?"spam":"index";
		
		sql = "insert into mail values(?,?,?,sysdate,?,?,?, mail_seq.nextval,'sent')";
		int res=jdbcTemplate.update(sql, new Object[] {
				mail.getMail_writer(), mail.getMail_title(), mail.getMail_content(), 
				mail.getMail_read(), mail.getMail_receiver(), receiver_position
		});
		return res>0;
	}
	
	public boolean read(Mail mail, String nick) {
		if(nick.equals(mail.getMail_receiver())) {
			String sql = "update mail set MAIL_READ='읽음' where no=?";
			return jdbcTemplate.update(sql, new Object[] {mail.getNo()})>0;
		}
		return false;
	}
	
	public boolean isExist(String nick) {
		String sql = "select count(*) from member where nick=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {nick}, Integer.class)>0;
	}
	
	public int countNewMail(String nick, boolean isSpam) {
		
		String sql = "select count(*) from mail where mail_receiver=? and mail_read='안읽음' and RECEIVER_POSITION";
		if (isSpam) sql+="='spam'";
		else sql+="='index'";
		return jdbcTemplate.queryForObject(sql, new Object[] {nick}, Integer.class);
	}
	
	public int maxLength(String nick, String box) {
		String sql = "select count(*) from mail ";
		
		int data_length;
		switch(box) {
		case "spam":
			sql+= "where mail_receiver=? and receiver_position=?";
			data_length = jdbcTemplate.queryForObject(sql, new Object[] {nick, box},Integer.class);
			break;
		case "sent":
			sql+="where mail_writer=? and writer_position=?";
			data_length = jdbcTemplate.queryForObject(sql, new Object[] {nick, box},Integer.class);
			break;
		case "protect":
		case "garbage":
			sql+="where mail_receiver=? and receiver_position=? or mail_writer=? and writer_position=?";
			data_length = jdbcTemplate.queryForObject(sql, new Object[] {nick, box, nick, box},Integer.class);
			break;
		default:
			return defaultList(nick, 0).size();
		}
		
		return data_length;
	}
	
	public boolean spam(String mynick, String target) {
		String sql = "select spam from member where nick=?";
		String spam = jdbcTemplate.queryForObject(sql, new Object[] {mynick}, String.class);
		
		if(spam!=null&&spam.indexOf(target)>=0) return false;
		
		target = (spam!=null)?spam+"-"+target:target;
		
		sql = "update member set spam=? where nick=?";
		return jdbcTemplate.update(sql, new Object[] {target, mynick})>0;
	}
}
