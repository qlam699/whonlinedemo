package com.donglam.webhoconline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bai")
public class Bai {
	@Id
	@Column(name = "mabai", nullable = false)
	private String mabai;

	@Column(name = "tenbai", nullable = true)
	private String tenbai;

	@ManyToOne
    @JoinColumn(name = "machuong")
	private Chuong chuong;

	@Column(name = "noidung", nullable = true)
	private String noidung;

	@Column(name = "trangthai", nullable = true)
	private boolean trangthai;
	
	@Column(name = "isdeleted", nullable = true)
	private boolean isdeleted;

	public Bai() {
	}

	public Bai(String mabai, String tenbai, Chuong chuong, String noidung, boolean trangthai) {
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
