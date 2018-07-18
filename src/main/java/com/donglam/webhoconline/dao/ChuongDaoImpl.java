package com.donglam.webhoconline.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.donglam.webhoconline.model.Chuong;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class ChuongDaoImpl extends GenericDaoImpl<Chuong, String> implements ChuongDao {
	// custom method
	@Override
	public List<Chuong> getListChuong() {
		Query query = currentSession().createQuery("from Chuong c where c.isdeleted is false");
		return query.list();
	}

	@Override
	public List<Chuong> getListByGT(String ten) {
		Query query = currentSession().createQuery("from Chuong c where c.giaotrinh.magt=:magt");
		query.setParameter("magt", ten);
		return query.list();
	}

	@Override
	public int getNextMaChuong(String magt) {
		Query query = currentSession().createQuery("select max(machuong) from Chuong c where c.giaotrinh.magt=:magt");
		query.setParameter("magt", magt);

		String t = (String) query.list().get(0);

		if (t == null)
			return 1;

		String[] tam = t.split("_");
		return Integer.parseInt(tam[3]) + 1;
	}

	@Override
	public boolean remove(String machuong) {
		Query query = currentSession().createQuery("delete Chuong c where c.machuong =:machuong");
		query.setParameter("machuong", machuong);
		int result = query.executeUpdate();
		return result > 0;
	}

}
