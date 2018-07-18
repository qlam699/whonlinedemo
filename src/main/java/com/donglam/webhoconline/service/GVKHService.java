package com.donglam.webhoconline.service;

import java.util.List;

import com.donglam.webhoconline.model.GVKH;
import com.donglam.webhoconline.model.GVKHId;

public interface GVKHService extends GenericService<GVKH, GVKHId> {

	public List<GVKH> getListGVKHChuaDuyet();
}
