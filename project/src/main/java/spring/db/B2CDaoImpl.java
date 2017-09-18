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
		B2CDto dto=new B2CDto();
		dto.setId(rs.getString("id"));
		dto.setType(rs.getString("type"));
		dto.setTitle(rs.getString("title"));
		dto.setDetail(rs.getString("detail"));
		return dto;
		
		};
	String sql="select * from b2c where no=?";
	List<B2CDto> list= jdbcTemplate.query(sql,new Object[] {no}, mapper);
	return list.get(0);
}
@Override
public void rinsert(RB2CDto rdto) {
	String sql="insert into rb2c values(?,?,?,sysdate)";
	jdbcTemplate.update(sql, new Object[] {rdto.getNo(),rdto.getNick(),rdto.getDetail()});
	sql="update b2c set state='답변완료' where no=?";
	jdbcTemplate.update(sql, new Object[] {rdto.getNo()});
}
@Override
public RB2CDto rdetail(int no) {
	RowMapper<RB2CDto> mapper=(rs,index)->{
		RB2CDto dto=new RB2CDto(rs);
		return dto;
		
		};
	String sql="select * from rb2c where no=?";
	List<RB2CDto> list= jdbcTemplate.query(sql,new Object[] {no}, mapper);
	if(list.size()==0) {
		return null;
	}
	return list.get(0);
}

}
