package com.donglam.webhoconline.service;

import java.util.List;

import com.donglam.webhoconline.model.Bai;

public interface BaiService extends GenericService<Bai, String> {

	// custom method
	public List<Bai> getListBai();
	public List<Bai> getListByChuong(String ten);
	public int getNextMaBai(String machuong);
	public boolean remove(String mabai);
}
