package com.tttn.ptithcm.dao.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tttn.ptithcm.entity.common.Company;

@Repository
public interface CompanyDao extends JpaRepository<Company, Long>{
	@Query("select c from Company c where id = :id")
	public Company find(@Param("id")Long id);
	
	@Query("select c from Company c where user_id = :id")
	public Company findByUserId(@Param("id")Long id);
	
	@Query("select c from Company c where name = :company_name")
	public Company findByCompanyName(String company_name);
	
	@Query(value="select * from company where state = :state limit :offset,:pageSize",nativeQuery=true)
	public List<Company> findCompanyList(@Param("state")String state,@Param("offset")int offset,@Param("pageSize")int pageSize);

	@Query(value="select * from company order by update_time desc limit :offset,:pageSize",nativeQuery=true)
	public List<Company> findAllCompanyList(@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	@Query(value="select * from company where state = :state and name like %:search_value% limit :offset,:pageSize",nativeQuery=true)
	public List<Company> findCompanyListBySearchValue(@Param("state")String state,@Param("search_value")String search_value,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	@Query(value="select count(*) from company where state = :state",nativeQuery=true)
	int getCompanyTotal(@Param("state")String state);

	@Query(value="select count(*) from company where state = :state and name like %:search_value%",nativeQuery=true)
	int getCompanyTotalBySearchValue(@Param("state")String state,@Param("search_value")String search_value);
}
