package com.donglam.webhoconline.model;

public class NDTNDto {

	private NDTNId ndtnid;

	public NDTNDto() {
	}

	public NDTNDto(NDTNId ndtnid) {
		super();
		this.ndtnid = ndtnid;
	}

	public NDTNId getNdtnid() {
		return ndtnid;
	}

	public void setNdtnid(NDTNId ndtnid) {
		this.ndtnid = ndtnid;
	}

}
