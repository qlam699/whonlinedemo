package com.donglam.webhoconline.dao;

import java.util.List;

import com.donglam.webhoconline.model.NDTN;
import com.donglam.webhoconline.model.NDTNId;

public interface NDTNDao extends GenericDao<NDTN, NDTNId> {

	public List<NDTN> getListByNguoiGui(int mand);
	public List<NDTN> getListByNguoiNhan(int mand);
	public List<NDTN> getListByTN(int matn);
	public NDTN getNDTNId(int nguoigui,int nguoinhan,int matn);
}
