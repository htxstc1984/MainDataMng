package com.itg.maindata.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the sy_user database table.
 * 
 */
@Entity
@Table(name = "sy_user")
public class SyUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pk_user", columnDefinition = "uniqueidentifier")
	private String pkUser = UUID.randomUUID().toString();

	private String disable;

	private String password;

	private String userid;

	private String username;

	public SyUser() {
	}

	public String getPkUser() {
		return this.pkUser;
	}

	public void setPkUser(String pkUser) {
		this.pkUser = pkUser;
	}

	public String getDisable() {
		return this.disable;
	}

	public void setDisable(String disable) {
		this.disable = disable;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}