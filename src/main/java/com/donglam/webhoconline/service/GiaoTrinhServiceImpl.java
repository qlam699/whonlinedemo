package com.donglam.webhoconline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donglam.webhoconline.dao.GenericDao;
import com.donglam.webhoconline.dao.GiaoTrinhDao;
import com.donglam.webhoconline.model.GiaoTrinh;

@Service
@Transactional
public class GiaoTrinhServiceImpl extends GenericServiceImpl<GiaoTrinh, String> implements GiaoTrinhService {
	@Autowired
	private GiaoTrinhDao dao;

	@Autowired
	public GiaoTrinhServiceImpl(GenericDao<GiaoTrinh, String> genericDao) {
		super(genericDao);
		this.dao = (GiaoTrinhDao) genericDao;
	}

	// custom method
	@Override
	public List<GiaoTrinh> getListGiaoTrinh() {
		return dao.getListGiaoTrinh();
	}

	@Override
	public int getNextMaGT() {
		return dao.getNextMaGT();
	}
	
	@Override
	public List<GiaoTrinh> getListGTGV(int mand) {
		return dao.getListGTGV(mand);
	}
}
