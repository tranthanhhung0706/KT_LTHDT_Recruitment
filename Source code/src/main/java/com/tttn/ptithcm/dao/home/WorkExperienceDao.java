package com.tttn.ptithcm.dao.home;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tttn.ptithcm.entity.home.WorkExperience;

@Repository 
public interface WorkExperienceDao extends JpaRepository<WorkExperience, Long>{
	@Query(value="select * from work_experience where user_id = :id",nativeQuery=true)
	public WorkExperience findWorkExperienceByUserId(@Param("id") Long id);
	
	@Query(value="select * from work_experience",nativeQuery=true)
	public List<WorkExperience> findAllWorkExperience();

}
