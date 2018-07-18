package com.donglam.webhoconline.dao;

import java.util.List;

import com.donglam.webhoconline.model.NguoiDung;

public interface NguoiDungDao extends GenericDao<NguoiDung, Integer> {
	public int getMaTea();
	public int getMaStu();
	public List<NguoiDung> getListCouTea(String makh);
	public NguoiDung findByEmail(String email);
	public NguoiDung findByConfirmationToken(String confirmationToken);
	public int getNoId(String maquyen);
	public List<NguoiDung> getListTea();
}
