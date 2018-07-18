package com.donglam.webhoconline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.donglam.webhoconline.dao.GenericDao;
import com.donglam.webhoconline.dao.QuyenDao;
import com.donglam.webhoconline.model.Quyen;

@Service
@Transactional
public class QuyenServiceImpl extends GenericServiceImpl<Quyen, Integer> implements QuyenService {
	@Autowired
	private QuyenDao dao;

	@Autowired
	public QuyenServiceImpl(GenericDao<Quyen, Integer> genericDao) {
		super(genericDao);
		this.dao = (QuyenDao) genericDao;
	}

	// custom method
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Quyen> getListQuyen() {
		return dao.getListQuyen();
	}
}
