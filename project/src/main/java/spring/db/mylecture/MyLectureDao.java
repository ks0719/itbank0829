package spring.db.mylecture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("myLectureDao")
public class MyLectureDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<MyLecture> mapper = (rs, index)->{
		return new MyLecture(rs);
	};
	
	private static final int LENGTH = 10;
	
//	@Override
//	public List<Board> list(String type, String key, int start, int end) {
//		String sql = "select * from ("
//								+ "select rownum rn, TMP.* from("
//								+ "select * from springboard "
//								+ "where "+type+" like '%'||?||'%' order by no desc"	
//								+ ")TMP) "
//							+ "where rn between ? and ?";
//		Object[] args = new Object[] {key, start, end};
//		List<Board> list = jdbcTemplate.query(sql, args, mapper);
//		System.out.println(type);
//		System.out.println(key);
//		System.out.println(list.size());
//		return list;
//	}
	
	
	public List<MyLecture> list(String id, String box, int start, int end) throws Exception {
		String sql = "select * from ("
				+ "select rownum rn, TMP.* from("
				+ "select * from mylecture "
				+ "where id=?";
		
		switch(box) {
		case "all":
			break;
		case "index":
			sql += " and state='진행중'";
			break;
		case "comp":
			sql += " and state='수료'";
			break;
		case "eval":
			sql += " and eval='미평가'";
			break;
		case "wish":
			sql += " and wish='wish'";
			break;
		default:
			throw new Exception("404");
		}
				
		sql += 	" order by reg desc,state,time desc"	
				+ ")TMP) "
			+ "where rn between ? and ?";
		
		return jdbcTemplate.query(sql,new Object[]{id, start, end},mapper);
	}
	
}
