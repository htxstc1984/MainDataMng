package com.itg.maindata.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the r_user_auth database table.
 * 
 */
@Entity
@Table(name="r_user_auth")
public class RUserAuth implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_r_user_auth")
	private String pkRUserAuth;

	@Column(name="pk_authority")
	private String pkAuthority;

	@Column(name="pk_user")
	private String pkUser;

    public RUserAuth() {
    }

	public String getPkRUserAuth() {
		return this.pkRUserAuth;
	}

	public void setPkRUserAuth(String pkRUserAuth) {
		this.pkRUserAuth = pkRUserAuth;
	}

	public String getPkAuthority() {
		return this.pkAuthority;
	}

	public void setPkAuthority(String pkAuthority) {
		this.pkAuthority = pkAuthority;
	}

	public String getPkUser() {
		return this.pkUser;
	}

	public void setPkUser(String pkUser) {
		this.pkUser = pkUser;
	}

}