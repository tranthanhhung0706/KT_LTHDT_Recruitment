package com.tttn.ptithcm.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tttn.ptithcm.dao.admin.OperaterLogDao;
import com.tttn.ptithcm.entity.admin.OperaterLog;
@Service
public class OperaterLogService {

	@Autowired
	private OperaterLogDao operaterLogDao;
	
	
	public OperaterLog save(OperaterLog operaterLog)
	{
		return operaterLogDao.save(operaterLog);
	}
	

	public List<OperaterLog> findOperaterLogList(int offset,int pageSize)
	{
		return operaterLogDao.findOperaterLogList(offset, pageSize);
	}
	
	
	public long total(){
		return operaterLogDao.count();
	}
	
	
	public void delete(Long id) {
		operaterLogDao.deleteById(id);
	}
	
}
