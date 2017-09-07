package spring.db.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<Reply> mapper = (rs, index) -> {
		return new Reply(rs);
	};

	public List<Reply> list(int no) {
		String sql = "select * from reply where context = ?";
		
		return jdbcTemplate.query(sql, new Object[] {no}, mapper);
	}

}
