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

	public List<Mail> list(String mail_receiver, String box){
		String sql="select * from mail";
		switch(box) {
			//무관
		case "protect":
		case "garbage":
			sql+=" where mail_position=? or mail_receiver=? and mail_position=? order by mail_reg desc, no desc";
			return jdbcTemplate.query(sql, new Object[] {mail_receiver,mail_receiver,box},mapper);
			
			//팀원이랑 상의
		case "spam":
			//보낸 사람이 나일때
		case "sent":
			sql+=" where mail_writer=? and mail_position=? order by mail_reg desc, no desc";
			break;
			//받는 사람이 나일때  or box가 없을때
		default:
			sql+=" where mail_receiver=? and mail_position='index' order by mail_reg desc, no desc";
			return jdbcTemplate.query(sql, new Object[] {mail_receiver},mapper);
		}
//		"select * from mail where mail_writer=? order by mail_reg desc"
		return jdbcTemplate.query(sql, new Object[] {mail_receiver,box},mapper);
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
	
	public Mail select(String id, int no) {
		String sql = "select * from mail where mail_receiver=? and no=?";
		return jdbcTemplate.query(sql, new Object[] {id, no}, mapper).get(0);
	}
	
	public String location(int no) {
		String sql = "select mail_position from mail where no=?";
		return jdbcTemplate.queryForObject(sql,new Object[] {no}, String.class);
	}
}
