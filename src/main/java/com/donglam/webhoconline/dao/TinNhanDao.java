package com.donglam.webhoconline.dao;

import java.util.List;

import com.donglam.webhoconline.model.TinNhan;

public interface TinNhanDao extends GenericDao<TinNhan, Integer> {

	public List<TinNhan> getListByTieuDe(String tieude);
}
