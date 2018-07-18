package com.donglam.webhoconline.model;

import java.util.List;

public class ChuongDto {

	private String machuong;

	private String tenchuong;

	private GiaoTrinh giaotrinh;

	private List<Bai> bais;
	
	private boolean isdeleted;
	
	public ChuongDto() {
	}

	public ChuongDto(String machuong, String tenchuong, GiaoTrinh giaotrinh, List<Bai> bais) {
		super();
		this.machuong = machuong;
		this.tenchuong = tenchuong;
		this.giaotrinh = giaotrinh;
		this.bais = bais;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getMachuong() {
		return machuong;
	}

	public void setMachuong(String machuong) {
		this.machuong = machuong;
	}

	public String getTenchuong() {
		return tenchuong;
	}

	public void setTenchuong(String tenchuong) {
		this.tenchuong = tenchuong;
	}

	public GiaoTrinh getGiaotrinh() {
		return giaotrinh;
	}

	public void setGiaotrinh(GiaoTrinh giaotrinh) {
		this.giaotrinh = giaotrinh;
	}

	public List<Bai> getBais() {
		return bais;
	}

	public void setBais(List<Bai> bais) {
		this.bais = bais;
	}



}
