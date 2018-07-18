package com.donglam.webhoconline.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "nguoidung")
public class NguoiDung implements UserDetails {

	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gvkhid.nguoidung", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<GVKH> gvkhs = new ArrayList<GVKH>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hvkhid.nguoidung", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<HVKH> hvkhs = new ArrayList<HVKH>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "thaoluanid.nguoidung", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<ThaoLuan> thaoluans;

	@OneToMany(mappedBy = "nguoidung", orphanRemoval = true)
	private List<GiaoTrinh> giaotrinhs;

	@OneToMany(mappedBy = "ndtnid.nguoigui", cascade = CascadeType.ALL)
	private List<NDTN> nguoiguitns;

	@OneToMany(mappedBy = "ndtnid.nguoinhan", cascade = CascadeType.ALL)
	private List<NDTN> nguoinhantns;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mand", nullable = false)
	private int mand;

	@Column(name = "hovaten", nullable = true)
	private String hovaten;

	@Column(name = "phai", nullable = true)
	private boolean phai;

	@Column(name = "ngaysinh", nullable = true)
	private String ngaysinh;

	@Column(name = "diachi", nullable = true)
	private String diachi;

	@Column(name = "cmnd", nullable = true, unique = true)
	private String cmnd;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "matkhau", nullable = true)
	@Transient
	private String matkhau;

	@Column(name = "hinh", nullable = true)
	private String hinh;

	@Column(name = "mota", nullable = true)
	private String mota;

	@Column(name = "kichhoat", nullable = true)
	private boolean kichhoat;

	@Column(name = "khoabimat", nullable = true)
	private String khoabimat;

	@ManyToOne
	@JoinColumn(name = "maquyen")
	private Quyen quyen;

	@Column(name = "loaitaikhoan", nullable = true)
	private String loaitaikhoan;

	@Column(name = "ngaycap", nullable = true)
	private String ngaycap;

	@Column(name = "isdeleted", nullable = true)
	private boolean isdeleted;

	public NguoiDung() {
	}

	public NguoiDung(int mand, String hovaten, boolean phai, String ngaysinh, String diachi, String cmnd, String email,
			String hinh, String mota, Quyen quyen) {
		super();
		this.mand = mand;
		this.hovaten = hovaten;
		this.phai = phai;
		this.ngaysinh = ngaysinh;
		this.diachi = diachi;
		this.cmnd = cmnd;
		this.email = email;
		this.hinh = hinh;
		this.mota = mota;
		this.quyen = quyen;
	}

	public NguoiDung(String hovaten, boolean phai, String ngaysinh, String diachi, String cmnd, String email,
			String hinh, String mota, Quyen quyen) {
		super();
		this.hovaten = hovaten;
		this.phai = phai;
		this.ngaysinh = ngaysinh;
		this.diachi = diachi;
		this.cmnd = cmnd;
		this.email = email;
		this.hinh = hinh;
		this.mota = mota;
		this.quyen = quyen;
	}

	public NguoiDung(List<GVKH> gvkhs, List<HVKH> hvkhs, List<GiaoTrinh> giaotrinhs, int mand, String hovaten,
			String ten, boolean phai, String ngaysinh, String diachi, String cmnd, String email, String matkhau,
			String hinh, String mota, boolean kichhoat, String khoabimat, String loaitaikhoan, Quyen quyen) {
		super();
		this.gvkhs = gvkhs;
		this.hvkhs = hvkhs;
		this.giaotrinhs = giaotrinhs;
		this.mand = mand;
		this.hovaten = hovaten;
		this.phai = phai;
		this.ngaysinh = ngaysinh;
		this.diachi = diachi;
		this.cmnd = cmnd;
		this.email = email;
		this.matkhau = matkhau;
		this.hinh = hinh;
		this.mota = mota;
		this.kichhoat = kichhoat;
		this.khoabimat = khoabimat;
		this.loaitaikhoan = loaitaikhoan;
		this.quyen = quyen;
	}

	public Quyen getQuyen() {
		return quyen;
	}

	public void setQuyen(Quyen quyen) {
		this.quyen = quyen;
	}

	public String getHovaten() {
		return hovaten;
	}

	public void setHovaten(String hovaten) {
		this.hovaten = hovaten;
	}

	public String getNgaycap() {
		return ngaycap;
	}

	public void setNgaycap(String ngaycap) {
		this.ngaycap = ngaycap;
	}

	public List<GVKH> getGvkhs() {
		return gvkhs;
	}

	public void setGvkhs(List<GVKH> gvkhs) {
		this.gvkhs = gvkhs;
	}

	public List<HVKH> getHvkhs() {
		return hvkhs;
	}

	public void setHvkhs(List<HVKH> hvkhs) {
		this.hvkhs = hvkhs;
	}

	public List<GiaoTrinh> getGiaotrinhs() {
		return giaotrinhs;
	}

	public void setGiaotrinhs(List<GiaoTrinh> giaotrinhs) {
		this.giaotrinhs = giaotrinhs;
	}

	public List<ThaoLuan> getThaoluans() {
		return thaoluans;
	}

	public void setThaoluans(List<ThaoLuan> thaoluans) {
		this.thaoluans = thaoluans;
	}

	public int getMand() {
		return mand;
	}

	public void setMand(int mand) {
		this.mand = mand;
	}

	public String gethovaten() {
		return hovaten;
	}

	public void sethovaten(String hovaten) {
		this.hovaten = hovaten;
	}

	public boolean isPhai() {
		return phai;
	}

	public void setPhai(boolean phai) {
		this.phai = phai;
	}

	public String getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public String getHinh() {
		return hinh;
	}

	public void setHinh(String hinh) {
		this.hinh = hinh;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public boolean isKichhoat() {
		return kichhoat;
	}

	public void setKichhoat(boolean kichhoat) {
		this.kichhoat = kichhoat;
	}

	public String getKhoabimat() {
		return khoabimat;
	}

	public void setKhoabimat(String khoabimat) {
		this.khoabimat = khoabimat;
	}

	public String getLoaitaikhoan() {
		return loaitaikhoan;
	}

	public void setLoaitaikhoan(String loaitaikhoan) {
		this.loaitaikhoan = loaitaikhoan;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

}
