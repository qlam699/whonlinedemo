package com.donglam.webhoconline.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "quyen")
public class Quyen {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "maquyen", nullable = false)
	private int maquyen;

	@Column(name = "tenquyen", nullable = true)
	private String tenquyen;
	
	@Column(name = "isdeleted", nullable = true)
	private boolean isdeleted;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "quyen", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<NguoiDung> nguoidungs = new ArrayList<NguoiDung>();

	public Quyen() {
	}

	public Quyen(String tenquyen) {
		super();
		this.tenquyen = tenquyen;
	}
	
	public List<NguoiDung> getNguoidungs() {
		return nguoidungs;
	}

	public void setNguoidungs(List<NguoiDung> nguoidungs) {
		this.nguoidungs = nguoidungs;
	}

	public Quyen(int maquyen, String tenquyen, List<NguoiDung> nguoidungs) {
		super();
		this.maquyen = maquyen;
		this.tenquyen = tenquyen;
		this.nguoidungs = nguoidungs;
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

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

}
