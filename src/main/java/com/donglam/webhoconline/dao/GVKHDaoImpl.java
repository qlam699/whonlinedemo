package com.donglam.webhoconline.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.donglam.webhoconline.model.GVKH;
import com.donglam.webhoconline.model.GVKHId;

@SuppressWarnings("unchecked")
@Repository
public class GVKHDaoImpl extends GenericDaoImpl<GVKH, GVKHId> implements GVKHDao {
	// custom method
	@Override
	public List<GVKH> getListGVKHChuaDuyet() {
		Query query = currentSession().createQuery(
				"from GVKH gvkh where gvkh.gvkhid.khoahoc.daduyet is false and gvkh.gvkhid.khoahoc.isdeleted is false");
		return query.list();
	}
}
