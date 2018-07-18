package com.donglam.webhoconline.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.donglam.webhoconline.model.Bai;

@SuppressWarnings("unchecked")
@Repository
public class BaiDaoImpl extends GenericDaoImpl<Bai, String> implements BaiDao {
	// custom method
	@Override
	public List<Bai> getListBai() {
		Query query = currentSession().createQuery("from Bai b where b.isdeleted is false");
		return query.list();
	}

	@Override
	public List<Bai> getListByChuong(String ten) {
		Query query = currentSession().createQuery("from Bai b where b.chuong.machuong=:machuong");
		query.setParameter("machuong", ten);
		return query.list();
	}

	@Override
	public int getNextMaBai(String machuong) {
		Query query = currentSession().createQuery("select max(mabai) from Bai b where b.chuong.machuong=:machuong");
		query.setParameter("machuong", machuong);

		String t = (String) query.list().get(0);
		if (t == null)
			return 1;

		String[] tam = t.split("_");
		return Integer.parseInt(tam[5]) + 1;
	}

	@Override
	public boolean remove(String mabai) {
		Query query = currentSession().createQuery("delete Bai b where b.mabai =:mabai");
		query.setParameter("mabai", mabai);
		int result = query.executeUpdate();
		return result > 0;
	}
}
