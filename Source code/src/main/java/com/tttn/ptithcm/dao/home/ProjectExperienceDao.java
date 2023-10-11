package com.tttn.ptithcm.dao.home;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tttn.ptithcm.entity.home.ProjectExperience;

@Repository 
public interface ProjectExperienceDao extends JpaRepository<ProjectExperience, Long>{
	@Query(value="select * from project_experience where user_id = :id",nativeQuery=true)
	public ProjectExperience findProjectExperienceByUserId(@Param("id")Long id);
}
