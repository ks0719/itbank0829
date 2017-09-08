package spring.db.myinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("myDao")
public class MyDaoImpl implements MyDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public MyDto select(String nick) {
		RowMapper<MyDto> mapper=(rs,index)->{
			return new MyDto(rs);
		};
		String sql="select * from member where nick=?";
		List<MyDto> list=jdbcTemplate.query(sql,new Object[] {nick}, mapper);
		return list.get(0);
	}

}
