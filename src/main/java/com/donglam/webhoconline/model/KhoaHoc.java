package com.donglam.webhoconline.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "khoahoc")
public class KhoaHoc {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gvkhid.khoahoc", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<GVKH> gvkhs = new ArrayList<GVKH>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hvkhid.khoahoc", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<HVKH> hvkhs = new ArrayList<HVKH>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "khoahoc", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<ThaoLuan> thaoluans;

	@ManyToOne
	@JoinColumn(name = "maloaikh", nullable = false)
	private LoaiKhoaHoc loaikhoahoc;

	@Id
	@Column(name = "makh", nullable = false)
	private String makh;

	@Column(name = "tenkh", nullable = true)
	private String tenkh;

	@Column(name = "tgbdhoc", nullable = true)
	private String tgbdhoc;

	@Column(name = "tgkthoc", nullable = true)
	private String tgkthoc;

	@Column(name = "tgbddk", nullable = true)
	private String tgbddk;

	@Column(name = "tgktdk", nullable = true)
	private String tgktdk;

	@Column(name = "soluong", nullable = true)
	private int soluong;
	
	@Column(name = "soluonght", nullable = true)
	private int soluonght;
	
	@Column(name = "hocphi", nullable = true)
	private double hocphi;

	@Column(name = "mota", nullable = true)
	private String mota;

	@Column(name = "hinh", nullable = true)
	private String hinh;

	@Column(name = "daduyet", nullable = true)
	private boolean daduyet;
	
	@Column(name = "soluot", nullable = true)
	private int soluot;
	
	@Column(name = "sodiem", nullable = true)
	private double sodiem;
	
	@ManyToOne
	@JoinColumn(name = "magt", nullable = true)
	private GiaoTrinh giaotrinh;
	
	@Column(name = "isdeleted", nullable = true)
	private boolean isdeleted;
	
	public KhoaHoc() {
	}

	public KhoaHoc(List<GVKH> gvkhs, List<HVKH> hvkhs, List<ThaoLuan> thaoluans, LoaiKhoaHoc loaikhoahoc, String makh,
			String tenkh, String tgbdhoc, String tgkthoc, String tgbddk, String tgktdk,
			int soluong, int soluonght, double hocphi, String mota, String hinh, boolean daduyet, GiaoTrinh giaotrinh) {
		super();
		this.gvkhs = gvkhs;
		this.hvkhs = hvkhs;
		this.thaoluans = thaoluans;
		this.loaikhoahoc = loaikhoahoc;
		this.makh = makh;
		this.tenkh = tenkh;
		this.tgbdhoc = tgbdhoc;
		this.tgkthoc = tgkthoc;
		this.tgbddk = tgbddk;
		this.tgktdk = tgktdk;
		this.soluong = soluong;
		this.soluonght = soluonght;
		this.hocphi = hocphi;
		this.mota = mota;
		this.hinh = hinh;
		this.daduyet = daduyet;
		this.giaotrinh = giaotrinh;
	}
	public KhoaHoc(LoaiKhoaHoc loaikhoahoc, String makh,
			String tenkh, String tgbdhoc, String tgkthoc, String tgbddk, String tgktdk,
			int soluong, int soluonght, double hocphi, String mota, String hinh, boolean daduyet, GiaoTrinh giaotrinh) {
		super();
		this.loaikhoahoc = loaikhoahoc;
		this.makh = makh;
		this.tenkh = tenkh;
		this.tgbdhoc = tgbdhoc;
		this.tgkthoc = tgkthoc;
		this.tgbddk = tgbddk;
		this.tgktdk = tgktdk;
		this.soluong = soluong;
		this.soluonght = soluonght;
		this.hocphi = hocphi;
		this.mota = mota;
		this.hinh = hinh;
		this.daduyet = daduyet;
		this.giaotrinh = giaotrinh;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public int getSoluot() {
		return soluot;
	}

	public void setSoluot(int soluot) {
		this.soluot = soluot;
	}

	public double getSodiem() {
		return sodiem;
	}

	public void setSodiem(double sodiem) {
		this.sodiem = sodiem;
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

	public List<ThaoLuan> getThaoluans() {
		return thaoluans;
	}

	public void setThaoluans(List<ThaoLuan> thaoluans) {
		this.thaoluans = thaoluans;
	}

	public LoaiKhoaHoc getLoaikhoahoc() {
		return loaikhoahoc;
	}

	public void setLoaikhoahoc(LoaiKhoaHoc loaikhoahoc) {
		this.loaikhoahoc = loaikhoahoc;
	}

	public String getMakh() {
		return makh;
	}

	public void setMakh(String makh) {
		this.makh = makh;
	}

	public String getTenkh() {
		return tenkh;
	}

	public void setTenkh(String tenkh) {
		this.tenkh = tenkh;
	}

	public String getTgbdhoc() {
		return tgbdhoc;
	}

	public void setTgbdhoc(String tgbdhoc) {
		this.tgbdhoc = tgbdhoc;
	}

	public String getTgkthoc() {
		return tgkthoc;
	}

	public void setTgkthoc(String tgkthoc) {
		this.tgkthoc = tgkthoc;
	}

	public String getTgbddk() {
		return tgbddk;
	}

	public void setTgbddk(String tgbddk) {
		this.tgbddk = tgbddk;
	}

	public String getTgktdk() {
		return tgktdk;
	}

	public void setTgktdk(String tgktdk) {
		this.tgktdk = tgktdk;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public int getSoluonght() {
		return soluonght;
	}

	public void setSoluonght(int soluonght) {
		this.soluonght = soluonght;
	}

	public double getHocphi() {
		return hocphi;
	}

	public void setHocphi(double hocphi) {
		this.hocphi = hocphi;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public String getHinh() {
		return hinh;
	}

	public void setHinh(String hinh) {
		this.hinh = hinh;
	}

	public boolean isDaduyet() {
		return daduyet;
	}

	public void setDaduyet(boolean daduyet) {
		this.daduyet = daduyet;
	}

	public GiaoTrinh getGiaotrinh() {
		return giaotrinh;
	}

	public void setGiaotrinh(GiaoTrinh giaotrinh) {
		this.giaotrinh = giaotrinh;
	}

}
