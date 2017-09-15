package spring.db.teacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.db.lecture.LectureInfo;

@Repository
public class TeacherDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<Teacher> mapper = (rs, index) -> {
		return new Teacher(rs);
	};

	private int count() {
		return jdbcTemplate.queryForObject("select count(*) from teacher", Integer.class);
	}

	public int count(String type, String key) {
		if (type == null || type == "" || key == null) return count();
		if (!(type.equals("sort") || type.equals("name"))) type = "sort";
		return jdbcTemplate.queryForObject("select count(*) from teacher where lower (" + type + ") like '%'||'"+ key +"'||'%'", Integer.class);
	}
	
	public List<Teacher> list(String standard, String sub1, String sub2, String type, String key, int start, int end) {
		if (sub1 == "" || sub2 == "") return list(type, key, start, end);
		if (type == null || type == "" || key == null) return list(standard, sub1, sub2, start, end);
		if (!(type.equals("sort") || type.equals("name"))) type = "sort";
		
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from teacher where lower (" + type + ") like '%'||?||'%' order by " + standard + ", " + sub1 + ", " + sub2 + ")"
				+ " TMP) where rn between ? and ?";
		
		Object[] args = {key, start, end};
		
		return jdbcTemplate.query(sql, args, mapper);
	}

	public List<Teacher> list(String standard, String sub1, String sub2, int start, int end) {
		if (sub1 == "" || sub2 == "") return list(start, end);
		System.out.println("standard : " + standard);
		System.out.println("sub1 : " + sub1);
		System.out.println("sub2 : " + sub2);
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from teacher order by " + standard + ", " + sub1 + ", " + sub2 + ")"
				+ " TMP) where rn between ? and ?";
		
		Object[] args = {start, end};

		return jdbcTemplate.query(sql, args, mapper);
	}

	public List<Teacher> list(String type, String key, int start, int end) {
		if (type == null || type == "" || key == null) return list(start, end);
		if (!(type.equals("sort") || type.equals("name"))) type = "sort";
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from teacher where lower (" + type + ") like '%'||?||'%' order by reg desc)"
				+ " TMP) where rn between ? and ?";
		
		Object[] args = {key, start, end};

		return jdbcTemplate.query(sql, args, mapper);
	}

	public List<Teacher> list(int start, int end) {
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from teacher order by reg desc)"
				+ " TMP) where rn between ? and ?";
		
		Object[] args = {start, end};

		return jdbcTemplate.query(sql, args, mapper);
	}

	public Teacher showOne(String nick) throws Exception {
		String sql = "select * from teacher where name = ?";
		
		List<Teacher> list = jdbcTemplate.query(sql, new Object[] {nick}, mapper);
		
		if (list.isEmpty()) throw new Exception("404");
		return list.get(0);
	}

	public boolean apply(Teacher teacher) {
		String sql = "insert into teacher values(?, ?, ?, ?, ?, ?, ?, ?, 0, 0, 0, sysdate, 'wait')";
		
		String[] extension = teacher.getPicture_type().split("/");
		String filename = teacher.getName() + "." + extension[extension.length - 1];
		
		Object[] args = {teacher.getName(), teacher.getSort(), teacher.getCareer(), teacher.getIntro(), 
				filename, teacher.getPicture_realname(), teacher.getPicture_type(), teacher.getPicture_size()};
		
		return jdbcTemplate.update(sql, args) > 0;
	}

	public boolean applycheck(String nick) {
		String sql = "select count(*) from teacher where name = ?";
		
		return jdbcTemplate.queryForObject(sql, new Object[] {nick}, Integer.class) > 0;
	}

}
