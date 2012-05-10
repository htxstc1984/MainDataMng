package com.itg.maindata.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the r_psn_dept database table.
 * 
 */
@Entity
@Table(name="r_psn_dept")
public class RPsnDept implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_r_psn_dept")
	private String pkRPsnDept;

	@Column(name="pk_md_department")
	private String pkMdDepartment;

	@Column(name="pk_md_psn")
	private String pkMdPsn;

    public RPsnDept() {
    }

	public String getPkRPsnDept() {
		return this.pkRPsnDept;
	}

	public void setPkRPsnDept(String pkRPsnDept) {
		this.pkRPsnDept = pkRPsnDept;
	}

	public String getPkMdDepartment() {
		return this.pkMdDepartment;
	}

	public void setPkMdDepartment(String pkMdDepartment) {
		this.pkMdDepartment = pkMdDepartment;
	}

	public String getPkMdPsn() {
		return this.pkMdPsn;
	}

	public void setPkMdPsn(String pkMdPsn) {
		this.pkMdPsn = pkMdPsn;
	}

}