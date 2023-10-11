package com.tttn.ptithcm.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.ptithcm.dao.common.CompanyDao;
import com.tttn.ptithcm.entity.common.Company;

@Service
public class CompanyService {

	@Autowired
	private CompanyDao companyDao;
	
	
	public Company save(Company company)
	{
		return companyDao.save(company);
	}
	
	
	public Company find(Long id)
	{
		return companyDao.find(id);
	}
	
	
	public Company findByUserId(Long id)
	{
		return companyDao.findByUserId(id);
	}
	
	
	public Company findByCompanyName(String company_name)
	{
		return companyDao.findByCompanyName(company_name);
	}
	
	public List<Company> findAllCompanyList(int offset,int pageSize){
		return companyDao.findAllCompanyList(offset, pageSize);
	}
	
	
	public List<Company> findCompanyList(String state,int offset,int pageSize)
	{
		return companyDao.findCompanyList(state, offset, pageSize);
	}
	
	
	public List<Company> findCompanyListBySearchValue(String state,String search_value,int offset,int pageSize)
	{
		return companyDao.findCompanyListBySearchValue(state, search_value, offset, pageSize);
	}
	
	public int getCompanyTotal(String state){
		return companyDao.getCompanyTotal(state);
	}
	

	public int getCompanyTotalBySearchValue(String state,String search_value){
		return companyDao.getCompanyTotalBySearchValue(state,search_value);
	}
	
	
	public void delete(Long id) {
		companyDao.deleteById(id);
	}
	
	
	public long total() {
		return companyDao.count();
	}
}
