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
	
	//영구 삭제
	public boolean delete(String mail_writer) {
		
		String sql="delete from mail where mail_writer=?";
		
		int res=jdbcTemplate.update(sql, mail_writer);
		return res>0;
//		return jdbcTemplate.query(sql, new Object[] {mail_receiver,box},mapper);
	}
}
