package com.donglam.webhoconline.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.donglam.webhoconline.model.KhoaHoc;

@SuppressWarnings("unchecked")
@Repository
public class KhoaHocDaoImpl extends GenericDaoImpl<KhoaHoc, String> implements KhoaHocDao {
	// custom method
	@Override
	public List<KhoaHoc> getListKhoaHoc() {
		Query query = currentSession().createQuery("from KhoaHoc kh where kh.isdeleted is false");
		return query.list();
	}

	@Override
	public List<KhoaHoc> getListByName(String ten) {
		Query query = currentSession().createQuery("from KhoaHoc kh where kh.tenkh like CONCAT('%', :tenkh, '%') and kh.isdeleted is false and kh.daduyet is true");
		query.setParameter("tenkh", ten);
		return query.list();
	}

	@Override
	public List<KhoaHoc> getListByPage(int firstResult, int maxResult) {
		Query query = currentSession().createQuery("from KhoaHoc where KhoaHoc.tenkh=:tenkh");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		return currentSession().createCriteria(daoType).list();
	}

	@Override
	public Page<KhoaHoc> getListPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KhoaHoc> getListGVKH(int magv) {
		Query query = currentSession()
				.createQuery("from KhoaHoc kh join kh.gvkhs gvkh with gvkh.gvkhid.nguoidung.mand=:magv");
		query.setParameter("magv", magv);
		List<Object[]> a = query.list();
		List<KhoaHoc> list = new ArrayList<KhoaHoc>();
		for (Object[] x : a) {
			list.add((KhoaHoc) x[0]);
		}
		try {
			for (KhoaHoc x : list) {
				if (x.isIsdeleted() || !x.isDaduyet()) {
					list.remove(x);
				}
			}
			return list;
		} catch (Exception ee) {
			return null;
		}
	}

	@Override
	public List<KhoaHoc> getListHVKH(int mahv) {
		Query query = currentSession()
				.createQuery("from KhoaHoc kh join kh.hvkhs hvkh with hvkh.hvkhid.nguoidung.mand=:mahv");
		query.setParameter("mahv", mahv);
		List<Object[]> a = query.list();
		List<KhoaHoc> list = new ArrayList<KhoaHoc>();
		for (Object[] x : a) {
			list.add((KhoaHoc) x[0]);
		}
		try {
			for (KhoaHoc x : list) {
				if (x.isIsdeleted() || !x.isDaduyet()) {
					list.remove(x);
				}
			}
			return list;
		} catch (Exception ee) {
			return null;
		}
	}

	@Override
	public KhoaHoc getKHGT(String magt) {
		Query query = currentSession().createQuery("from KhoaHoc kh where kh.giaotrinh.magt=:magt");
		query.setParameter("magt", magt);
		return (KhoaHoc) query.list().get(0);
	}

	@Override
	public int getNextMakh(String maloaikh) {
		Query query = currentSession()
				.createQuery("select max(makh) from KhoaHoc kh where kh.loaikhoahoc.maloaikh=:maloaikh");
		query.setParameter("maloaikh", maloaikh);

		String t = (String) query.list().get(0);
		if (t == null)
			return 1;

		String[] tam = t.split("_");
		return Integer.parseInt(tam[2]) + 1;
	}

	@Override
	public List<KhoaHoc> getListKHDaDuyet(boolean daduyet) {
		Query query = currentSession()
				.createQuery("from KhoaHoc kh where kh.daduyet=:daduyet and kh.isdeleted is false");
		query.setParameter("daduyet", daduyet);
		return query.list();
	}

	@Override
	public boolean remove(String makh) {
		Query query = currentSession().createQuery("delete KhoaHoc kh where kh.makh=:makh");
		query.setParameter("makh", makh);
		int result = query.executeUpdate();
		return result > 0;
	}
	
	public List<KhoaHoc> getListKH(String maloaikh){
		Query query = currentSession().createQuery("from KhoaHoc kh where kh.loaikhoahoc.maloaikh=:maloaikh and kh.isdeleted is false and kh.daduyet is true");
		query.setParameter("maloaikh", maloaikh);
		return query.list();
	}
}