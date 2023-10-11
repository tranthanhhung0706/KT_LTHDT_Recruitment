package com.tttn.ptithcm.dao.home;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tttn.ptithcm.entity.home.WorkShow;

@Repository 
public interface WorkShowDao extends JpaRepository<WorkShow, Long>{
	@Query(value="select * from work_show where user_id = :id",nativeQuery=true)
	public WorkShow findWorkShowByUserId(@Param("id")Long id);
}
