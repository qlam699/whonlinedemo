package com.donglam.webhoconline.dao;

import java.util.List;

import com.donglam.webhoconline.model.Bai;
import com.donglam.webhoconline.service.GenericService;

public interface BaiDao extends GenericService<Bai, String> {

	public List<Bai> getListBai();
	public List<Bai> getListByChuong(String ten);
	public int getNextMaBai(String machuong);
	public boolean remove(String mabai);
}
