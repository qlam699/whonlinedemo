package com.donglam.webhoconline.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "gvkh")
/*@AssociationOverrides({ @AssociationOverride(name = "gvkhid.nguoidung", joinColumns = @JoinColumn(name = "magv")),
	@AssociationOverride(name = "gvkhid.khoahoc", joinColumns = @JoinColumn(name = "makh")) })*/

public class GVKH {
	
	@EmbeddedId
	private GVKHId gvkhid;
	
	@Column(name = "ngaytao", nullable = true)
	private String ngaytao;

	public GVKH() {
	}

	public GVKH(GVKHId gvkhid, String ngaytao) {
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

	@Transient
	public NguoiDung getNguoidung() {
		return getGvkhid().getNguoidung();
	}

	public void setNguoidung(NguoiDung nguoidung) {
		getGvkhid().setNguoidung(nguoidung);;
	}

	@Transient
	public KhoaHoc getKhoahoc() {
		return getGvkhid().getKhoahoc();
	}

	public void setKhoahoc(KhoaHoc khoahoc) {
		getGvkhid().setKhoahoc(khoahoc);;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		GVKH that = (GVKH) o;

		if (getGvkhid() != null ? !getGvkhid().equals(that.getGvkhid()) : that.getGvkhid() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getGvkhid() != null ? getGvkhid().hashCode() : 0);
	}

}
