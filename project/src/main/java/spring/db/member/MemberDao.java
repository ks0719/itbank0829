package spring.db.member;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("memberDao")
public class MemberDao {
private Logger log=LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;

	RowMapper<Member> mapper = (rs, index) -> {
		return new Member(rs);
	};
	
	public void insert(Member member) {
		
		String sql="insert into member values(member_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 1, sysdate,'')";
		
		Object[]args=new Object[] {member.getId(),member.getPw(),member.getName(),member.getNick(),member.getPhone(),
				member.getPost(),member.getAddr1(),member.getAddr2(),member.getSort()};
		
		jdbcTemplate.update(sql,args);
	}
	
	public List<Member> list(String id, String password){
		
		String sql="select*from member where id=? and pw=? order by reg desc";
		return jdbcTemplate.query(sql, new Object[] {id, password},mapper);
	}

	  
	  public String logincheck(String id,String pw) {
		  String sql="select nick from member where id=? and pw=?";
		  String nick= jdbcTemplate.queryForObject(sql,new Object[] {id,pw}, String.class);
		 log.debug("nick="+nick);
		  return nick;
		 
	  }
	public boolean check(String column, String data) {
	
		String sql = "select count(*) from member where "+column+"=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {data},Integer.class)>0;
	}
	
	public boolean delete(String nick, String pw) {
		
		String sql = "delete member where nick = ? and pw=?";
		return jdbcTemplate.update(sql, new Object[] {nick,pw})>0;
	}
	public String edit(Member mb,String nick) {
		String sql="update member set nick=?,post=?,addr1=?,addr2=?,phone=? where nick=?";
		jdbcTemplate.update(sql,new Object[] {mb.getNick(),mb.getPost(),mb.getAddr1(),mb.getAddr2(),mb.getPhone(),nick});
		return mb.getNick();
	}
	

	public boolean changepw(String nick,String pw,String newpw) {
		String sql="select * from member where nick=? and pw=?";
		boolean result=jdbcTemplate.queryForObject(sql, new Object[] {nick,pw},Integer.class)>0;
		if(result) {
			sql="update member set pw=? where nick=? and pw=?";
			jdbcTemplate.update(sql, new Object[] {newpw,nick,pw});
			return true;
		}
		return false;
		
	}
	
	public boolean checklogin(String id, String pw) {
		
		String sql="select * from member where id=? and pw=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id,pw},Integer.class)>0;
	}
}
