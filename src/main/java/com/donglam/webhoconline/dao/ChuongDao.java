package com.donglam.webhoconline.dao;

import java.util.List;

import com.donglam.webhoconline.model.Chuong;

public interface ChuongDao extends GenericDao<Chuong, String> {

	public List<Chuong> getListChuong();
	public List<Chuong> getListByGT(String ten);
	public int getNextMaChuong(String magt);
	public boolean remove(String machuong);
}
