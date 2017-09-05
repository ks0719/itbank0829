package spring.db.board;

import java.util.List;

import org.springframework.stereotype.Repository;

import spring.db.lecture.LectureInfo;

@Repository
public class BoardDao {

	private int count() {
		return 0;
	}

	public int count(String type, String key) {
		if (type == "" || key == null) return count();
		
		return 0;
	}

	private List<LectureInfo> list(int start, int end) {
		return null;
	}

	public List<LectureInfo> list(String type, String key, int start, int end) {
		if (type == "" || key == null) return list(start, end);
		
		return null;
	}

}
