package com.itg.maindata.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;


/**
 * The persistent class for the md_department database table.
 * 
 */
@Entity
@Table(name="md_department")
public class MdDepartment implements Serializable {
	private static final long serialVersionUID = 1L;
	private String pkMdDepartment = UUID.randomUUID().toString();
	private String deptid;
	private String deptname;
	private String fcflag;
	private String pkParent;
	private int sort;

    public MdDepartment() {
    }


	@Id
	@Column(name="pk_md_department")
	public String getPkMdDepartment() {
		return this.pkMdDepartment;
	}

	public void setPkMdDepartment(String pkMdDepartment) {
		this.pkMdDepartment = pkMdDepartment;
	}


	public String getDeptid() {
		return this.deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}


	public String getDeptname() {
		return this.deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}


	public String getFcflag() {
		return this.fcflag;
	}

	public void setFcflag(String fcflag) {
		this.fcflag = fcflag;
	}


	@Column(name="pk_parent")
	public String getPkParent() {
		return this.pkParent;
	}

	public void setPkParent(String pkParent) {
		this.pkParent = pkParent;
	}


	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}