package com.tttn.ptithcm.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tttn.ptithcm.dao.common.PositionDao;
import com.tttn.ptithcm.entity.common.Position;
@Service 
public class PositionService {

	@Autowired
	private PositionDao positionDao;
	

	public Position save(Position position)
	{
		return positionDao.save(position);
	}
	
	
	public List<Position> findAll(){
		return positionDao.findAll();
	}
	
	

	public List<Position> findPositionByCompanyId(Long id){
		return positionDao.findPositionByCompanyId(id);
	}

	public List<Position> findPositionByCompanyIdAndState(Long id,String state){
		return positionDao.findPositionByCompanyIdAndState(id, state);
	}
	
	

	public List<Position> findAllPositionList(int offset,int pageSize){
		return positionDao.findAllPositionList(offset,pageSize);
	}
	

	public int getPositionTotalByState(Long id,String state){
		return positionDao.getPositionTotalByState(id,state);
	}

	public int getPositionTotal(String state){
		return positionDao.getPositionTotal(state);
	}
	

	public int getPositionTotalBySearchValue(String state,String search_value){
		return positionDao.getPositionTotalBySearchValue(state,search_value);
	}
	
	

	public Position findById(Long id) {
		return positionDao.find(id);
	}
	

	public void delete(Long id) {
		positionDao.deleteById(id);
	}
	
	

	public List<Position> findPositionList(String state,int offset,int pageSize)
	{
		return positionDao.findPositionList(state, offset, pageSize);
	}
	

	public List<Position> findPositionListBySearchValue(String state,String search_value,int offset,int pageSize)
	{
		return positionDao.findPositionListBySearchValue(state,search_value,offset, pageSize);
	}
	
	
	public long total() {
		return positionDao.count();
	}

	
}
