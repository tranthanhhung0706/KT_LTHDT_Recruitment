package com.tttn.ptithcm.dao.home;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tttn.ptithcm.entity.home.EducationBackground;

@Repository 
public interface EducationBackgroundDao extends JpaRepository<EducationBackground, Long>{

	@Query(value="select * from education_background where user_id = :id",nativeQuery=true)
	public EducationBackground findEducationBackgroundByUserId(@Param("id")Long id);
	
	@Query(value="select * from education_background",nativeQuery=true)
	public List<EducationBackground> findAllEducationBackground();
}
