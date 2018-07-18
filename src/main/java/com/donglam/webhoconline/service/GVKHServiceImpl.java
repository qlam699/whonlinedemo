package com.donglam.webhoconline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donglam.webhoconline.dao.GVKHDao;
import com.donglam.webhoconline.dao.GenericDao;
import com.donglam.webhoconline.model.GVKH;
import com.donglam.webhoconline.model.GVKHId;
@Service
@Transactional
public class GVKHServiceImpl extends GenericServiceImpl<GVKH, GVKHId> implements GVKHService {
	@Autowired
	private GVKHDao dao;

	@Autowired
	public GVKHServiceImpl(GenericDao<GVKH, GVKHId> genericDao) {
		super(genericDao);
		this.dao = (GVKHDao) genericDao;
	}

	@Override
	public List<GVKH> getListGVKHChuaDuyet() {
		return dao.getListGVKHChuaDuyet();
	}

}
