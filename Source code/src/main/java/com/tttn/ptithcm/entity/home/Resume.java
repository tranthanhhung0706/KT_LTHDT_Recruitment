package com.tttn.ptithcm.entity.home;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.tttn.ptithcm.annotion.ValidateEntity;
import com.tttn.ptithcm.entity.common.BaseEntity;
import com.tttn.ptithcm.entity.common.Company;
import com.tttn.ptithcm.entity.common.Position;
import com.tttn.ptithcm.entity.common.User;


@Entity
@Table(name = "resume")
@EntityListeners(AuditingEntityListener.class)
public class Resume extends BaseEntity {

	

	private static final String DEFAULT_POSITION_STATE = "wait";

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "position_id")
	private Position position;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	@ValidateEntity(required = false)
	@Column(name = "state", nullable = false)
	private String state = DEFAULT_POSITION_STATE; 

	@Column(name = "interview")
	private Date interview;

	public Date getInterview() {
		return interview;
	}

	public void setInterview(Date interview) {
		this.interview = interview;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
