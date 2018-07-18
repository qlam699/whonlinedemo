package com.donglam.webhoconline.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.donglam.webhoconline.model.HVKH;
import com.donglam.webhoconline.model.HVKHId;

@SuppressWarnings("unchecked")
@Repository
public class HVKHDaoImpl extends GenericDaoImpl<HVKH, HVKHId> implements HVKHDao {
	// custom method
	@Override
	public List<HVKH> getListByHV(String mand) {
		Query query = currentSession().createQuery("from HVKH where HVKH.hvkhid.nguoidung.mand =:mand");
		query.setParameter("mand", mand);
		return query.list();
	}
}
