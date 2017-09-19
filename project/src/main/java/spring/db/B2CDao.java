package spring.db;

import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface B2CDao {
	void insert(B2CDto dto);
	List<B2CDto> list(String id);
	B2CDto detail(int no);
	void rinsert(RB2CDto rdto);
	RB2CDto rdetail(int no);
}
