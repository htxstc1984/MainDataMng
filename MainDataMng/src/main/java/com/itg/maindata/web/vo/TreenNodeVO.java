package com.itg.maindata.web.vo;

import java.io.Serializable;
import java.util.List;

public class TreenNodeVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nodeType;
	
	private String text;
	
	private String draggable;

	private String id;
	
	private List children;
	
}
