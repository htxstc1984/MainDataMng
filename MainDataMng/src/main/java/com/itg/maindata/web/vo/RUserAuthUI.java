package com.itg.maindata.web.vo;

import java.io.Serializable;

import com.itg.maindata.domain.SyAuthority;

public class RUserAuthUI extends SyAuthority implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private SyAuthority syAuthority;

	private boolean haveAuth;

	public RUserAuthUI() {
		// TODO Auto-generated constructor stub
	}


	public boolean isHaveAuth() {
		return haveAuth;
	}

	public void setHaveAuth(boolean haveAuth) {
		this.haveAuth = haveAuth;
	}

}
