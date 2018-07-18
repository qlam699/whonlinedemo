package com.donglam.webhoconline.service;

import java.util.List;

import com.donglam.webhoconline.model.TinNhan;

public interface TinNhanService extends GenericService<TinNhan, Integer> {

	// custom method
	public List<TinNhan> getListByTieuDe(String tieude);
}
