package com.itg.maindata.dao;

public interface UtilDao {
	public Object getVOByPk(String className, String pk) throws Exception;

	public Object[] getVOsByRefPk(String className, String midClassName,
			String refFieldName, String pk) throws Exception;
}
