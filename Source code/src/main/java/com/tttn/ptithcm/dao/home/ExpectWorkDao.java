package com.tttn.ptithcm.dao.home;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tttn.ptithcm.entity.home.ExpectWork;

@Repository 
public interface ExpectWorkDao extends JpaRepository<ExpectWork, Long>{
	@Query(value="select * from expect_work where user_id = :id",nativeQuery=true)
	public ExpectWork findExpectWorkByUserId(@Param("id")Long id);

}
