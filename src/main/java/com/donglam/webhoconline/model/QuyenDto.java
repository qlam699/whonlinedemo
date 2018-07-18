package com.donglam.webhoconline.model;

public class QuyenDto {

	private int maquyen;

	private String tenquyen;
	
	private boolean isdeleted;
	
	public QuyenDto() {
	}

	public QuyenDto(String tenquyen) {
		super();
		this.tenquyen = tenquyen;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public int getMaquyen() {
		return maquyen;
	}

	public void setMaquyen(int maquyen) {
		this.maquyen = maquyen;
	}

	public String getTenquyen() {
		return tenquyen;
	}

	public void setTenquyen(String tenquyen) {
		this.tenquyen = tenquyen;
	}

}
