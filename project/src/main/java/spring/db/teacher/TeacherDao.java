package spring.db.teacher;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.db.lecture.LectureInfo;
import spring.db.member.Member;

@Repository
public class TeacherDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<Teacher> mapper = (rs, index) -> {
		return new Teacher(rs);
	};
	
	RowMapper<Qna> mapper2 = (rs, index) -> {
		return new Qna(rs);
	};
	
	RowMapper<Assess> mapper3 = (rs, index) -> {
		return new Assess(rs);
	};

	private int count() {
		return jdbcTemplate.queryForObject("select count(*) from teacher", Integer.class);
	}

	public int count(String type, String key) {
		if (type == null || type == "" || key == null) return count();
		if (!(type.equals("sort") || type.equals("name"))) type = "sort";
		return jdbcTemplate.queryForObject("select count(*) from teacher where lower (" + type + ") like '%'||'"+ key +"'||'%' and state='active'", Integer.class);
	}
	
	public int count2(String type, String key) {
		if (type == null || type == "" || key == null) return count();
		if (!(type.equals("sort") || type.equals("name"))) type = "sort";
		return jdbcTemplate.queryForObject("select count(*) from teacher where lower (" + type + ") like '%'||'"+ key +"'||'%' and state='wait'", Integer.class);
	}
	
	public List<Teacher> list(String standard, String sub1, String sub2, String type, String key, int start, int end) {
		if (sub1 == "" || sub2 == "") return list(type, key, start, end);
		if (type == null || type == "" || key == null) return list(standard, sub1, sub2, start, end);
		if (!(type.equals("sort") || type.equals("name"))) type = "sort";
		
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from teacher where lower (" + type + ") like '%'||?||'%' and state='active' order by " + standard + ", " + sub1 + ", " + sub2 + ")"
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
				+ "select * from teacher where state='active' order by " + standard + ", " + sub1 + ", " + sub2 + ")"
				+ " TMP) where rn between ? and ?";
		
		Object[] args = {start, end};

		return jdbcTemplate.query(sql, args, mapper);
	}
	

	public List<Teacher> list(String type, String key, int start, int end) {
		if (type == null || type == "" || key == null) return list(start, end);
		if (!(type.equals("sort") || type.equals("name"))) type = "sort";
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from teacher where lower (" + type + ") like '%'||?||'%' and state='active' order by reg desc)"
				+ " TMP) where rn between ? and ?";
		
		Object[] args = {key, start, end};

		return jdbcTemplate.query(sql, args, mapper);
	}
	
	public List<Teacher> list2(String type, String key, int start, int end) {
		if (type == null || type == "" || key == null) return list2(start, end);
		if (!(type.equals("sort") || type.equals("name"))) type = "sort";
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from teacher where lower (" + type + ") like '%'||?||'%' and state='wait' order by reg desc)"
				+ " TMP) where rn between ? and ?";
		
		Object[] args = {key, start, end};

		return jdbcTemplate.query(sql, args, mapper);
	}

	public List<Teacher> list(int start, int end) {
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from teacher where state='active' order by reg desc)"
				+ " TMP) where rn between ? and ?";
		
		Object[] args = {start, end};

		return jdbcTemplate.query(sql, args, mapper);
	}
	
	public List<Teacher> list2(int start, int end) {
		String sql = "select * from (select rownum rn, TMP.* from ("
				+ "select * from teacher where state='wait' order by reg desc)"
				+ " TMP) where rn between ? and ?";
		
		Object[] args = {start, end};

		return jdbcTemplate.query(sql, args, mapper);
	}
	

	public Teacher showOne(int no) throws Exception {
		String sql = "select * from teacher where teacherno = ? and state='active'";
		
		List<Teacher> list = jdbcTemplate.query(sql, new Object[] {no}, mapper);
		
		if (list.isEmpty()) throw new Exception("404");
		return list.get(0);
	}

	public boolean apply(Teacher teacher, int teacherNo) {
		String sql = "insert into teacher values(?, ?, ?, ?, ?, ?, ?, ?, 0, 0, 0, sysdate, 'wait',?)";
		
		String filename = null;
		if (teacher.getPicture_type() != null) {
			String[] extension = teacher.getPicture_type().split("/");
			filename = "lecturer/" + teacher.getName() + "." + extension[extension.length - 1];
		}
		
		Object[] args = {teacher.getName(), teacher.getSort(), teacher.getCareer(), teacher.getIntro(), 
				filename, teacher.getPicture_realname(), teacher.getPicture_type(), teacher.getPicture_size(),teacherNo};
		
		return jdbcTemplate.update(sql, args) > 0;
	}

	public boolean applycheck(int no) {
		String sql = "select count(*) from teacher where teacherno = ?";
		
		return jdbcTemplate.queryForObject(sql, new Object[] {no}, Integer.class) > 0;
	}

	public void edit(Teacher teacher) {
		String sql = "update teacher set sort = ?, career = ?, intro = ?, picture_name = ?, picture_realname = ?, picture_type = ?, picture_size = ? where teacherno = ?";
		
		String filename = null;
		if (teacher.getPicture_type() != "") {
			String[] extension = teacher.getPicture_type().split("/");
			filename = "lecturer/" + teacher.getName() + "." + extension[extension.length - 1];
		}
		
		Object[] args = {teacher.getSort(), teacher.getCareer(), teacher.getIntro(), 
				filename, teacher.getPicture_realname(), teacher.getPicture_type(), teacher.getPicture_size(), teacher.getTeacherno()};

		jdbcTemplate.update(sql, args);
	}
	
	public List<Qna> qnaList(int no) {
		String sql = "select * from qna where no = ?";
		
		return jdbcTemplate.query(sql, new Object[] {no}, mapper2);
	}
	
	public List<Assess> assessList(int no) {
		String sql = "select * from assess where no = ?";
		
		return jdbcTemplate.query(sql, new Object[] {no}, mapper3);
	}
	
