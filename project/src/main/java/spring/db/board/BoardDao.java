package spring.db.board;

import java.util.List;

import org.springframework.stereotype.Repository;

import spring.db.lecture.LectureInfo;

@Repository
public class BoardDao {

	public int count(String type, String key) {
		return 0;
	}

	public List<LectureInfo> list(String type, String key, int start, int end) {
		return null;
	}

}
