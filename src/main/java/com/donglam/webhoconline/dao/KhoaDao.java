package com.donglam.webhoconline.dao;

import java.util.List;

import com.donglam.webhoconline.model.Khoa;

public interface KhoaDao extends GenericDao<Khoa, String> {

	public List<Khoa> getListKhoa(); // chỉ lấy các Khoa có isdeleted là false
}
