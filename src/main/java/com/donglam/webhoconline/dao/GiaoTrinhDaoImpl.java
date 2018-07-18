package com.donglam.webhoconline.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.donglam.webhoconline.model.GiaoTrinh;

@SuppressWarnings("unchecked")
@Repository
public class GiaoTrinhDaoImpl extends GenericDaoImpl<GiaoTrinh, String> implements GiaoTrinhDao {
	// custom method
	@Override
	public List<GiaoTrinh> getListGiaoTrinh() {
		Query query = currentSession().createQuery("from GiaoTrinh gt where gt.isdeleted is false");
		return query.list();
	}

	@Override
	public int getNextMaGT() {
		Query query = currentSession().createQuery("select max(magt) from GiaoTrinh");
		try {
			String t = (String) query.list().get(0);
			String[] tam = t.split("_");
			return Integer.parseInt(tam[1]) + 1;
		} catch (Exception e) {
			return 1;
		}
	}
	
	@Override
	public List<GiaoTrinh> getListGTGV(int mand){
		Query query = currentSession().createQuery("from GiaoTrinh gt where gt.nguoidung.mand=:mand");
		query.setParameter("mand", mand);
		return query.list();
	}
}
