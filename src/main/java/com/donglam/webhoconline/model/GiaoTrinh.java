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
@Table(name = "giaotrinh")
public class GiaoTrinh {

	@OneToMany(mappedBy = "giaotrinh",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<KhoaHoc> khoahocs;

	@Id
	@Column(name = "magt", nullable = false)
	private String magt;

	@Column(name = "tengt", nullable = true)
	private String tengt;

	@Column(name = "tgcapnhat", nullable = true)
	private String tgcapnhat;

	@OneToMany(mappedBy = "giaotrinh",orphanRemoval=true)
	private List<Chuong> chuongs;

	@ManyToOne
	@JoinColumn(name = "magv")
	private NguoiDung nguoidung;

	@Column(name = "isdeleted", nullable = true)
	private boolean isdeleted;
	
	public GiaoTrinh() {
	}

	public GiaoTrinh(List<KhoaHoc> khoahocs, String magt, String tengt, String tgcapnhat, List<Chuong> chuongs,
			NguoiDung nguoidung) {
		super();
		this.khoahocs = khoahocs;
		this.magt = magt;
		this.tengt = tengt;
		this.tgcapnhat = tgcapnhat;
		this.chuongs = chuongs;
		this.nguoidung = nguoidung;
	}
	public GiaoTrinh(String magt, String tengt, String tgcapnhat,NguoiDung nguoidung) {
		super();
		this.magt = magt;
		this.tengt = tengt;
		this.tgcapnhat = tgcapnhat;
		this.nguoidung = nguoidung;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public List<KhoaHoc> getKhoahocs() {
		return khoahocs;
	}

	public void setKhoahocs(List<KhoaHoc> khoahocs) {
		this.khoahocs = khoahocs;
	}

	public String getMagt() {
		return magt;
	}

	public void setMagt(String magt) {
		this.magt = magt;
	}

	public String getTengt() {
		return tengt;
	}

	public void setTengt(String tengt) {
		this.tengt = tengt;
	}

	public String getTgcapnhat() {
		return tgcapnhat;
	}

	public void setTgcapnhat(String tgcapnhat) {
		this.tgcapnhat = tgcapnhat;
	}

	public List<Chuong> getChuongs() {
		return chuongs;
	}

	public void setChuongs(List<Chuong> chuongs) {
		this.chuongs = chuongs;
	}

	public NguoiDung getNguoidung() {
		return nguoidung;
	}

	public void setNguoidung(NguoiDung nguoidung) {
		this.nguoidung = nguoidung;
	}

}
