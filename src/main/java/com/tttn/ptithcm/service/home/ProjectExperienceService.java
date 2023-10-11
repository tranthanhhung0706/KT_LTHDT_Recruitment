package com.tttn.ptithcm.service.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.ptithcm.dao.home.ProjectExperienceDao;
import com.tttn.ptithcm.entity.home.ProjectExperience;

@Service
public class ProjectExperienceService {

	@Autowired
	private ProjectExperienceDao projectExperienceDao;

	public ProjectExperience save(ProjectExperience projectExperience) {
		return projectExperienceDao.save(projectExperience);
	}

	public ProjectExperience findProjectExperienceByUserId(Long user_id) {
		return projectExperienceDao.findProjectExperienceByUserId(user_id);
	}

	public void delete(Long id) {
		projectExperienceDao.deleteById(id);
	}
}
