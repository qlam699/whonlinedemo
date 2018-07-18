package com.donglam.webhoconline.service;

import java.util.List;

import com.donglam.webhoconline.model.ThaoLuan;
import com.donglam.webhoconline.model.ThaoLuanId;

public interface ThaoLuanService extends GenericService<ThaoLuan, ThaoLuanId> {

	// custom method
	public List<ThaoLuan> getListByTopic(Integer matl);
	
	public ThaoLuan getQuestion(Integer matl);
	
	public List<ThaoLuan> getReply(Integer matl);
	
	public int getCountReply(Integer matl);
	
	public List<ThaoLuan> getForumKH(String makh);
	
	public int getNextMatl();
	public boolean removeTL(int mand,String time);
}
