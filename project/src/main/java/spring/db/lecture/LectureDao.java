package spring.db.lecture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LectureDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<LectureInfo> mapper = (rs, index) -> {
		return new LectureInfo(rs);
	};
	
	public boolean insert(LectureInfo info) {
		String sql = "insert into lecture_info values(lecture_info_seq.nextval, ?, ?, ?, ?, ?, ?, '등록 가능', 0, 0, 0, ?, ?, ?, 0, ?, ?, 'false', sysdate)";
		
		Object[] args = new Object[] {info.getTag(), info.getTitle(), info.getTeacher(), info.getTime(), info.getType(), 
				info.getPrice(), info.getPicture_name(), info.getPicture_realname(), info.getPicture_type(), 
				info.getPicture_size(), info.getIntro(), info.getDetail()};
		
		return jdbcTemplate.update(sql, args) > 0;
	}
	
	public boolean update(LectureInfo info) {
		String sql = "update lecture_info set ";
		
		return false;
	}
	
	public boolean delete(int no) {
		String sql = "delete lecture_info where no = ?";
		
		return jdbcTemplate.update(sql, no) > 0;
	}
	
	public LectureInfo showOne(int no) {
		String sql = "select * from lecture_info where state = '등록 가능' and accept = 'true' and no = ?";
		
		List<LectureInfo> list = jdbcTemplate.query(sql, new Object[] {no}, mapper);
		
		return list.get(0);
	}
	
	public int count() {
		return jdbcTemplate.queryForObject("select count(*) from lecture_info where state = '등록 가능' and accept = 'true'", Integer.class);
	}
	
	public int count(String type, String key) {
		if (type == null || key == null) return count();
		return jdbcTemplate.queryForObject("select count(*) from lecture_info where state = '등록 가능' and accept = 'true' and " + type + " like '%'||'"+ key +"'||'%'", Integer.class);
	}
	
	public List<LectureInfo> list(int start, int end) {
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from lecture_info where state = '등록 가능' and accept = 'true' order by no desc)"
				+ " TMP) where rn between ? and ?";
		
		return jdbcTemplate.query(sql, new Object[] {start, end}, mapper);
	}
	
	public List<LectureInfo> list(String type, String key, int start, int end) {
		if (type == null || key == null) return list(start, end);
		
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from lecture_info where state = '등록 가능' and accept = 'true' and lower (" + type + ") like '%'||?||'%' order by no desc)"
						+ " TMP) where rn between ? and ?";
		
		return jdbcTemplate.query(sql, new Object[] {key, start, end}, mapper);
	}

}
