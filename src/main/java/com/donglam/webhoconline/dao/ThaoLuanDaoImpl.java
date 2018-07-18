package com.donglam.webhoconline.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.donglam.webhoconline.model.ThaoLuan;
import com.donglam.webhoconline.model.ThaoLuanId;

@SuppressWarnings("unchecked")
@Repository
public class ThaoLuanDaoImpl extends GenericDaoImpl<ThaoLuan, ThaoLuanId> implements ThaoLuanDao {
	// custom method
	@Override
	public List<ThaoLuan> getListByTopic(Integer matl) {
		Query query = currentSession().createQuery("from ThaoLuan tl where tl.matl=:matl order by tl.thaoluanid.tgdang");
		query.setParameter("matl", matl);

		return query.list();
	}

	@Override
	public ThaoLuan getQuestion(Integer matl) {	
		List<ThaoLuan> list = new ArrayList<>();
		list = getListByTopic(matl);
		
		return list.get(0);
	}

	@Override
	public List<ThaoLuan> getReply(Integer matl) {	
		List<ThaoLuan> list = new ArrayList<>();
		list = getListByTopic(matl);
		list.remove(0);
		
		return list;
	}
	
	@Override
	public int getCountReply(Integer matl) {	
		List<ThaoLuan> list = new ArrayList<>();
		list = getReply(matl);
		
		return list.size();
	}
	
	@Override
	public List<ThaoLuan> getForumKH(String makh) {
		Query query = currentSession().createQuery("from ThaoLuan tl where tl.tieude!='' and tl.khoahoc.makh=:makh");
		query.setParameter("makh", makh);
		
		return query.list();
	}
	
	@Override
	public int getNextMatl() {
		Query query = currentSession().createQuery("from ThaoLuan tl order by tl.matl desc");
		List<ThaoLuan> list = new ArrayList<>();
		list = query.list();
		
		int matl = 1;
		try
		{
			matl = list.get(0).getMatl() + 1;
		}
		catch (Exception ee)
		{
			return 1;
		}
		return matl;
	}

	@Override
	public boolean removeTL(int mand, String time) {
		Query query = currentSession().createQuery("delete ThaoLuan tl where tl.thaoluanid.nguoidung.mand =:mand and tl.thaoluanid.tgdang =:time");
		query.setParameter("mand", mand);
		query.setParameter("time", time);
		int result = query.executeUpdate();
		return result>0;
	}
	
	
}
