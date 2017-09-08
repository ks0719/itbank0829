package spring.db.member;

import java.util.List;

import javax.servlet.http.HttpSession;

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
		
		Object[]args=new Object[] {member.getId(),member.getPw(),member.getName(),member.getNickname(),member.getPhone(),
				member.getPost(),member.getAddr1(),member.getAddr2(),member.getSort()};
		
		jdbcTemplate.update(sql,args);
	}
	
	public List<Member> list(String id, String password){
		
		String sql="select*from member where id=? and pw=? order by reg desc";
		return jdbcTemplate.query(sql, new Object[] {id, password},mapper);
	}

	  public boolean idcheck(String id) {
	      
	      String sql = "select count(*) from member where id=?";
	      boolean result = jdbcTemplate.queryForObject(sql, new Object[] {id},Integer.class)>0;
	      
	      return result;
	   }
	  
	  public boolean nickcheck(String nickname) {
	      
	      String sql = "select count(*) from member where nick=?";
	      boolean result = jdbcTemplate.queryForObject(sql, new Object[] {nickname},Integer.class)>0;
	      
	      return result;
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
}
