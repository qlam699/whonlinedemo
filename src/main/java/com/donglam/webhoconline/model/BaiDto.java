package com.donglam.webhoconline.model;

public class BaiDto {

	private String mabai;
	
	private String tenbai;

	private Chuong chuong;

	private String noidung;

	private boolean trangthai;
	
	private boolean isdeleted;
	
	public BaiDto() {
	}

	public BaiDto(String mabai, String tenbai, Chuong chuong, String noidung, boolean trangthai) {
		super();
		this.mabai = mabai;
		this.tenbai = tenbai;
		this.chuong = chuong;
		this.noidung = noidung;
		this.trangthai = trangthai;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getMabai() {
		return mabai;
	}

	public void setMabai(String mabai) {
		this.mabai = mabai;
	}

	public String getTenbai() {
		return tenbai;
	}

	public void setTenbai(String tenbai) {
		this.tenbai = tenbai;
	}

	public Chuong getChuong() {
		return chuong;
	}

	public void setChuong(Chuong chuong) {
		this.chuong = chuong;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public boolean isTrangthai() {
		return trangthai;
	}

	public void setTrangthai(boolean trangthai) {
		this.trangthai = trangthai;
	}



}
