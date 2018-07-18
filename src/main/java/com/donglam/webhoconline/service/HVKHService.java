package com.donglam.webhoconline.service;

import java.util.List;

import com.donglam.webhoconline.model.HVKH;
import com.donglam.webhoconline.model.HVKHId;

public interface HVKHService extends GenericService<HVKH, HVKHId> {

	// custom method
	public List<HVKH> getListByHV(String mand);
}
