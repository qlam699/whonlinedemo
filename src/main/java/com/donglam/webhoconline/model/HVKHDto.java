package com.donglam.webhoconline.model;

public class HVKHDto {

	private HVKHId hvkhid;

	private String ngaydk;

	private double sao;
	
	private String noidung;

	public HVKHDto() {
	}

	public HVKHDto(HVKHId hvkhid, String ngaydk, double sao, String noidung) {
		super();
		this.hvkhid = hvkhid;
		this.ngaydk = ngaydk;
		this.sao = sao;
		this.noidung = noidung;
	}

	public HVKHId getHvkhid() {
		return hvkhid;
	}

	public void setHvkhid(HVKHId hvkhid) {
		this.hvkhid = hvkhid;
	}

	public String getNgaydk() {
		return ngaydk;
	}

	public void setNgaydk(String ngaydk) {
		this.ngaydk = ngaydk;
	}

	public double getSao() {
		return sao;
	}

	public void setSao(double sao) {
		this.sao = sao;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}
}
