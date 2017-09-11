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
		return jdbcTemplate.queryForObject("select count(*) from board where path = '" + path + "' and seq = 1", Integer.class);
	}

	public int count(String path, String type, String key) {
		if (type == "" || key == null) return count(path);
		return jdbcTemplate.queryForObject("select count(*) from board where path = '" + path + "' and lower (" + type + ") like '%'||'"+ key +"'||'%' and seq = 1", Integer.class);
	}

	private List<Board> list(String path, int start, int end) {
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from board where path = ? and seq = 1 order by no desc)"
				+ " TMP) where rn between ? and ?";
		
		return jdbcTemplate.query(sql, new Object[] {path, start, end}, mapper);
	}

	public List<Board> list(String path, String type, String key, int start, int end) {
		if (type == "" || key == null) return list(path, start, end);
		
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from board where path = ? and lower (" + type + ") like '%'||?||'%' and seq = 1 order by no desc)"
						+ " TMP) where rn between ? and ?";
		
		return jdbcTemplate.query(sql, new Object[] {path, key, start, end}, mapper);
	}

	public int write(String path, Board board, int context) {
		String sql = "select board_seq.nextval from dual";
		int no = jdbcTemplate.queryForObject(sql, Integer.class);
		
		int seq = 0;
		System.out.println("소속 : " + board.getContext());
		if (context > 0) {
			sql = "select max(seq) from board where no = ?";
			seq = jdbcTemplate.queryForObject(sql, new Object[] {context}, Integer.class);
		}
		
		String[] extension = board.getFiletype().split("/");
		String filename = no + "." + extension[extension.length - 1];
		
		sql = "insert into board values(?, ?, ?, ?, ?, ?, ?, ?, ?, null, 0, 0, 0, sysdate, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] {no, board.getWriter(), path, board.getHead(), board.getTitle(), board.getDetail(), 
				context > 0 ? context : no, seq + 1, context > 0 ? 1 : no, filename, board.getOriginfile(), board.getFiletype(), board.getFilesize()});
		
		return no;
	}

	public List<Board> detail(int no) {
		String sql = "select * from board where context = ? order by seq, best desc";
		
		List<Board> list = jdbcTemplate.query(sql, new Object[] {no}, mapper);
		
		return list;
	}

	public void edit(int no, Board board) {
		String sql = "update board set head = ?, title = ?, detail = ?, filename = ?, originfile = ?, filetype = ?, filesize = ? where no = ?";
		
		String[] extension = board.getFiletype().split("/");
		String filename = no + "." + extension[extension.length - 1];
		
		jdbcTemplate.update(sql, board.getHead(), board.getTitle(), board.getDetail(), filename, board.getOriginfile(), 
				board.getFiletype(), board.getFilesize(), no);
	}

	public void delete(int no) {
		String sql = "delete board where no = ? or context = ?";
		
		jdbcTemplate.update(sql, no, no);
	}
	
	public void readUp(int no) {
		String sql = "update board set read = read + 1 where no = ?";
		
		jdbcTemplate.update(sql, no);
	}

	public void best(int no) {
		String sql = "update board set best = best + 1 where no = ?";
		
		jdbcTemplate.update(sql, no);
	}

	public Board detailOne(int no) {
		String sql = "select * from board where no = ?";
		
		List<Board> list = jdbcTemplate.query(sql, new Object[] {no}, mapper);
		
		return list.get(0);
	}

}
