package spring.db;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
@Repository("b2CDao")
public class B2CDaoImpl implements B2CDao{
	private Logger log=LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
public void insert(B2CDto dto) {
	String sql="select b2c_seq.nextval from dual";
	int no=jdbcTemplate.queryForObject(sql, Integer.class);
	sql="insert into b2c values(?,?,?,?,?,?,?,?,?,sysdate,'진행중')";
	jdbcTemplate.update(sql,no, dto.getId(),dto.getType(),dto.getTitle(),dto.getDetail(),
			dto.getFilename(),dto.getRealname(),dto.getFiletype(),dto.getFilesize());
}
@Override
public List<B2CDto> list(String id){
	RowMapper<B2CDto> mapper=(rs,index)->{
	return new B2CDto(rs);
	};
	String sql="select * from (select rownum rn, tmp.* from(select * from b2c order by reg desc)tmp)"
			+ "where id=? order by rn asc";
	List<B2CDto> list=jdbcTemplate.query(sql, new Object[] {id},mapper);
	return list;
}
@Override
public B2CDto detail(int no) {
	RowMapper<B2CDto> mapper=(rs,index)->{
		return new B2CDto(rs);
		};
	String sql="select * from b2c where no=?";
	return (B2CDto) jdbcTemplate.query(sql, mapper);
}
}