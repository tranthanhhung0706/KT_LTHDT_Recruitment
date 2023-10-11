package com.tttn.ptithcm.dao.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tttn.ptithcm.entity.common.User;

@Repository 
public interface UserDao extends JpaRepository<User, Long>{

	@Query("select u from User u where email = :email")
	public User findByEmail(String email);
	
	@Query(value="select * from user order by update_time desc limit :offset,:pageSize",nativeQuery=true)
	public List<User> findAllUserList(@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	// @Query(value="SELECT u.* FROM user u WHERE u.id in (SELECT r.user_id FROM resume r WHERE r.id = :resumeId)", nativeQuery = true)
	// public User findUserByResumeId(@Param("resumeId") Long resumeId);

	@Query(value="CALL GetUserByResumeId(:resumeId);", nativeQuery = true)
	public User findUserByResumeId(@Param("resumeId") Long resumeId);
	
	public User findByUsername(String username);

	@Query("select u from User u where id = :id")
	public User find(@Param("id")Long id);
}
