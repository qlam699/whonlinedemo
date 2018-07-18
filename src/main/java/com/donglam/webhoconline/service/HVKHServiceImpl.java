package com.donglam.webhoconline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donglam.webhoconline.dao.GenericDao;
import com.donglam.webhoconline.dao.HVKHDao;
import com.donglam.webhoconline.model.HVKH;
import com.donglam.webhoconline.model.HVKHId;

@Service
@Transactional
public class HVKHServiceImpl extends GenericServiceImpl<HVKH, HVKHId> implements HVKHService {
	@Autowired
	private HVKHDao dao;

	@Autowired
	public HVKHServiceImpl(GenericDao<HVKH, HVKHId> genericDao) {
		super(genericDao);
		this.dao = (HVKHDao) genericDao;
	}

	// custom method
	@Override
	public List<HVKH> getListByHV(String mand) {
		return dao.getListByHV(mand);
	}
}
