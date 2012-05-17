package com.itg.maindata.web.jsonvo;

import java.io.Serializable;
import java.util.List;

import com.itg.maindata.domain.MdPsn;

public class JsonPsns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<MdPsn> psns;

	public List<MdPsn> getPsns() {
		return psns;
	}

	public void setPsns(List<MdPsn> psns) {
		this.psns = psns;
	}

}
