package spring.db.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<Comment> mapper = (rs, index) -> {
		return new Comment(rs);
	};

	public List<Comment> list(int no) {
		String sql = "select context from board where no = ?";
		
		int context = jdbcTemplate.queryForObject(sql, new Object[] {no}, Integer.class);
		
		sql = "select * from commentboard where topcontext = ? order by best desc, seq";
		
		return jdbcTemplate.query(sql, new Object[] {context}, mapper);
	}
	
	public Comment insert(String nick, Comment comment) {
		int seq = 0;
		String sql = "select max(seq) from commentboard where context = ?";
		seq = jdbcTemplate.queryForObject(sql, new Object[] {comment.getContext()}, Integer.class) == null ? 0 : jdbcTemplate.queryForObject(sql, new Object[] {comment.getContext()}, Integer.class);
		
		int no = jdbcTemplate.queryForObject("select comment_seq.nextval from dual", Integer.class);
		
		sql = "insert into commentboard values(?, ?, ?, ?, ?, ?, 0, 0, sysdate)";
		
		jdbcTemplate.update(sql, no, nick, comment.getDetail(), comment.getTopcontext(), comment.getContext(), seq + 1);
		
		return detail(no);
	}
	
	public Comment detail(int no) {
		String sql = "select * from commentboard where no = ?";
		
		List<Comment> list = jdbcTemplate.query(sql, new Object[] {no}, mapper);
		
		return list.get(0);
	}

	public Comment best(int no) {
		String sql = "update commentboard set best = best + 1 where no = ?";
		
		jdbcTemplate.update(sql, no);
		
		return detail(no);
	}

	public void delete(int context) {
		String sql = "delete commentboard where context = ?";
		
		jdbcTemplate.update(sql, context);
	}

	public void deleteOne(int commentNo) {
		String sql = "delete commentboard where no = ?";
		
		jdbcTemplate.update(sql, commentNo);
	}

}
