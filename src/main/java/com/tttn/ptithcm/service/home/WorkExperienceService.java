package com.tttn.ptithcm.service.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.ptithcm.dao.home.WorkExperienceDao;
import com.tttn.ptithcm.entity.home.WorkExperience;

@Service
public class WorkExperienceService {

	@Autowired
	private WorkExperienceDao workExperienceDao;

	public WorkExperience save(WorkExperience workExperience) {
		return workExperienceDao.save(workExperience);
	}

	public WorkExperience findWorkExperienceByUserId(Long user_id) {
		return workExperienceDao.findWorkExperienceByUserId(user_id);
	}

	public List<WorkExperience> findAllWorkExperience() {
		return workExperienceDao.findAllWorkExperience();
	}

	public void delete(Long id) {
		workExperienceDao.deleteById(id);
	}

}
