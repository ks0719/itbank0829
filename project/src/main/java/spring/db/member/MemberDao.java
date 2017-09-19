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
		
		String sql="insert into member values(member_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 1, sysdate,'','일반')";
		
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
	  public String mypwid(String id) {
		  String sql="select pw from member where id=?";
		  String pw=jdbcTemplate.queryForObject(sql, new Object[] {id},String.class);
		  return pw;
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
		sql="update mail set MAIL_WRITER=? where MAIL_WRITER=?";
		jdbcTemplate.update(sql,new Object[] {mb.getNick(),nick});
		sql="update mail set MAIL_RECEIVER=? where MAIL_RECEIVER=?";
		jdbcTemplate.update(sql,new Object[] {mb.getNick(),nick});
		
		return mb.getNick();
	}
	
	public Member select(String nick) {
		String sql = "select * from member where nick=?";
		return jdbcTemplate.query(sql, new Object[] {nick}, mapper).get(0);
	}
	public String mypwnick(String nick) {
		String sql="select pw from member where nick=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {nick},String.class);
	}
	public boolean changepw(String nick,String newpw) {
		String sql="select count(*) from member where nick=?";
		boolean result= jdbcTemplate.queryForObject(sql, new Object[] {nick},Integer.class)>0;
		if(result) {
			sql="update member set pw=? where nick=?";
			jdbcTemplate.update(sql, new Object[] {newpw,nick});
			return true;
		}
		return false;
		
	}

	public boolean update(String column, String value) {
		String sql = "update member set "+column+"=?";
		if(column.equals("no")||column.equals("mileage")||column.equals("lev")) 
			return jdbcTemplate.update(sql, new Object[] {Integer.parseInt(value)})>0;
		else
			return jdbcTemplate.update(sql, new Object[] {value})>0;
	}

//	public boolean checkpw(String nick, String pw) {
//		String sql="select*from member where nick=? and pw=?";
////		String sql="select nick from member where pw=? and nick=?";
////		return false;
//		return jdbcTemplate.queryForObject(sql, new Object[] {nick, pw},Integer.class)>0;
//	}
	
	public boolean checklogin(String id, String pw) {
		String sql="select * from member where id=? and pw=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id,pw},Integer.class)>0;
	}
	public int mypoint(String nick) {
		String sql="select MILEAGE from member where nick=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {nick},Integer.class);
		
	}
	public Member list(String nick){
		RowMapper<Member> mapper=(rs,index)->{
			return new Member(rs);
		};
		String sql="select * from member where nick=?";
		List<Member> list=null;
		list=jdbcTemplate.query(sql, new Object[] {nick},mapper);
		return list.get(0);
		
	}
}
