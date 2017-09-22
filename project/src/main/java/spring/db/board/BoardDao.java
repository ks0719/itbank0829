package spring.db.board;

import java.util.ArrayList;
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
		if (!(type.equals("head") || type.equals("title") || type.equals("writer"))) type = "head";
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
		if (!(type.equals("head") || type.equals("title") || type.equals("writer"))) type = "head";
		
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from board where path = ? and lower (" + type + ") like '%'||?||'%' and seq = 1 order by no desc)"
						+ " TMP) where rn between ? and ?";
		
		return jdbcTemplate.query(sql, new Object[] {path, key, start, end}, mapper);
	}

	public int write(String path, int memberNo, String nick, Board board, int context) {
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
		
		sql = "insert into board values(?, ?, ?, ?, ?, ?, ?, ?, ?, null, 0, 0, 0, sysdate, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] {no, nick, path, board.getHead(), board.getTitle(), board.getDetail(), 
				context > 0 ? context : no, seq + 1, context > 0 ? 1 : 0, filename, board.getOriginfile(), board.getFiletype(), board.getFilesize(), memberNo});
		
		return no;
	}

	public List<Board> detail(int no) {
		String sql = "select depth from board where no = ?";
		
		int depth = jdbcTemplate.queryForObject(sql, new Object[] {no}, Integer.class);
		
		if (depth != 0) return new ArrayList<Board>();
		
		sql = "select * from board where context = ? order by seq, best desc";
		
		List<Board> list = jdbcTemplate.query(sql, new Object[] {no}, mapper);
		
		return list;
	}

	public boolean edit(int no, int memberNo, Board board) {
		String sql = "update board set head = ?, title = ?, detail = ?, filename = ?, originfile = ?, filetype = ?, filesize = ? where no = ?, memberNo = ?";
		
		String[] extension = board.getFiletype().split("/");
		String filename = no + "." + extension[extension.length - 1];
		
		return jdbcTemplate.update(sql, board.getHead(), board.getTitle(), board.getDetail(), filename, board.getOriginfile(), 
				board.getFiletype(), board.getFilesize(), no, memberNo) > 0;
	}

	public boolean delete(int no) {
		String sql = "delete board where no = ? or context = ?";
		
		return jdbcTemplate.update(sql, no, no) > 0;
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

	public void update(String originNick, String nick) {
		String sql = "update board set writer = ? where writer = ?";
		
		jdbcTemplate.update(sql, new Object[] {nick, originNick});
	}

	public boolean isWriter(int no, int memberNo) {
		String sql = "select * from board where no = ? and memberNo = ?";
		
		return jdbcTemplate.query(sql, new Object[] {no, memberNo}, mapper).size() > 0;
	}

}
