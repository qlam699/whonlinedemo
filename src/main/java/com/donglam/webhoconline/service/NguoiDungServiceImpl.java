package com.donglam.webhoconline.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donglam.webhoconline.dao.GenericDao;
import com.donglam.webhoconline.dao.NguoiDungDao;
import com.donglam.webhoconline.model.NguoiDung;

@Service("userDetailsService")
@Transactional
public class NguoiDungServiceImpl extends GenericServiceImpl<NguoiDung, Integer>
		implements NguoiDungService, UserDetailsService {
	@Autowired
	private NguoiDungDao dao;
	@Autowired
	private QuyenService qs;

	@Autowired
	public NguoiDungServiceImpl(GenericDao<NguoiDung, Integer> genericDao) {
		super(genericDao);
		this.dao = (NguoiDungDao) genericDao;
	}

	// custom method
	@Override
	public List<NguoiDung> getListTeaStu() {
		List<NguoiDung> nd = qs.get(3).getNguoidungs();
		nd.addAll(qs.get(4).getNguoidungs());
		return nd;
	}

	@Override
	public List<NguoiDung> getListStu() {
		List<NguoiDung> nd = qs.get(4).getNguoidungs();
		return nd;
	}

	@Override
	public int getMaTea() {
		return dao.getMaTea();
	}

	@Override
	public int getMaStu() {
		return dao.getMaStu();
	}

	@Override
	public List<NguoiDung> getListCouTea(String makh) {
		return dao.getListCouTea(makh);
	}

	@Override
	public NguoiDung findByEmail(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public NguoiDung findByConfirmationToken(String confirmationToken) {
		return dao.findByConfirmationToken(confirmationToken);
	}

	@Autowired
	private HttpServletRequest request;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("Vao user detail");
		NguoiDung user = null;
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		System.out.println(email);
		// if(email.contains("@")) { //local
		user = dao.findByEmail(email);
		System.out.println("go user");
		if (user.equals(null) || !user.getLoaitaikhoan().equals("local")) {
			throw new UsernameNotFoundException("User not found");
		}
		System.out.println("dang nhap ok");
		// user.getQuyen().getTenquyen()
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getQuyen().getTenquyen()));

		HttpSession session = request.getSession(true);
		session.setAttribute("CKFinder_UserRole", user.getQuyen().getTenquyen());

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getMatkhau(),
				grantedAuthorities);
	}

	@Override
	public int getNoId(String maquyen) {
		return dao.getNoId(maquyen);
	}
	/*
	 * private String getPrincipal(){ String userName = null; Object principal =
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 * 
	 * if (principal instanceof UserDetails) { userName =
	 * ((UserDetails)principal).getUsername(); } else { userName =
	 * principal.toString(); } return userName; }
	 */

	@Override
	public List<NguoiDung> getListTea() {
		return dao.getListTea();
	}
}
