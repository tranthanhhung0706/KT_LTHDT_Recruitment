package com.tttn.ptithcm.dao.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tttn.ptithcm.entity.common.PositionCategory;

@Repository 
public interface PositionCategoryDao extends JpaRepository<PositionCategory, Long>{
	@Query("select pc from PositionCategory pc where id = :id")
	public PositionCategory find(@Param("id")Long id);
}
