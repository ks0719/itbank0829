package spring.db.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Repository("memberDao")
public class MemberDao {

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
}
