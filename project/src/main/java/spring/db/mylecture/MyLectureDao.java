package spring.db.mylecture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("myLectureDao")
public class MyLectureDao {
	public static final int MY_LECTURE_LENGTH = 10;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<MyLecture> mapper = (rs, index)->{
		return new MyLecture(rs);
	};
	
	public List<MyLecture> list(String id, String box, int pageno) throws Exception {
		int start = (pageno-1)*MY_LECTURE_LENGTH+1;
		int end = start+MY_LECTURE_LENGTH-1;
		
		String sql = "select * from ("
				+ "select rownum rn, TMP.* from("
				+ "select * from mylecture "
				+ "where id=?";
		
		switch(box) {
		case "all":
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
			sql += " and state='진행중'";
		}
				
		sql += 	" order by reg desc,state,time desc"	
				+ ")TMP) "
			+ "where rn between ? and ?";
		
		return jdbcTemplate.query(sql,new Object[]{id, start, end},mapper);
	}
	
}