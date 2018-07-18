package com.donglam.webhoconline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donglam.webhoconline.dao.ChuongDao;
import com.donglam.webhoconline.dao.GenericDao;
import com.donglam.webhoconline.model.Chuong;

@Service
@Transactional
public class ChuongServiceImpl extends GenericServiceImpl<Chuong, String> implements ChuongService {
	@Autowired
	private ChuongDao dao;

	@Autowired
	public ChuongServiceImpl(GenericDao<Chuong, String> genericDao) {
		super(genericDao);
		this.dao = (ChuongDao) genericDao;
	}

	// custom method
	@Override
	public List<Chuong> getListChuong() {
		return dao.getListChuong();
	}

	@Override
	public List<Chuong> getListByGT(String ten) {
		return dao.getListByGT(ten);
	}

	@Override
	public int getNextMaChuong(String magt) {
		return dao.getNextMaChuong(magt);
	}

	@Override
	public boolean remove(String machuong) {
		return dao.remove(machuong);
	}
}
