package com.donglam.webhoconline.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.donglam.webhoconline.model.Quyen;

@SuppressWarnings("unchecked")
@Repository
public class QuyenDaoImpl extends GenericDaoImpl<Quyen, Integer> implements QuyenDao {
	// custom method
	@Override
	public List<Quyen> getListQuyen() {
		Query query = currentSession().createQuery("from Quyen q where q.isdeleted is false");
		return query.list();
	}
}
