package spring.db.myinfo;

import org.springframework.stereotype.Repository;

@Repository
public interface MyDao {
	MyDto select(String nick);
	
}
