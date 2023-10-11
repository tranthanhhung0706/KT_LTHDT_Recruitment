package com.tttn.ptithcm.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.ptithcm.dao.common.PositionCategoryDao;
import com.tttn.ptithcm.entity.common.PositionCategory;


@Service
public class PositionCategoryService {

	@Autowired
	private PositionCategoryDao positionCategoryDao;
	

	public PositionCategory save(PositionCategory positionCategory)
	{
		return positionCategoryDao.save(positionCategory);
	}
	
	
	

	

	public long total(){
		return positionCategoryDao.count();
	}

	public List<PositionCategory> findAll()
	{
		return positionCategoryDao.findAll();
	}
	

	public PositionCategory find(Long id)
	{
		return positionCategoryDao.find(id);
	}
	


	public void delete(Long id){
		positionCategoryDao.deleteById(id);
	}
}
