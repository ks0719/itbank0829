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
	
	public int insert(LectureInfo info) {
		String sql = "select lecture_info_seq.nextval from dual";
		
		int no = jdbcTemplate.queryForObject(sql, Integer.class);
		
		sql = "insert into lecture_info values(?, ?, ?, ?, ?, ?, ?, '등록 가능', 0, 0, 0, ?, ?, ?, ?, ?, ?, 'false', sysdate, ?, ?)";
		
		String filename = null;
		if (info.getPicture_type() != null) {
			String[] extension = info.getPicture_type().split("/");
			filename = "lecture" + no + "." + extension[extension.length - 1];
		}
		
		Object[] args = new Object[] {no, info.getTag(), info.getTitle(), info.getTeacher(), info.getTime(), info.getType(), 
				info.getPrice(), filename, info.getPicture_realname(), info.getPicture_type(), 
				info.getPicture_size(), info.getIntro(), info.getDetail(), info.getPeriod(), info.getTeacherno()};
		
		jdbcTemplate.update(sql, args);
		
		return no;
	}

	public void update(String originNick, String nick) {
		String sql = "update lecture_info set teacher = ? where teacher = ?";
		
		jdbcTemplate.update(sql, new Object[] {nick, originNick});
	}
	
	public boolean delete(int no) {
		String sql = "delete lecture_info where no = ?";
		
		return jdbcTemplate.update(sql, no) > 0;
	}
	
	public LectureInfo showOne(int no) throws Exception {
		String sql = "select * from lecture_info where state = '등록 가능' and accept = 'true' and no = ?";
		
		List<LectureInfo> list = jdbcTemplate.query(sql, new Object[] {no}, mapper);
		
		if (list.isEmpty()) throw new Exception("404");
		return list.get(0);
	}
	
	public LectureInfo teacherShowOne(int no, String nick) throws Exception {
		String sql = "select * from lecture_info where teacher = ? and no = ?";
		
		List<LectureInfo> list = jdbcTemplate.query(sql, new Object[] {nick, no}, mapper);
		
		if (list.isEmpty()) throw new Exception("404");
		return list.get(0);
	}
	
	public int count() {
		return jdbcTemplate.queryForObject("select count(*) from lecture_info where state = '등록 가능' and accept = 'true'", Integer.class);
	}
	
	public int count(String search, String key) {
		if (search == "" || key == null) return count();
		return jdbcTemplate.queryForObject("select count(*) from lecture_info where state = '등록 가능' and accept = 'true' and lower (" + search + ") like '%'||'"+ key +"'||'%'", Integer.class);
	}
	
	public List<LectureInfo> list(int start, int end) {
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from lecture_info where state = '등록 가능' and accept = 'true' order by no desc)"
				+ " TMP) where rn between ? and ?";
		
		return jdbcTemplate.query(sql, new Object[] {start, end}, mapper);
	}
	
	public List<LectureInfo> list(String search, String key, int start, int end) {
		if (search == "" || key == null) return list(start, end);
		
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from lecture_info where state = '등록 가능' and accept = 'true' and lower (" + search + ") like '%'||?||'%' order by no desc)"
						+ " TMP) where rn between ? and ?";
		
		return jdbcTemplate.query(sql, new Object[] {key, start, end}, mapper);
	}
	
	public int teacherCount(String nick) {
		return jdbcTemplate.queryForObject("select count(*) from lecture_info where teacher = ?", new Object[] {nick}, Integer.class);
	}
	
	public int teacherCount(String nick, String search, String key) {
		if (search == "" || key == null) return teacherCount(nick);
		return jdbcTemplate.queryForObject("select count(*) from lecture_info where teacher = ? and " + search + " like '%'||?||'%'", new Object[] {nick, key}, Integer.class);
	}
	
	public List<LectureInfo> teacherList(String nick, int start, int end) throws Exception {
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from lecture_info where teacher = ? order by no desc)"
				+ " TMP) where rn between ? and ?";
		
		return jdbcTemplate.query(sql, new Object[] {nick, start, end}, mapper);
	}
	
	public List<LectureInfo> teacherList(String nick, String search, String key, int start, int end) throws Exception {
		if (search == "" || key == null) return teacherList(nick, start, end);
		
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from lecture_info where teacher = ? and " + search + " like '%'||?||'%' order by no desc)"
						+ " TMP) where rn between ? and ?";
		
		return jdbcTemplate.query(sql, new Object[] {nick, key, start, end}, mapper);
	}

	public void edit(LectureInfo lectureInfo) {
		String sql = "update lecture_info set tag = ?, title = ?, time = ?, type = ?, price = ?, intro = ?, detail = ?,"
				+ " period = ?, picture_name = ?, picture_realname = ?, picture_type = ?, picture_size = ?, accept = 'false' where no = ?";

		String filename = lectureInfo.getPicture_name();
		if (lectureInfo.getPicture_type() != null) {
			String[] extension = lectureInfo.getPicture_type().split("/");
			filename = "lecture" + lectureInfo.getNo() + "." + extension[extension.length - 1];
		}
		
		Object[] args = {lectureInfo.getTag(), lectureInfo.getTitle(), lectureInfo.getTime(), lectureInfo.getType(),
				lectureInfo.getPrice(), lectureInfo.getIntro(), lectureInfo.getDetail(), lectureInfo.getPeriod(), 
				filename, lectureInfo.getPicture_realname(), lectureInfo.getPicture_type(), lectureInfo.getPicture_size(), 
				lectureInfo.getNo()};
		
		jdbcTemplate.update(sql, args);
	}

}
