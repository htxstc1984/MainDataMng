package com.itg.maindata.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sy_menu database table.
 * 
 */
@Entity
@Table(name="sy_menu")
public class SyMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_menu")
	private String pkMenu;

	private String code;

	private String name;

	@Column(name="pk_parent")
	private String pkParent;

    public SyMenu() {
    }

	public String getPkMenu() {
		return this.pkMenu;
	}

	public void setPkMenu(String pkMenu) {
		this.pkMenu = pkMenu;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPkParent() {
		return this.pkParent;
	}

	public void setPkParent(String pkParent) {
		this.pkParent = pkParent;
	}

}