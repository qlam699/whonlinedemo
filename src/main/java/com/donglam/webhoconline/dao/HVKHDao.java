package com.donglam.webhoconline.dao;

import java.util.List;

import com.donglam.webhoconline.model.HVKH;
import com.donglam.webhoconline.model.HVKHId;

public interface HVKHDao extends GenericDao<HVKH, HVKHId>{

	public List<HVKH> getListByHV(String mand);
}
