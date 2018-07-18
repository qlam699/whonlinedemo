package com.donglam.webhoconline.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.donglam.webhoconline.model.NguoiDung;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class NguoiDungDaoImpl extends GenericDaoImpl<NguoiDung, Integer> implements NguoiDungDao {
	// custom method
	public int getMaTea() {
		Query query = currentSession()
				.createQuery("from Quyen nd join nd.nguoidungs nds with nd.maquyen=3 order by nds.mand desc");
		List<Object[]> a = query.list();
		int t = 0;
		for (Object[] x : a) {
			t = ((NguoiDung) x[0]).getMand();
			break;
		}
		return t;
	}

	public int getMaStu() {
		Query query = currentSession()
				.createQuery("from Quyen nd join nd.nguoidungs nds with nd.maquyen=4 order by nds.mand desc");
		List<Object[]> a = query.list();
		int t = 0;
		for (Object[] x : a) {
			t = ((NguoiDung) x[0]).getMand();
			break;
		}
		return t;
	}

	public List<NguoiDung> getListCouTea(String makh) {
		Query query = currentSession()
				.createQuery("from NguoiDung nd join nd.gvkhs gvkh with gvkh.gvkhid.khoahoc.makh=:makh");
		query.setParameter("makh", makh);
		List<Object[]> a = query.list();
		List<NguoiDung> list = new ArrayList<NguoiDung>();
		for (Object[] x : a) {
			list.add((NguoiDung) x[0]);
		}
		return list;
	}

	// Email local
	@Override
	public NguoiDung findByEmail(String email) {
		Query query = currentSession()
				.createQuery("from NguoiDung nd where nd.email=:email and nd.loaitaikhoan='local'");
		query.setParameter("email", email);
		if (query.list().size() == 0)
			return null;
		return (NguoiDung) query.list().get(0);
	}

	@Override
	public NguoiDung findByConfirmationToken(String confirmationToken) {
		Query query = currentSession().createQuery("from NguoiDung nd where nd.khoabimat=:khoabimat");
		query.setParameter("khoabimat", confirmationToken);
		if (query.list().size() == 0)
			return null;
		return (NguoiDung) query.list().get(0);
	}

	@Override
	public int getNoId(String maquyen) {
		Query query = currentSession()
				.createQuery("select count(mand) from NguoiDung nd where nd.mand like CONCAT('%', :ma, '%')");
		query.setParameter("ma", maquyen); // hv
		Number number = (Number) query.list().get(0);
		return number.intValue() + 1;
	}

	@Override
	public List<NguoiDung> getListTea() {
		Query query = currentSession().createQuery("from NguoiDung nd where nd.quyen.maquyen=3 and nd.isdeleted is false");
		return query.list();
	}
}
