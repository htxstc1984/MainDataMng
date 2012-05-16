package com.itg.maindata.web.jsonvo;

import java.io.Serializable;
import java.util.List;

import com.itg.maindata.web.vo.RAuthMenuUI;

public class JsonRAuthMenuUI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<RAuthMenuUI> rams;

	public List<RAuthMenuUI> getRams() {
		return rams;
	}

	public void setRams(List<RAuthMenuUI> rams) {
		this.rams = rams;
	}

}
