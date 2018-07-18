package com.donglam.webhoconline.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hvkh")
/*
 * @AssociationOverrides({ @AssociationOverride(name = "hvkhid.nguoidung",
 * joinColumns = @JoinColumn(name = "mahv")),
 * 
 * @AssociationOverride(name = "hvkhid.khoahoc", joinColumns = @JoinColumn(name
 * = "makh")) })
 */

public class HVKH {

	@EmbeddedId
	private HVKHId hvkhid;

	@Column(name = "ngaydk", nullable = true)
	private String ngaydk;

	@Column(name = "sao", nullable = true)
	private double sao;
	
	@Column(name = "noidung", nullable = true)
	private String noidung;

	public HVKH() {
	}

	public HVKH(HVKHId hvkhid, String ngaydk, double sao, String noidung) {
		super();
		this.hvkhid = hvkhid;
		this.ngaydk = ngaydk;
		this.sao = sao;
		this.noidung = noidung;
	}

	public HVKHId getHvkhid() {
		return hvkhid;
	}

	public void setHvkhid(HVKHId hvkhid) {
		this.hvkhid = hvkhid;
	}

	public String getNgaydk() {
		return ngaydk;
	}

	public void setNgaydk(String ngaydk) {
		this.ngaydk = ngaydk;
	}

	public double getSao() {
		return sao;
	}

	public void setSao(double sao) {
		this.sao = sao;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		HVKH that = (HVKH) o;

		if (getHvkhid() != null ? !getHvkhid().equals(that.getHvkhid()) : that.getHvkhid() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getHvkhid() != null ? getHvkhid().hashCode() : 0);
	}
}
