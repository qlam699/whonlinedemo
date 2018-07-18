package com.donglam.webhoconline.service;

import java.util.List;

import com.donglam.webhoconline.model.Khoa;

public interface KhoaService extends GenericService<Khoa, String> {

	// custom method
	public List<Khoa> getListKhoa();
}
