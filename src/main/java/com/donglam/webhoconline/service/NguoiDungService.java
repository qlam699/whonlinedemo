package com.donglam.webhoconline.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.donglam.webhoconline.model.NguoiDung;

public interface NguoiDungService extends GenericService<NguoiDung, Integer> {

	// custom method
	public List<NguoiDung> getListTeaStu();
	public List<NguoiDung> getListStu();
	public int getMaTea();
	public int getMaStu();
	public List<NguoiDung> getListCouTea(String makh);
	public NguoiDung findByEmail(String email);
	public NguoiDung findByConfirmationToken(String confirmationToken);
	public int getNoId(String maquyen);
	public UserDetails loadUserByUsername(String email);
	public List<NguoiDung> getListTea();
}
