package com.donglam.webhoconline.service;

import java.util.List;

import com.donglam.webhoconline.model.NDTN;
import com.donglam.webhoconline.model.NDTNId;

public interface NDTNService extends GenericService<NDTN, NDTNId> {

	// custom method
	public List<NDTN> getListByNguoiGui(int mand);
	public List<NDTN> getListByNguoiNhan(int mand);
	public List<NDTN> getListByTN(int matn);
	public NDTN getNDTNId(int nguoigui,int nguoinhan,int matn);
}
