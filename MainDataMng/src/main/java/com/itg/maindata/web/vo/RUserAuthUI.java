package com.itg.maindata.web.vo;

import java.io.Serializable;

import com.itg.maindata.domain.RUserAuth;
import com.itg.maindata.domain.SyAuthority;

public class RUserAuthUI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SyAuthority syAuthority;

	private boolean haveAuth;

	public RUserAuthUI() {
		// TODO Auto-generated constructor stub
	}

	public SyAuthority getSyAuthority() {
		return syAuthority;
	}

	public void setSyAuthority(SyAuthority syAuthority) {
		this.syAuthority = syAuthority;
	}

	public boolean isHaveAuth() {
		return haveAuth;
	}

	public void setHaveAuth(boolean haveAuth) {
		this.haveAuth = haveAuth;
	}

}
