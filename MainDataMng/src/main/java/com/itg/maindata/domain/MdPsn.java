package com.itg.maindata.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the md_psn database table.
 * 
 */
@Entity
@Table(name="md_psn")
public class MdPsn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_md_psn")
	private String pkMdPsn;

	private String domainacc;

	private String email;

	private String nomngflag;

	private String oaacc;

	private String pcompany;

	@Column(name="pk_psnbasdoc")
	private String pkPsnbasdoc;

	private String pmobile;

	private String psncode;

	private String psnname;

	private String sex;

	private String visible;

    public MdPsn() {
    }

	public String getPkMdPsn() {
		return this.pkMdPsn;
	}

	public void setPkMdPsn(String pkMdPsn) {
		this.pkMdPsn = pkMdPsn;
	}

	public String getDomainacc() {
		return this.domainacc;
	}

	public void setDomainacc(String domainacc) {
		this.domainacc = domainacc;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomngflag() {
		return this.nomngflag;
	}

	public void setNomngflag(String nomngflag) {
		this.nomngflag = nomngflag;
	}

	public String getOaacc() {
		return this.oaacc;
	}

	public void setOaacc(String oaacc) {
		this.oaacc = oaacc;
	}

	public String getPcompany() {
		return this.pcompany;
	}

	public void setPcompany(String pcompany) {
		this.pcompany = pcompany;
	}

	public String getPkPsnbasdoc() {
		return this.pkPsnbasdoc;
	}

	public void setPkPsnbasdoc(String pkPsnbasdoc) {
		this.pkPsnbasdoc = pkPsnbasdoc;
	}

	public String getPmobile() {
		return this.pmobile;
	}

	public void setPmobile(String pmobile) {
		this.pmobile = pmobile;
	}

	public String getPsncode() {
		return this.psncode;
	}

	public void setPsncode(String psncode) {
		this.psncode = psncode;
	}

	public String getPsnname() {
		return this.psnname;
	}

	public void setPsnname(String psnname) {
		this.psnname = psnname;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getVisible() {
		return this.visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

}