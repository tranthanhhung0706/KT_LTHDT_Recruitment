package com.tttn.ptithcm.dao.home;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tttn.ptithcm.entity.home.Resume;

@Repository
public interface ResumeDao extends JpaRepository<Resume, Long>{

	@Query("select r from Resume r where user_id = :user_id and position_id = :position_id")
	public Resume findByPositionIdAndUserId(@Param("user_id")Long user_id,@Param("position_id")Long position_id);
	
	@Query("select r from Resume r where position_id = :position_id")
	public List<Resume> findByPositionId(@Param("position_id")Long position_id);
	
	@Query("select r from Resume r where user_id = :user_id order by update_time desc")
	public List<Resume> findByUserId(@Param("user_id")Long user_id);
	
	@Query("select r from Resume r where user_id = :user_id and state = :state order by update_time desc")
	public List<Resume> findByUserIdAndState(@Param("user_id")Long user_id,@Param("state")String state);
	
	@Query("select r from Resume r where company_id = :company_id and state = :state order by create_time desc")
	public List<Resume> findByCompanyIdAndState(@Param("company_id")Long company_id,@Param("state")String state);
	
	@Query("select r from Resume r where company_id = :company_id")
	public List<Resume> findByCompanyId(@Param("company_id")Long company_id);

	@Query("select r from Resume r where id = :id")
	public Resume findByResumeId(@Param("id")Long id);

	@Query(value="SELECT COUNT(*)  FROM resume WHERE state = :state AND company_id = :id",nativeQuery=true)
	int getPositionTotalByStateAndCompanyId(@Param("id") Long id, @Param("state") String state);
	
}
