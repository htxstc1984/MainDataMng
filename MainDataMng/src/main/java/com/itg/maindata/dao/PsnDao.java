package com.itg.maindata.dao;

import java.util.List;

import com.itg.maindata.domain.MdPsn;

public interface PsnDao {
	public void addPsn(MdPsn psn);

	public void removePsn(String pk_psn);

	public MdPsn getPsn(String pk_psn);

	public void updatePsn(MdPsn psn);

	public List<MdPsn> getPsns(String sql);

	public List<MdPsn> getPsnsByDept(String pk_depts);

	public List<MdPsn> getAllPsns();
}
