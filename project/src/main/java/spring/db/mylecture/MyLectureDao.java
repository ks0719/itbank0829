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
			sql += " and state!='미결제'";
			break;
		case "comp":
			sql += " and state='수료'";
			break;
		case "eval":
			sql += " and eval='미평가' and state='수료'";
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
	
	public boolean insert(String nick, LectureInfo lecture, String wish) {
		if(wish.equals("wish")) {
			//wish가 "wish" 일 때
			String sql = "insert into mylecture values(?, ?, ?, ?, ?, ?, ?, ?, '미결제', ?, '미평가', ?)";
			
			Object[] args = {nick, lecture.getNo(), lecture.getTag(), lecture.getTitle(), lecture.getTeacher(), lecture.getTime(), 
					lecture.getType(), lecture.getPrice(), lecture.getPeriod(), wish};
			
			return jdbcTemplate.update(sql, args)>0;
		}else {
			//wish가 "" 일 때
			//이 강의가 예전에 찜하기를 한 강의인지 아닌지 확인해야함
			//그런데 귀찮으니 걍 다 찜을 먼저 하고 update를 실행
			wish(nick, lecture);
			String sql = "update mylecture set wish='', state='결제 완료' where no=? and id=?";
			return jdbcTemplate.update(sql, new Object[] {lecture.getNo(), nick})>0;
		}
	}
	
	public boolean wish(String nick, LectureInfo lecture) {
		String sql = "select * from mylecture where id = ? and no = ?";
		List<MyLecture> mylectures = jdbcTemplate.query(sql, new Object[] {nick, lecture.getNo()}, mapper);
		//찜하기가 완료됐거나 결제된 강의 일 경우
		if(mylectures.size()>0) return false;
		//등록이 안되있는 강의일 경우
		else return insert(nick,lecture, "wish");
	}

	public MyLecture select(int no, String nick) {
		String sql = "select * from mylecture where no=? and id=?";
		try {
			return jdbcTemplate.query(sql, new Object[] {no, nick}, mapper).get(0);
		}
		catch(Exception e) {
			return null;
		}
	}
	
}
