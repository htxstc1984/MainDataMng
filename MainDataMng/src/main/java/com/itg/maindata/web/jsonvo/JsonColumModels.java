package com.itg.maindata.web.jsonvo;

import java.io.Serializable;
import java.util.List;

import com.itg.maindata.web.vo.ColumModelVO;

public class JsonColumModels implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ColumModelVO> cols;

	public List<ColumModelVO> getCols() {
		return cols;
	}

	public void setCols(List<ColumModelVO> cols) {
		this.cols = cols;
	}

}
