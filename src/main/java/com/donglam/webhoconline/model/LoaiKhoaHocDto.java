package com.donglam.webhoconline.model;

import java.util.List;

public class LoaiKhoaHocDto {

	private Khoa khoa;
	
	private List<KhoaHoc> khoahocs;

	private String maloaikh;

	private String tenloaikh;

	private boolean isdeleted;
	
	public LoaiKhoaHocDto() {
	}

	public LoaiKhoaHocDto(Khoa khoa, List<KhoaHoc> khoahocs, String maloaikh, String tenloaikh) {
		super();
		this.khoa = khoa;
		this.khoahocs = khoahocs;
		this.maloaikh = maloaikh;
		this.tenloaikh = tenloaikh;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public Khoa getKhoa() {
		return khoa;
	}

	public void setKhoa(Khoa khoa) {
		this.khoa = khoa;
	}

	public List<KhoaHoc> getKhoahocs() {
		return khoahocs;
	}

	public void setKhoahocs(List<KhoaHoc> khoahocs) {
		this.khoahocs = khoahocs;
	}

	public String getMaloaikh() {
		return maloaikh;
	}

	public void setMaloaikh(String maloaikh) {
		this.maloaikh = maloaikh;
	}

	public String getTenloaikh() {
		return tenloaikh;
	}

	public void setTenloaikh(String tenloaikh) {
		this.tenloaikh = tenloaikh;
	}

}
