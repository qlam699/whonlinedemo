package com.donglam.webhoconline.model;

import java.util.List;

public class KhoaHocDto {

	private List<GVKH> gvkhs;

	private List<HVKH> hvkhs;

	private List<ThaoLuan> thaoluans;

	private LoaiKhoaHoc loaikhoahoc;

	private String makh;

	private String tenkh;

	private String tgbdhoc;

	private String tgkthoc;

	private String tgbddk;

	private String tgktdk;

	private int soluong;

	private int soluonght;

	private double hocphi;

	private String mota;

	private String hinh;

	private boolean daduyet;

	private GiaoTrinh giaotrinh;

	private int soluot;

	private double sodiem;
	
	private boolean isdeleted;
	
	public KhoaHocDto() {
	}

	public KhoaHocDto(List<GVKH> gvkhs, List<HVKH> hvkhs, List<ThaoLuan> thaoluans, LoaiKhoaHoc loaikhoahoc,
			String makh, String tenkh, String tgbdhoc, String tgkthoc, String tgbddk,
			String tgktdk, int soluong, int soluonght, double hocphi, String mota, String hinh, boolean daduyet,
			GiaoTrinh giaotrinh) {
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

	public KhoaHocDto(LoaiKhoaHoc loaikhoahoc, String makh, String tenkh, String tgbdhoc,
			String tgkthoc, String tgbddk, String tgktdk, int soluong, int soluonght, double hocphi, String mota,
			String hinh, boolean daduyet, GiaoTrinh giaotrinh) {
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
