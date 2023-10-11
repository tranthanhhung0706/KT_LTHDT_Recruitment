package com.tttn.ptithcm.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.tttn.ptithcm.annotion.ValidateEntity;


@Entity
@Table(name="position_category")
@EntityListeners(AuditingEntityListener.class)  
public class PositionCategory extends BaseEntity{

	
	@ManyToOne  
	@JoinColumn(name="parent_id")
	private PositionCategory parentId;//Menu Phân loại cha
	
	private Long _parentId;
	
	@ValidateEntity(required = true, requiredMaxLength = true, requiredMinLength = true, minLength = 1, maxLength = 50, errorRequiredMsg = "Tên danh mục công việc không thể trống", 
	errorMinLengthMsg="Độ dài tên loại công việc phải lớn hơn 0",errorMaxLengthMsg="Độ dài tên loại công việc không thể lớn hơn 50")
	@Column(name="name",nullable=false,length=50)
	private String name;//Tên loại công việc
	
	@ValidateEntity(required=false)
	@Column(name="url",length=128)
	private String url;//URL loại công việc

	

	public PositionCategory getParentId() {
		return parentId;
	}

	public void setParentId(PositionCategory parentId) {
		this.parentId = parentId;
	}

	public Long get_parentId() {
		return _parentId;
	}

	public void set_parentId(Long _parentId) {
		this._parentId = _parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "PositionCategory [parentId=" + parentId + ", _parentId=" + _parentId + ", name=" + name + ", url=" + url
				+ "]";
	}
	
}
