package com.donglam.webhoconline.service;

import java.util.List;

import com.donglam.webhoconline.model.Quyen;

public interface QuyenService extends GenericService<Quyen, Integer> {

	// custom method
	public List<Quyen> getListQuyen();
}
