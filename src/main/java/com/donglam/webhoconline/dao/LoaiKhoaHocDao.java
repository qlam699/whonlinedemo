package com.donglam.webhoconline.dao;

import java.util.List;

import com.donglam.webhoconline.model.LoaiKhoaHoc;

public interface LoaiKhoaHocDao extends GenericDao<LoaiKhoaHoc, String> {
	// custom method
	public List<LoaiKhoaHoc> getListLoaiKH(); // chỉ lấy các LoaiKH có isdeleted là false
	public int getNextMalkh(String makhoa);
}
