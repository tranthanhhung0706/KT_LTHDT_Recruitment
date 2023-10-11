package com.tttn.ptithcm.service.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.ptithcm.dao.home.ExpectWorkDao;
import com.tttn.ptithcm.entity.home.ExpectWork;

@Service
public class ExpectWorkService {

	@Autowired
	private ExpectWorkDao expectWorkDao;

	public ExpectWork save(ExpectWork expectWork) {
		return expectWorkDao.save(expectWork);
	}

	public ExpectWork findExpectWorkByUserId(Long user_id) {
		return expectWorkDao.findExpectWorkByUserId(user_id);
	}

	public void delete(Long id) {
		expectWorkDao.deleteById(id);
	}

}