//	public void assess(int no, int grade) {
//		String sql = "select * from teacher where teacherno = (select teacherno from lecture_info where no = ?)";
//		
//		List<Teacher> list = jdbcTemplate.query(sql, new Object[] {no}, mapper);
//		Teacher teacher = list.get(0);
//		
//		double result = ((double)teacher.getGrade() * (teacher.getStudents() - 1) + grade) / list.getStudents();
//		
//		sql = "update teacher set grade = ? where teacherno = (select teacherno from lecture_info where no = ?)";
//		
//		jdbcTemplate.update(sql, result, no);
//	}
	
	public List<Teacher>list(){
		
		String sql="select * from teacher where state='wait'";
		return jdbcTemplate.query(sql, mapper);
	}
	
	public void stateedit(String nick) {
		
		String sql="update teacher set state='active' where name=?";
		jdbcTemplate.update(sql,new Object[] {nick});
		sql="update member set power='강사' where nick=?";
		jdbcTemplate.update(sql,new Object[] {nick});
	}
	
	public void stateedit2(int no) {
		
		String sql="update teacher set state='active' where teacherno=?";
		jdbcTemplate.update(sql, new Object[] {no});
		sql="update member set power='강사' where no=?";
		jdbcTemplate.update(sql, new Object[] {no});
	}
	
	public void teachernotapply(String nick) {
		
		String sql="delete teacher where name = ?";
		jdbcTemplate.update(sql, new Object[] {nick});
	}
	
	public void notaccept(int no) {
		
		String sql="delete teacher where teacherno=?";
		jdbcTemplate.update(sql, new Object[] {no});
	}

	public void update(String originNick, String nick) {
		String sql = "update teacher set name = ? where name = ?";
		
		jdbcTemplate.update(sql, new Object[] {nick, originNick});
	}

	public boolean isRight(int no, int memberNo) {
		String sql = "select * from teacher where no = ? and memberNo = ?";
		
		return jdbcTemplate.query(sql, new Object[] {no, memberNo}, mapper).size() > 0;
	}
	
	
	public List<Teacher> detail(int teacherno) {
		
		String sql="select*from teacher where teacherno=?";
		List<Teacher>list=jdbcTemplate.query(sql, new Object[] {teacherno},mapper);
		return list;
	}
	
}
