package com.donglam.webhoconline.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.donglam.webhoconline.model.NDTN;
import com.donglam.webhoconline.model.NDTNId;

@SuppressWarnings("unchecked")
@Repository
public class NDTNDaoImpl extends GenericDaoImpl<NDTN, NDTNId> implements NDTNDao {
	// custom method
	@Override
	public List<NDTN> getListByTN(int matn) {
		Query query = currentSession().createQuery("from NDTN tn where tn.ndtnid.tinnhan.matn =:matn");
		query.setParameter("matn", matn);
		return query.list();
	}

	@Override
	public List<NDTN> getListByNguoiGui(int nguoigui) {
		Query query = currentSession().createQuery("from NDTN tn where tn.ndtnid.nguoigui.mand =:nguoigui");
		query.setParameter("nguoigui", nguoigui);
		return query.list();
	}

	@Override
	public NDTN getNDTNId(int nguoigui, int nguoinhan, int matn) {
		Query query = currentSession().createQuery(
				"from NDTN tn where tn.ndtnid.nguoigui.mand =:nguoigui and tn.ndtnid.nguoinhan.mand=:nguoinhan and tn.ndtnid.tinnhan.matn=:matn");
		query.setParameter("nguoigui", nguoigui);
		query.setParameter("nguoinhan", nguoinhan);
		query.setParameter("matn", matn);
		return query.list().size()>0?(NDTN)query.list().get(0):null;
	}

	@Override
	public List<NDTN> getListByNguoiNhan(int mand) {
		Query query = currentSession().createQuery("from NDTN tn where tn.ndtnid.nguoinhan.mand =:nguoinhan");
		query.setParameter("nguoinhan", mand);
		return query.list();
	}
}
