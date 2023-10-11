package com.tttn.ptithcm.service.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.ptithcm.dao.home.WorkShowDao;
import com.tttn.ptithcm.entity.home.WorkShow;

@Service
public class WorkShowService {

	@Autowired
	private WorkShowDao workShowDao;

	public WorkShow save(WorkShow WorkShow) {
		return workShowDao.save(WorkShow);
	}

	public WorkShow findWorkShowByUserId(Long user_id) {
		return workShowDao.findWorkShowByUserId(user_id);
	}

	public void delete(Long id) {
		workShowDao.deleteById(id);
	}
}
