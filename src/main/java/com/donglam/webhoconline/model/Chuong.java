package com.donglam.webhoconline.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "chuong")
public class Chuong {
	@Id
	@Column(name = "machuong", nullable = false)
	private String machuong;

	@Column(name = "tenchuong", nullable = true)
	private String tenchuong;

	@ManyToOne
	@JoinColumn(name = "magt")
	private GiaoTrinh giaotrinh;

	@OneToMany(mappedBy = "chuong", orphanRemoval = true,fetch=FetchType.EAGER)
	private List<Bai> bais;

	@Column(name = "isdeleted", nullable = true)
	private boolean isdeleted;
	
	public Chuong() {
	}

	public Chuong(String machuong, String tenchuong, GiaoTrinh giaotrinh, List<Bai> bais) {
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
