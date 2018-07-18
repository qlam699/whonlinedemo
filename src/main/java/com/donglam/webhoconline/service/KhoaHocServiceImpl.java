package com.donglam.webhoconline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.donglam.webhoconline.dao.GenericDao;
import com.donglam.webhoconline.dao.KhoaHocDao;
import com.donglam.webhoconline.model.KhoaHoc;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class KhoaHocServiceImpl extends GenericServiceImpl<KhoaHoc, String> implements KhoaHocService {
	@Autowired
	private KhoaHocDao dao;

	@Autowired
	public KhoaHocServiceImpl(GenericDao<KhoaHoc, String> genericDao) {
		super(genericDao);
		this.dao = (KhoaHocDao) genericDao;
	}

	// custom method
	@Override
	public List<KhoaHoc> getListKhoaHoc() {
		return dao.getListKhoaHoc();
	}

	@Override
	public List<KhoaHoc> getListByName(String ten) {
		return dao.getListByName(ten);
	}

	@Override
	public List<KhoaHoc> getListByPage(int firstResult, int maxResult) {
		return dao.getListByPage(firstResult, maxResult);
	}

	@Override
	public Page<KhoaHoc> getListPage(Pageable pageable) {
		return dao.getListPage(pageable);
	}

	@Override
	public List<KhoaHoc> getListGVKH(int magv) {
		return dao.getListGVKH(magv);
	}

	@Override
	public List<KhoaHoc> getListHVKH(int mahv) {
		return dao.getListHVKH(mahv);
	}

	@Override
	public KhoaHoc getKHGT(String magt) {
		return dao.getKHGT(magt);
	}

	@Override
	public int getNextMakh(String maloaikh) {
		return dao.getNextMakh(maloaikh);
	}

	@Override
	public List<KhoaHoc> getListKHDaDuyet(boolean daduyet) {
		return dao.getListKHDaDuyet(daduyet);
	}

	@Override
	public boolean remove(String makh) {
		return dao.remove(makh);
	}

	@Override
	public List<KhoaHoc> getListKH(String maloaikh) {
		return dao.getListKH(maloaikh);
	}
}