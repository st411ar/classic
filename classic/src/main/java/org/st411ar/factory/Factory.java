package org.st411ar.factory;

import org.st411ar.dao.DAO;

public class Factory {
	private DAO dao;

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	public DAO getDao() {
		return dao;
	}
}