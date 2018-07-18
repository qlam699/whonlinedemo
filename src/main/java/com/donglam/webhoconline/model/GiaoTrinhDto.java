package com.donglam.webhoconline.model;

import java.util.List;

public class GiaoTrinhDto {

	private List<KhoaHoc> khoahocs;

	private String magt;

	private String tengt;

	private String tgcapnhat;

	private List<Chuong> chuongs;

	private NguoiDung nguoidung;

	private boolean isdeleted;
	
	public GiaoTrinhDto() {
	}

	public GiaoTrinhDto(List<KhoaHoc> khoahocs, String magt, String tengt, String tgcapnhat, List<Chuong> chuongs,
			NguoiDung nguoidung) {
		super();
		this.khoahocs = khoahocs;
		this.magt = magt;
		this.tengt = tengt;
		this.tgcapnhat = tgcapnhat;
		this.chuongs = chuongs;
		this.nguoidung = nguoidung;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public List<KhoaHoc> getKhoahocs() {
		return khoahocs;
	}

	public void setKhoahocs(List<KhoaHoc> khoahocs) {
		this.khoahocs = khoahocs;
	}

	public String getMagt() {
		return magt;
	}

	public void setMagt(String magt) {
		this.magt = magt;
	}

	public String getTengt() {
		return tengt;
	}

	public void setTengt(String tengt) {
		this.tengt = tengt;
	}

	public String getTgcapnhat() {
		return tgcapnhat;
	}

	public void setTgcapnhat(String tgcapnhat) {
		this.tgcapnhat = tgcapnhat;
	}

	public List<Chuong> getChuongs() {
		return chuongs;
	}

	public void setChuongs(List<Chuong> chuongs) {
		this.chuongs = chuongs;
	}

	public NguoiDung getNguoidung() {
		return nguoidung;
	}

	public void setNguoidung(NguoiDung nguoidung) {
		this.nguoidung = nguoidung;
	}

}
