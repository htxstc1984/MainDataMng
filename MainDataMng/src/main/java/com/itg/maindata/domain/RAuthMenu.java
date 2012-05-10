package com.itg.maindata.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the r_auth_menu database table.
 * 
 */
@Entity
@Table(name="r_auth_menu")
public class RAuthMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_r_auth_menu")
	private String pkRAuthMenu;

	@Column(name="pk_authority")
	private String pkAuthority;

	@Column(name="pk_menu")
	private String pkMenu;

    public RAuthMenu() {
    }

	public String getPkRAuthMenu() {
		return this.pkRAuthMenu;
	}

	public void setPkRAuthMenu(String pkRAuthMenu) {
		this.pkRAuthMenu = pkRAuthMenu;
	}

	public String getPkAuthority() {
		return this.pkAuthority;
	}

	public void setPkAuthority(String pkAuthority) {
		this.pkAuthority = pkAuthority;
	}

	public String getPkMenu() {
		return this.pkMenu;
	}

	public void setPkMenu(String pkMenu) {
		this.pkMenu = pkMenu;
	}

}