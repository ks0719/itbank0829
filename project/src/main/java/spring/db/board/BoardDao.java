package spring.db.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<Board> mapper = (rs, index) -> {
		return new Board(rs);
	};

	private int count(String path) {
		return jdbcTemplate.queryForObject("select count(*) from board where path = '" + path + "'", Integer.class);
	}

	public int count(String path, String type, String key) {
		if (type == "" || key == null) return count(path);
		return jdbcTemplate.queryForObject("select count(*) from board where path = '" + path + "' and lower (" + type + ") like '%'||'"+ key +"'||'%'", Integer.class);
	}

	private List<Board> list(String path, int start, int end) {
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from board where path = ? order by no desc)"
				+ " TMP) where rn between ? and ?";
		
		return jdbcTemplate.query(sql, new Object[] {path, start, end}, mapper);
	}

	public List<Board> list(String path, String type, String key, int start, int end) {
		if (type == "" || key == null) return list(path, start, end);
		
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from board where path = ? and lower (" + type + ") like '%'||?||'%' order by no desc)"
						+ " TMP) where rn between ? and ?";
		
		return jdbcTemplate.query(sql, new Object[] {path, key, start, end}, mapper);
	}

	public int write(String path, Board board) {
		String sql = "select board_seq.nextval from dual";
		int no = jdbcTemplate.queryForObject(sql, Integer.class);
		
		sql = "insert into board values(?, ?, ?, ?, ?, ?, null, 0, 0, 0, sysdate)";
		jdbcTemplate.update(sql, new Object[] {no, board.getWriter(), path, board.getHead(), board.getTitle(), board.getDetail()});
		
		return no;
	}

	public Board detail(int no) {
		String sql = "select * from board where no = ?";
		
		List<Board> list = jdbcTemplate.query(sql, new Object[] {no}, mapper);
		
		return list.get(0);
	}

	public void edit(int no, Board board) {
		String sql = "update board set head = ?, title = ?, detail = ? where no = ?";
		
		jdbcTemplate.update(sql, board.getHead(), board.getTitle(), board.getDetail(), no);
	}

	public void delete(int noI) {
		String sql = "delete board where no = ?";
		
		jdbcTemplate.update(sql, noI);
	}

}
