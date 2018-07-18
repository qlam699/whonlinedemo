package com.donglam.webhoconline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.donglam.webhoconline.dao.GenericDao;
import com.donglam.webhoconline.dao.TinNhanDao;
import com.donglam.webhoconline.model.TinNhan;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TinNhanServiceImpl extends GenericServiceImpl<TinNhan, Integer> implements TinNhanService {
	@Autowired
	private TinNhanDao dao;

	@Autowired
	public TinNhanServiceImpl(GenericDao<TinNhan, Integer> genericDao) {
		super(genericDao);
		this.dao = (TinNhanDao) genericDao;
	}

	// custom method
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<TinNhan> getListByTieuDe(String tieude) {
		return dao.getListByTieuDe(tieude);
	}
}
