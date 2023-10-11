package com.tttn.ptithcm.entity.common;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.tttn.ptithcm.annotion.ValidateEntity;

@Entity
@Table(name="position")
@EntityListeners(AuditingEntityListener.class)  
public class Position extends BaseEntity{

	private static final String DEFAULT_WORK_TYPE = "full"; 
	
	private static final String DEFAULT_POSITION_STATE = "wait"; 
	
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;//Vị trí thuộc công ty
	
	@ValidateEntity(required = true, requiredMaxLength = true, requiredMinLength = true, minLength = 1, maxLength = 18, errorRequiredMsg = "Loại công việc không thể trống", 
	errorMinLengthMsg="Độ dài loại công việc phải lớn hơn 0",errorMaxLengthMsg="Độ dài loại công việc không thể lớn hơn 18")
	@Column(name="type",nullable=false,length=18)
	private String type;//Danh mục Công việc
	
	@ValidateEntity(required = true, requiredMaxLength = true, requiredMinLength = true, minLength = 1, maxLength = 50, errorRequiredMsg = "Tên của vị trí không thể trống", 
	errorMinLengthMsg="Độ dài của tên công việc cần phải lớn hơn 0",errorMaxLengthMsg="Độ dài của tên công việc không thể lớn hơn 50")
	@Column(name="name",nullable=false,length=18)
	private String name;//Tiêu đề công việc
	
	@ValidateEntity(required=false)
	@Column(name="department")
	private String department;//Bộ công việc

	@ValidateEntity(required=false)
	@Column(name="work_type",nullable=false)
	private String workType = DEFAULT_WORK_TYPE;  //Bản chất của công việc
	
	@ValidateEntity(required = true, requiredMaxValue = true, requiredMinValue = true, minValue = 1, maxValue = 1000, 
	errorRequiredMsg="Mức lương tối thiểu phải có",errorMinValueMsg="Mức lương tối thiểu lớn hơn 0",errorMaxValueMsg="Mức lương tối thiểu không thể lớn hơn 1000")
	@Column(name="min_money",nullable=false)
	private int minMoney;  //Mức lương tối thiểu của vị trí (trong đơn vị)
	
	@ValidateEntity(required = true, requiredMaxValue = true, requiredMinValue = true, minValue = 1, maxValue = 1000, 
	errorRequiredMsg="Mức lương cao nhất không thể trống",errorMinValueMsg="Mức lương cao nhất phải lớn hơn 0",errorMaxValueMsg="Mức lương tối đa không thể lớn hơn 1000")
	@Column(name="max_money",nullable=false)
	private int maxMoney;  //Mức lương cao nhất của vị trí (trong đơn vị)
	
	@ValidateEntity(required=true,errorRequiredMsg="Thành phố công việc không thể trống!")
	@Column(name="city",nullable=false)
	private String city;  //Thành phố việc làm
	
	@ValidateEntity(required=true,errorRequiredMsg="Vị trí Yêu cầu kinh nghiệm làm việc không thể trống!")
	@Column(name="work_experience",nullable=false)
	private String workExperience;  //Yêu cầu kinh nghiệm làm việc
	
	@ValidateEntity(required=true,errorRequiredMsg="Yêu cầu giáo dục công việc không thể trống!")
	@Column(name="degree",nullable=false)
	private String degree;  //Yêu cầu giáo dục công việc
	
	@ValidateEntity(required=true,errorRequiredMsg="Yêu cầu của vị trí không thể trống rỗng!")
	@Column(name="advantage",nullable=false)
	private String advantage;  
	
	@ValidateEntity(required=false)
	@Column(name="state",nullable=false)
	private String state = DEFAULT_POSITION_STATE;  //Trạng thái vị trí
	
	@ValidateEntity(required=true,errorRequiredMsg="Mô tả công việc không thể trống!")
	@Lob
    @Basic(fetch = FetchType.LAZY)   
	private String description; //mô tả công việc
	
	@ValidateEntity(required=true,errorRequiredMsg="Địa chỉ công việc của công việc không thể trống!")
	@Column(name="address",nullable=false)
	private String address;  //Địa chỉ công việc
	
	@ValidateEntity(required=false)
	@Column(name="number")
	private int number = 0;  //Số lượng công việc tiếp tục công việc

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public int getMinMoney() {
		return minMoney;
	}

	public void setMinMoney(int minMoney) {
		this.minMoney = minMoney;
	}

	public int getMaxMoney() {
		return maxMoney;
	}

	public void setMaxMoney(int maxMoney) {
		this.maxMoney = maxMoney;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getAdvantage() {
		return advantage;
	}

	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
