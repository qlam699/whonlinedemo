package com.donglam.webhoconline.service;

import java.util.List;

import com.donglam.webhoconline.model.LoaiKhoaHoc;

public interface LoaiKhoaHocService extends GenericService<LoaiKhoaHoc, String> {
	// custom method
	public List<LoaiKhoaHoc> getListLoaiKH();
	public int getNextMalkh(String makhoa);
}
