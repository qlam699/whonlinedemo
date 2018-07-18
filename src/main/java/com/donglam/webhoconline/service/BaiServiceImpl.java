package com.donglam.webhoconline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donglam.webhoconline.dao.BaiDao;
import com.donglam.webhoconline.dao.GenericDao;
import com.donglam.webhoconline.model.Bai;

@Service
@Transactional
public class BaiServiceImpl extends GenericServiceImpl<Bai, String> implements BaiService {
	@Autowired
	private BaiDao dao;

	@Autowired
	public BaiServiceImpl(GenericDao<Bai, String> genericDao) {
		super(genericDao);
		this.dao = (BaiDao) genericDao;
	}

	// custom method
	@Override
	public List<Bai> getListBai() {
		return dao.getListBai();
	}

	@Override
	public List<Bai> getListByChuong(String ten) {
		return dao.getListByChuong(ten);
	}

	@Override
	public int getNextMaBai(String machuong) {
		return dao.getNextMaBai(machuong);
	}

	@Override
	public boolean remove(String mabai) {
		return dao.remove(mabai);
	}
}
