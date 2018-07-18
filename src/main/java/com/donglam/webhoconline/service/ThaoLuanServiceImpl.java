package com.donglam.webhoconline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.donglam.webhoconline.dao.GenericDao;
import com.donglam.webhoconline.dao.ThaoLuanDao;
import com.donglam.webhoconline.model.ThaoLuan;
import com.donglam.webhoconline.model.ThaoLuanId;

@Service
@Transactional
public class ThaoLuanServiceImpl extends GenericServiceImpl<ThaoLuan, ThaoLuanId> implements ThaoLuanService {
	@Autowired
	private ThaoLuanDao dao;

	@Autowired
	public ThaoLuanServiceImpl(GenericDao<ThaoLuan, ThaoLuanId> genericDao) {
		super(genericDao);
		this.dao = (ThaoLuanDao) genericDao;
	}

	// custom method
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<ThaoLuan> getListByTopic(Integer matl) {
		return dao.getListByTopic(matl);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ThaoLuan getQuestion(Integer matl) {
		return dao.getQuestion(matl);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<ThaoLuan> getReply(Integer matl) {
		return dao.getReply(matl);
	}
	
	@Override
	public int getCountReply(Integer matl) {
		return dao.getCountReply(matl);
	}
	
	@Override
	public List<ThaoLuan> getForumKH(String makh) {
		return dao.getForumKH(makh);
	}
	
	@Override
	public int getNextMatl() {
		return dao.getNextMatl();
	}

	@Override
	public boolean removeTL(int mand, String time) {
		return dao.removeTL(mand, time);
	}
}

