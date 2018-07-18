package com.donglam.webhoconline.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.donglam.webhoconline.model.KhoaHoc;

public interface KhoaHocDao extends GenericDao<KhoaHoc, String> {

	public List<KhoaHoc> getListKhoaHoc();

	public List<KhoaHoc> getListByName(String ten);

	public List<KhoaHoc> getListByPage(int firstResult, int maxResult);

	public Page<KhoaHoc> getListPage(Pageable pageable);

	public List<KhoaHoc> getListGVKH(int magv);

	public List<KhoaHoc> getListHVKH(int mahv);

	public KhoaHoc getKHGT(String magt);

	public int getNextMakh(String maloaikh);

	public List<KhoaHoc> getListKHDaDuyet(boolean daduyet);

	public boolean remove(String makh);
	
	public List<KhoaHoc> getListKH(String maloaikh);
}