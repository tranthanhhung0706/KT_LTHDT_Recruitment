package com.tttn.ptithcm.entity.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.tttn.ptithcm.entity.common.BaseEntity;

@Entity
@Table(name="operater_log")
@EntityListeners(AuditingEntityListener.class)  
public class OperaterLog extends BaseEntity{

	@Column(name="operator",nullable=false,length=18)
	private String operator;
	
	@Column(name="content",nullable=false,length=512)
	private String content;

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
