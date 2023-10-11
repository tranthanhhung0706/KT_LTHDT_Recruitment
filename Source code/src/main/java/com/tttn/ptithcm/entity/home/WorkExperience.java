package com.tttn.ptithcm.entity.home;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.tttn.ptithcm.annotion.ValidateEntity;
import com.tttn.ptithcm.entity.common.BaseEntity;
import com.tttn.ptithcm.entity.common.User;

@Entity
@Table(name="work_experience")
@EntityListeners(AuditingEntityListener.class)  
public class WorkExperience extends BaseEntity{

	

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ValidateEntity(required=true,errorRequiredMsg="Tên của công ty không thể trống！")
	@Column(name="name",nullable=false)
	private String name;  //Tên công ty
	
	@ValidateEntity(required=true,errorRequiredMsg="Tên của vị trí không thể trống!")
	@Column(name="position",nullable=false)
	private String position;  //Vị trí của công ty
	
	@ValidateEntity(required=true,errorRequiredMsg="Năm bắt đầu không thể trống")
	@Column(name="start_year",nullable=false)
	private String startYear;  
	
	@ValidateEntity(required=true,errorRequiredMsg="Năm kết thúc không thể trống")
	@Column(name="end_year",nullable=false)
	private String endYear;  
	
	@ValidateEntity(required=true,errorRequiredMsg="Tháng bắt đầu không thể trống")
	@Column(name="start_month",nullable=false)
	private String startMonth;  
	
	@ValidateEntity(required=true,errorRequiredMsg="Tháng kết thúc không thể trống")
	@Column(name="end_month",nullable=false)
	private String endMonth;  
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	
	
	
	

}
