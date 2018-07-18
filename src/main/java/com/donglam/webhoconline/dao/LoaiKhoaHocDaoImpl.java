package com.donglam.webhoconline.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.donglam.webhoconline.model.LoaiKhoaHoc;

@SuppressWarnings("unchecked")
@Repository
public class LoaiKhoaHocDaoImpl extends GenericDaoImpl<LoaiKhoaHoc, String> implements LoaiKhoaHocDao {
	// custom method
	@Override
	public List<LoaiKhoaHoc> getListLoaiKH() {
		Query query = currentSession().createQuery("from LoaiKhoaHoc lkh where lkh.isdeleted is false");
		return query.list();
	}
	
	@Override
	public int getNextMalkh(String makhoa) {
		Query query = currentSession().createQuery("select max(maloaikh) from LoaiKhoaHoc lkh where lkh.khoa.makhoa=:makhoa");
		query.setParameter("makhoa", makhoa);

		String t = (String) query.list().get(0);

		if (t == null) return 1;

		String[] tam = t.split("_");
		return Integer.parseInt(tam[1]) + 1;
	}
}
