package com.donglam.webhoconline.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.donglam.webhoconline.model.Khoa;

@SuppressWarnings("unchecked")
@Repository
public class KhoaDaoImpl extends GenericDaoImpl<Khoa, String> implements KhoaDao {
	// custom method
	@Override
	public List<Khoa> getListKhoa() {
		Query query = currentSession().createQuery("from Khoa k where k.isdeleted is false");
		return query.list();
	}
}
