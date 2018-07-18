package com.donglam.webhoconline.model;

public class GVKHDto {

	private GVKHId gvkhid;
	
	private String ngaytao;

	public GVKHDto() {
	}

	public GVKHDto(GVKHId gvkhid, String ngaytao) {
		super();
		this.gvkhid = gvkhid;
		this.ngaytao = ngaytao;
	}

	public GVKHId getGvkhid() {
		return gvkhid;
	}

	public void setGvkhid(GVKHId gvkhid) {
		this.gvkhid = gvkhid;
	}

	public String getNgaytao() {
		return ngaytao;
	}

	public void setNgaytao(String ngaytao) {
		this.ngaytao = ngaytao;
	}

}
