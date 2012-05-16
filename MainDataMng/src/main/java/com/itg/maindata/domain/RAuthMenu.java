package com.itg.maindata.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

/**
 * The persistent class for the r_auth_menu database table.
 * 
 */
@Entity
@Table(name = "r_auth_menu")
public class RAuthMenu implements Serializable {
	private static final long serialVersionUID = 1L;
	private String pkRAuthMenu = UUID.randomUUID().toString();;
	private String pkAuthority;
	private String pkMenu;

	public RAuthMenu() {
	}

	@Id
	@Column(name = "pk_r_auth_menu")
	public String getPkRAuthMenu() {
		return this.pkRAuthMenu;
	}

	public void setPkRAuthMenu(String pkRAuthMenu) {
		this.pkRAuthMenu = pkRAuthMenu;
	}

	@Column(name = "pk_authority")
	public String getPkAuthority() {
		return this.pkAuthority;
	}

	public void setPkAuthority(String pkAuthority) {
		this.pkAuthority = pkAuthority;
	}

	@Column(name = "pk_menu")
	public String getPkMenu() {
		return this.pkMenu;
	}

	public void setPkMenu(String pkMenu) {
		this.pkMenu = pkMenu;
	}

}