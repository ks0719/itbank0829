package spring.db.mylecture;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
	
	private RowMapper<MyLecture> mapper = (rs, index)->{
		return new MyLecture(rs);
	};
	
	private List<MyLecture> defaultLecture(String id, int pageno){
		SimpleDateFormat formatter = new SimpleDateFormat ("yy.MM.dd", Locale.KOREA );
		String date = formatter.format(new Date());
		
		int start, end;
		if(pageno==0) {
			start=1;
			end = Integer.MAX_VALUE;
		}else {
			start = (pageno-1)*MY_LECTURE_LENGTH+1;
			end = start+MY_LECTURE_LENGTH-1;
		}
		
		List<MyLecture> returnList = new ArrayList<>();
		
		String sql = "select * from ("
				+ "select rownum rn, TMP.* from("
				+ "select * from mylecture "
				+ "where id=? and state='결제 완료'"
				+ " order by period desc,state,time desc"	
				+ ")TMP) "
				+ "where rn between ? and ?";
		
		List<MyLecture> needSort = jdbcTemplate.query(sql,new Object[]{id, start, end},mapper);
		
		for(MyLecture lecture:needSort) {
			String period = lecture.getPeriod();
			String s = period.substring(0, period.indexOf("~"));
			String e = period.substring(period.indexOf("~")+1);
			if(s.compareTo(date)<0&&e.compareTo(date)>0) {
				returnList.add(lecture);
			}
		}
		return returnList;
	}
	
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
			return defaultLecture(id, pageno);
		}
				
		sql += 	" order by period desc,state,time desc"	
				+ ")TMP) "
			+ "where rn between ? and ?";
		
		return jdbcTemplate.query(sql,new Object[]{id, start, end},mapper);
	}
	
	public int maxLength(String id, String box) {
		String sql = "select count(*) from mylecture where id=?";
		switch (box) {
		case "all":
			sql += " and state!='미결제'";
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
			return defaultLecture(id, 0).size();
		}
		
		return jdbcTemplate.queryForObject(sql, new Object[] {id} ,Integer.class);
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
			System.out.println("no : " + lecture.getNo() + ", " + nick);
			String sql = "update mylecture set wish='', state='결제 완료' where no=? and id=?";
			return jdbcTemplate.update(sql, new Object[] {lecture.getNo(), nick})>0;
		}
	}
	
	public String wish(String nick, LectureInfo lecture) {
		String sql = "select * from mylecture where id = ? and no = ?";
		List<MyLecture> mylectures = jdbcTemplate.query(sql, new Object[] {nick, lecture.getNo()}, mapper);
		//찜하기가 완료됐거나 결제된 강의 일 경우
		if(mylectures.size()>0) return "이미 찜했거나 수강중인 강의 입니다.";
		//등록이 안되있는 강의일 경우
		else {
			SimpleDateFormat formatter = new SimpleDateFormat ("yy.MM.dd", Locale.KOREA );
			String date = formatter.format(new Date());
			String period = lecture.getPeriod();
			String s = period.substring(0, period.indexOf("~"));
			if(s.compareTo(date)<0) {
				//[1] 날짜가 등록할 수 없는 강의일경우 
				return "이미 시작되거나 종료된 강의 입니다.";
			}else {
				//[2] 그 외의 경우
				if(insert(nick,lecture, "wish")) return "찜하기가 완료되었습니다.";
				return null;
			}
		}
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

	public List<MyLecture> getStudents(int no) {
		String sql = "select * from mylecture where no = ?";
		
		return jdbcTemplate.query(sql, new Object[] {no}, mapper);
	}

	public void evalCount(int no, String nick) {
		String sql = "update mylecture set eval = '평가 완료' where no = ? and id = ?";
		
		jdbcTemplate.update(sql, no, nick);
	}
	
}
