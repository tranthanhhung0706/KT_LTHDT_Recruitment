package com.tttn.ptithcm.dao.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tttn.ptithcm.entity.admin.OperaterLog;

@Repository
public interface OperaterLogDao extends JpaRepository<OperaterLog, Long> {
	@Query(value="select * from operater_log order by create_time desc limit :offset,:pageSize",nativeQuery=true)
	public List<OperaterLog> findOperaterLogList(int offset, int pageSize);
}
