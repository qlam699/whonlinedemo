package com.donglam.webhoconline.model;

import java.util.List;

public class KhoaDto {

	private List<LoaiKhoaHoc> loaikhoahocs;

	private String makhoa;

	private String tenkhoa;

	private boolean isdeleted;

	public KhoaDto() {
	}

	public KhoaDto(List<LoaiKhoaHoc> loaikhoahocs, String makhoa, String tenkhoa) {
		super();
		this.loaikhoahocs = loaikhoahocs;
		this.makhoa = makhoa;
		this.tenkhoa = tenkhoa;
	}

	public List<LoaiKhoaHoc> getLoaikhoahocs() {
		return loaikhoahocs;
	}

	public void setLoaikhoahocs(List<LoaiKhoaHoc> loaikhoahocs) {
		this.loaikhoahocs = loaikhoahocs;
	}

	public String getMakhoa() {
		return makhoa;
	}

	public void setMakhoa(String makhoa) {
		this.makhoa = makhoa;
	}

	public String getTenkhoa() {
		return tenkhoa;
	}

	public void setTenkhoa(String tenkhoa) {
		this.tenkhoa = tenkhoa;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

}
