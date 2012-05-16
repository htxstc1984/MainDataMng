package com.itg.maindata.web.jsonvo;

import java.io.Serializable;
import java.util.List;

import com.itg.maindata.domain.SyAuthority;

public class JsonAuthoritys implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SyAuthority> auths;

	public List<SyAuthority> getAuths() {
		return auths;
	}

	public void setAuths(List<SyAuthority> auths) {
		this.auths = auths;
	}
}
