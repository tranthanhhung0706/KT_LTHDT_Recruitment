package com.tttn.ptithcm.dao.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tttn.ptithcm.entity.common.Position;


@Repository 
public interface PositionDao extends JpaRepository<Position, Long>{

	@Query("select p from Position p where company_id = :id")
	public List<Position> findPositionByCompanyId(@Param("id")Long id);

	@Query(value="select * from position order by update_time desc limit :offset,:pageSize",nativeQuery=true)
	public List<Position> findAllPositionList(@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	@Query("select p from Position p where id = :id")
	public Position find(@Param("id")Long id);
		
	@Query("select p from Position p where state = :state and company_id = :id order by create_time desc")
	public List<Position> findPositionByCompanyIdAndState(@Param("id")Long id,@Param("state")String state);

	@Query(value="select count(*) from position where state = :state and company_id = :id",nativeQuery=true)
	int getPositionTotalByState(@Param("id")Long id,@Param("state")String state);
	
	@Query(value="select count(*) from position where state = :state",nativeQuery=true)
	int getPositionTotal(@Param("state")String state);
	
	@Query(value="select count(*) from position where state = :state and name like %:search_value%",nativeQuery=true)
	int getPositionTotalBySearchValue(@Param("state")String state,@Param("search_value")String search_value);
	
	@Query(value="select * from position where state = :state order by create_time desc limit :offset,:pageSize",nativeQuery=true)
	public List<Position> findPositionList(@Param("state")String state,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	@Query(value="select * from position where state = :state and name like %:search_value% order by create_time desc limit :offset,:pageSize",nativeQuery=true)
	public List<Position> findPositionListBySearchValue(@Param("state")String state,@Param("search_value")String search_value,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
}
