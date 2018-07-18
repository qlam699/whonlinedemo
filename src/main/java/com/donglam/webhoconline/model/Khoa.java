package com.donglam.webhoconline.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "khoa")
public class Khoa {

	@OneToMany(mappedBy = "khoa", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<LoaiKhoaHoc> loaikhoahocs;

	@Id
	@Column(name = "makhoa", nullable = false)
	private String makhoa;

	@Column(name = "tenkhoa", nullable = true)
	private String tenkhoa;

	@Column(name = "isdeleted", nullable = true)
	private boolean isdeleted;

	public Khoa() {
	}

	public Khoa(List<LoaiKhoaHoc> loaikhoahocs, String makhoa, String tenkhoa) {
		super();
		this.loaikhoahocs = loaikhoahocs;
		this.makhoa = makhoa;
		this.tenkhoa = tenkhoa;
	}

	public List<LoaiKhoaHoc> getLoaikhoahocs() {
		return loaikhoahocs;
	}

	public void setLoaikhoahocs(List<LoaiKhoaHoc> loaikhoahocs) {
		this.loaikhoahocs = loaikhoahocs;
	}

	public String getMakhoa() {
		return makhoa;
	}

	public void setMakhoa(String makhoa) {
		this.makhoa = makhoa;
	}

	public String getTenkhoa() {
		return tenkhoa;
	}

	public void setTenkhoa(String tenkhoa) {
		this.tenkhoa = tenkhoa;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
}
