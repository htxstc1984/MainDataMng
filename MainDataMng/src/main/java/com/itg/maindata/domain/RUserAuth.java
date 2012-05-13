package com.itg.maindata.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;


/**
 * The persistent class for the r_user_auth database table.
 * 
 */
@Entity
@Table(name="r_user_auth")
public class RUserAuth implements Serializable {
	private static final long serialVersionUID = 1L;
	private String pkRUserAuth = UUID.randomUUID().toString();
	private String pkAuthority;
	private String pkUser;

    public RUserAuth() {
    }


	@Id
	@Column(name="pk_r_user_auth")
	public String getPkRUserAuth() {
		return this.pkRUserAuth;
	}

	public void setPkRUserAuth(String pkRUserAuth) {
		this.pkRUserAuth = pkRUserAuth;
	}


	@Column(name="pk_authority")
	public String getPkAuthority() {
		return this.pkAuthority;
	}

	public void setPkAuthority(String pkAuthority) {
		this.pkAuthority = pkAuthority;
	}


	@Column(name="pk_user")
	public String getPkUser() {
		return this.pkUser;
	}

	public void setPkUser(String pkUser) {
		this.pkUser = pkUser;
	}

}