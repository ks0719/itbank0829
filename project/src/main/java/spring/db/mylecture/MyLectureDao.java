package spring.db.mylecture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.db.lecture.LectureInfo;

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
	
	public int maxPage(String id, String box) {
		String sql = "select count(*) from mylecture where id=?";
		switch (box) {
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
		
		int data_length = jdbcTemplate.queryForObject(sql, new Object[] {id} ,Integer.class);
		return (data_length-1)/MY_LECTURE_LENGTH+1;
	}
	
	public int insert(String id, int no, LectureInfo lecture, String wish) {
		String sql = "insert into mylecture values(?, ?, ?, ?, ?, ?, ?, ?, '미결제', ?, '미평가', ?)";
		
		Object[] args = {id, lecture.getNo(), lecture.getTag(), lecture.getTitle(), lecture.getTeacher(), lecture.getTime(), 
				lecture.getType(), lecture.getPrice(), lecture.getOpen(), wish};
		
		return jdbcTemplate.update(sql, args);
	}
	
	public int wish(String id, int no, LectureInfo lecture) {
		String sql = "select * from mylecture where id = ? and no = ?";
		
		int result = jdbcTemplate.update(sql, new Object[] {id, no});
		
		if (result == 0) return insert(id, no, lecture, "wish");
		else return 0;
	}
	
}
