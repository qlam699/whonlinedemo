package com.donglam.webhoconline.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.donglam.webhoconline.model.TinNhan;

@SuppressWarnings("unchecked")
@Repository
public class TinNhanDaoImpl extends GenericDaoImpl<TinNhan, Integer> implements TinNhanDao {
	// custom method
	@Override
	public List<TinNhan> getListByTieuDe(String tieude) {
		Query query = currentSession().createQuery("from TinNhan tn where tn.tieude =: tieude");
		query.setParameter("tieude", tieude);
		return currentSession().createCriteria(daoType).list();
	}
}
