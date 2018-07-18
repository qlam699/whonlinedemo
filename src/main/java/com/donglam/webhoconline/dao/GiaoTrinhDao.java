package com.donglam.webhoconline.dao;

import java.util.List;

import com.donglam.webhoconline.model.GiaoTrinh;

public interface GiaoTrinhDao extends GenericDao<GiaoTrinh, String> {

	public List<GiaoTrinh> getListGiaoTrinh();
	public int getNextMaGT();
	public List<GiaoTrinh> getListGTGV(int mand);
}
