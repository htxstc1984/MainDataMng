package com.itg.maindata.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

import com.itg.maindata.annotation.ext.FieldType;

/**
 * The persistent class for the sy_menu database table.
 * 
 */
@Entity
@Table(name = "sy_menu")
public class SyMenu implements Serializable {
	private static final long serialVersionUID = 1L;
	private String pkMenu = UUID.randomUUID().toString();
	private String code;
	private String name;
	private String pkParent;
	private String url;
	private int isfolder;
	private int level;

	public SyMenu() {
	}

	@Id
	@Column(name = "pk_menu")
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

	@Column(name = "pk_parent")
	public String getPkParent() {
		return this.pkParent;
	}

	public void setPkParent(String pkParent) {
		this.pkParent = pkParent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@FieldType(inputType = "", value = "checkbox")
	public int getIsfolder() {
		return isfolder;
	}

	public void setIsfolder(int isfolder) {
		this.isfolder = isfolder;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}