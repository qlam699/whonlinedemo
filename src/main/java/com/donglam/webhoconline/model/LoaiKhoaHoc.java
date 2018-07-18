package com.donglam.webhoconline.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "loaikhoahoc")
public class LoaiKhoaHoc {
	
	@ManyToOne
    @JoinColumn(name="makhoa", nullable=false)
    private Khoa khoa;
	
	@OneToMany(mappedBy="loaikhoahoc", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<KhoaHoc> khoahocs;
	
	@Id
	@Column(name = "maloaikh", nullable = false)
	private String maloaikh;

	@Column(name = "tenloaikh", nullable = true)
	private String tenloaikh;
	
	@Column(name = "isdeleted", nullable = true)
	private boolean isdeleted;
	
	public LoaiKhoaHoc() {
	}

	public LoaiKhoaHoc(Khoa khoa, List<KhoaHoc> khoahocs, String maloaikh, String tenloaikh) {
		super();
		this.khoa = khoa;
		this.khoahocs = khoahocs;
		this.maloaikh = maloaikh;
		this.tenloaikh = tenloaikh;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public Khoa getKhoa() {
		return khoa;
	}

	public void setKhoa(Khoa khoa) {
		this.khoa = khoa;
	}

	public List<KhoaHoc> getKhoahocs() {
		return khoahocs;
	}

	public void setKhoahocs(List<KhoaHoc> khoahocs) {
		this.khoahocs = khoahocs;
	}

	public String getMaloaikh() {
		return maloaikh;
	}

	public void setMaloaikh(String maloaikh) {
		this.maloaikh = maloaikh;
	}

	public String getTenloaikh() {
		return tenloaikh;
	}

	public void setTenloaikh(String tenloaikh) {
		this.tenloaikh = tenloaikh;
	}

}
