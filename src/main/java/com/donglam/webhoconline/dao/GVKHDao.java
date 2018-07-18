package com.donglam.webhoconline.dao;

import java.util.List;

import com.donglam.webhoconline.model.GVKH;
import com.donglam.webhoconline.model.GVKHId;

public interface GVKHDao extends GenericDao<GVKH, GVKHId>{

	public List<GVKH> getListGVKHChuaDuyet();
	
}
