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
@Table(name = "expect_work")
@EntityListeners(AuditingEntityListener.class)
public class ExpectWork extends BaseEntity {

	
	private static final String DEFAULT_TYPE = "full";

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ValidateEntity(required = false)
	@Column(name = "city")
	private String city;

	@ValidateEntity(required = false)
	@Column(name = "position")
	private String position;

	@ValidateEntity(required = false)
	@Column(name = "money")
	private String money;

	@ValidateEntity(required = false)
	@Column(name = "type")
	private String type = DEFAULT_TYPE;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
