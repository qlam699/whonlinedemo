package com.donglam.webhoconline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.donglam.webhoconline.dao.GenericDao;
import com.donglam.webhoconline.dao.KhoaDao;
import com.donglam.webhoconline.model.Khoa;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class KhoaServiceImpl extends GenericServiceImpl<Khoa, String> implements KhoaService {
	@Autowired
	private KhoaDao dao;

	@Autowired
	public KhoaServiceImpl(GenericDao<Khoa, String> genericDao) {
		super(genericDao);
		this.dao = (KhoaDao) genericDao;
	}

	// custom method
	@Override
	public List<Khoa> getListKhoa() {
		return dao.getListKhoa();
	}
}
