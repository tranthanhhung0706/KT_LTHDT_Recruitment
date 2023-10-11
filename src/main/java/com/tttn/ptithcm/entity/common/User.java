package com.tttn.ptithcm.entity.common;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.tttn.ptithcm.annotion.ValidateEntity;

@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity{

	private static final int USER_SEX_UNKONW = 0;
	
	private static final String DEFAULT_WORK_EXPERIENCE = "Tốt nghiệp"; 
	private static final String DEFAULT_DEGREE = "khác"; 
	private static final String DEFAULT_HEAD_IMAGE = "common/default_img.jpg";

	
	@ValidateEntity(required = true, requiredMaxLength = true, requiredMinLength = true, minLength = 1, maxLength = 50, errorRequiredMsg = "Tên người dùng không thể trống", 
	errorMinLengthMsg="Độ dài của tên cần phải lớn hơn 0",errorMaxLengthMsg="Độ dài tên không thể lớn hơn 50")
	@Column(name="username",nullable=false,length=100)
	private String username;
	
	@ValidateEntity(required = true, requiredMaxLength = true, requiredMinLength = true, minLength = 6, maxLength = 100, errorRequiredMsg = "Mật khẩu người dùng không thể trống!", 
	errorMinLengthMsg="Độ dài mật khẩu cần phải lớn hơn 5",errorMaxLengthMsg="Độ dài mật khẩu không thể lớn hơn 100")
	@Column(name="Password",nullable=false,length=100)
	private String Password;
	
	@ValidateEntity(required=true,errorRequiredMsg="Địa chỉ hộp thư người dùng không thể trống!")
	@Column(name="Email",nullable=false)
	private String Email;//Địa chỉ hộp thư người dùng
	
	@ValidateEntity(required=false)
	@Column(name="head_pic",length=128)
	private String headPic = DEFAULT_HEAD_IMAGE;//ảnh đại diện
	
	@ValidateEntity(required=false)
	@Column(name="work_experience",length=10)
	private String workExperience = DEFAULT_WORK_EXPERIENCE ; //Kinh nghiệm làm việc: Mặc định là sinh viên tốt nghiệp mới
	
	@ValidateEntity(required=false)
	@Column(name="degree",length=10)
	private String degree = DEFAULT_DEGREE ; //Giáo dục: Mặc định là khác
	
	@ValidateEntity(required=false)
	@Column(name="sex",length=1)
	private int sex = USER_SEX_UNKONW;//Giới tính
	
	@ValidateEntity(required = true, requiredMaxLength = true, requiredMinLength = true, minLength = 10, maxLength = 11, 
	errorMinLengthMsg="Vui lòng nhập số điện thoại di động chính xác",errorMaxLengthMsg="Vui lòng nhập số điện thoại di động chính xác")
	@Column(name="mobile",length=12)
	private String mobile;//Số điện thoại di động của người dùng
	
	@ValidateEntity(required=false)
	@Column(name="type",length=1,nullable=false)
	private Long type;//Thể loại người dùng: 0: Ứng viên, 1: Nhà tuyển dụng
	
	@Lob
  @Basic(fetch = FetchType.LAZY)  
	private String content; 

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
	
	
}
