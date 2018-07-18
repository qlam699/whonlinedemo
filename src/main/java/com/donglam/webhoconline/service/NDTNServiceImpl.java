package com.donglam.webhoconline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.donglam.webhoconline.dao.GenericDao;
import com.donglam.webhoconline.dao.NDTNDao;
import com.donglam.webhoconline.model.NDTN;
import com.donglam.webhoconline.model.NDTNId;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class NDTNServiceImpl extends GenericServiceImpl<NDTN, NDTNId> implements NDTNService {
	@Autowired
	private NDTNDao dao;

	@Autowired
	public NDTNServiceImpl(GenericDao<NDTN, NDTNId> genericDao) {
		super(genericDao);
		this.dao = (NDTNDao) genericDao;
	}

	@Override
	public List<NDTN> getListByNguoiGui(int mand) {
		return dao.getListByNguoiGui(mand);
	}

	@Override
	public List<NDTN> getListByTN(int matn) {
		return dao.getListByTN(matn);
	}

	@Override
	public NDTN getNDTNId(int nguoigui, int nguoinhan, int matn) {
		return dao.getNDTNId(nguoigui, nguoinhan, matn);
	}

	@Override
	public List<NDTN> getListByNguoiNhan(int mand) {
		return dao.getListByNguoiNhan(mand);
	}
}
