package com.baobaotao.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	private int userId;
	private String userName;
	private String password;
	private int credits;
	private String lastlp;
	private Date lastVisit;

	public int getUserid() {
		return userId;
	}

	public void setUserid(int userid) {
		this.userId = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getLastlp() {
		return lastlp;
	}

	public void setLastlp(String lastlp) {
		this.lastlp = lastlp;
	}

	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

}
