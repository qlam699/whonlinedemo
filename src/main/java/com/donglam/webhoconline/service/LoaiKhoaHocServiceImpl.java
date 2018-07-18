package com.donglam.webhoconline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donglam.webhoconline.dao.GenericDao;
import com.donglam.webhoconline.dao.LoaiKhoaHocDao;
import com.donglam.webhoconline.model.LoaiKhoaHoc;

@Service
@Transactional
public class LoaiKhoaHocServiceImpl extends GenericServiceImpl<LoaiKhoaHoc, String> implements LoaiKhoaHocService {
	@Autowired
	private LoaiKhoaHocDao dao;

	@Autowired
	public LoaiKhoaHocServiceImpl(GenericDao<LoaiKhoaHoc, String> genericDao) {
		super(genericDao);
		this.dao = (LoaiKhoaHocDao) genericDao;
	}

	// custom method
	@Override
	public List<LoaiKhoaHoc> getListLoaiKH() {
		return dao.getListLoaiKH();
	}
	
	@Override
	public int getNextMalkh(String makhoa) {
		return dao.getNextMalkh(makhoa);
	}
}
