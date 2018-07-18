package com.donglam.webhoconline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.donglam.webhoconline.dao.NguoiDungDao;

public class UserImpl implements UserDetailsService {

	@Autowired
	NguoiDungDao dao;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
	return dao.findByEmail(email);
	}
}
