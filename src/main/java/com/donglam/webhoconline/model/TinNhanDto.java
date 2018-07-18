package com.donglam.webhoconline.model;

public class TinNhanDto {

	private int matn;

	private String tieude;

	private String tggui;

	private String noidung;

	public TinNhanDto() {
	}

	public TinNhanDto(String tieude, String tggui, String noidung) {
		super();
		this.tieude = tieude;
		this.tggui = tggui;
		this.noidung = noidung;
	}

	public int getMatn() {
		return matn;
	}

	public void setMatn(int matn) {
		this.matn = matn;
	}

	public String getTieude() {
		return tieude;
	}

	public void setTieude(String tieude) {
		this.tieude = tieude;
	}

	public String getTggui() {
		return tggui;
	}

	public void setTggui(String tggui) {
		this.tggui = tggui;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

}
