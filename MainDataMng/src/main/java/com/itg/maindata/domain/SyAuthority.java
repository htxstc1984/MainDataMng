package com.itg.maindata.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;


/**
 * The persistent class for the sy_authority database table.
 * 
 */
@Entity
@Table(name="sy_authority")
public class SyAuthority implements Serializable {
	private static final long serialVersionUID = 1L;
	private String pkAuthority = UUID.randomUUID().toString();
	private String bz;
	private String name;

    public SyAuthority() {
    }


	@Id
	@Column(name="pk_authority")
	public String getPkAuthority() {
		return this.pkAuthority;
	}

	public void setPkAuthority(String pkAuthority) {
		this.pkAuthority = pkAuthority;
	}


	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}