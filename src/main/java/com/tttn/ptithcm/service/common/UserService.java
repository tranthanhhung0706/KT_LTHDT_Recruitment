package com.tttn.ptithcm.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.ptithcm.dao.common.UserDao;
import com.tttn.ptithcm.entity.common.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	
	public User save(User user)
	{
		return userDao.save(user);
	}

	public User findByEmail(String email)
	{
		return userDao.findByEmail(email);
	}
	
	

	public User findByUsername(String username)
	{
		return userDao.findByUsername(username);
	}
	

	public List<User> findAllUserList(int offset,int pageSize){
		return userDao.findAllUserList(offset, pageSize);
	}
	
	

	public User find(Long id)
	{
		return userDao.find(id);
	}
	

	public long total() {
		return userDao.count();
	}

	public void delete(Long id) {
		userDao.deleteById(id);
	}

	public User findUserByResumeId(Long resumeId)
	{
			return userDao.findUserByResumeId(resumeId);
	}
}
