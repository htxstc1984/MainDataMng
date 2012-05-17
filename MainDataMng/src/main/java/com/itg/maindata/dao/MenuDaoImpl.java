package com.itg.maindata.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.itg.maindata.domain.RAuthMenu;
import com.itg.maindata.domain.SyMenu;

public class MenuDaoImpl implements MenuDao {

	@PersistenceContext
	private EntityManager em;

	public MenuDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addMenu(SyMenu menu) {
		// TODO Auto-generated method stub
		em.persist(menu);
	}

	@Override
	public void removeMenu(String pk_menu) {
		// TODO Auto-generated method stub
		em.remove(getMenu(pk_menu));
	}

	@Override
	public SyMenu getMenu(String pk_menu) {
		// TODO Auto-generated method stub
		return em.find(SyMenu.class, pk_menu);
	}

	@Override
	public void updateMenu(SyMenu menu) {
		// TODO Auto-generated method stub
		em.merge(menu);
	}

	@Override
	public List<SyMenu> getMenus(String sWhere) {
		// TODO Auto-generated method stub
		return em.createQuery("select e from SyMenu e where " + sWhere,
				SyMenu.class).getResultList();
	}

	@Override
	public List<RAuthMenu> getMenusByAuth(String pk_auths) {
		// TODO Auto-generated method stub
		String[] arr_auths = pk_auths.split(",");
		String sWhere = "";
		for (String pk_auth : arr_auths) {
			sWhere = sWhere + "'" + pk_auth + "',";
		}
		if (!sWhere.equalsIgnoreCase("")) {
			sWhere = "pkAuthority in ("
					+ sWhere.substring(0, sWhere.length() - 1) + ")";
			return em.createQuery("select a from RAuthMenu a where " + sWhere,
					RAuthMenu.class).getResultList();
		}
		return null;
	}

	@Override
	public List<SyMenu> getAllMenus() {
		// TODO Auto-generated method stub
		return em
				.createQuery("select e from SyMenu e where 1=1 order by level",
						SyMenu.class).getResultList();
	}

}
