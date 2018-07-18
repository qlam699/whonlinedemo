package com.donglam.webhoconline.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class GVKHId implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="magv",referencedColumnName="mand")
	private NguoiDung nguoidung;
	@ManyToOne
	@JoinColumn(name="makh")
	private KhoaHoc khoahoc;
    
    public GVKHId() {}

	public GVKHId(NguoiDung nguoidung, KhoaHoc khoahoc) {
		super();
		this.nguoidung = nguoidung;
		this.khoahoc = khoahoc;
	}

	public NguoiDung getNguoidung() {
		return nguoidung;
	}

	public void setNguoidung(NguoiDung nguoidung) {
		this.nguoidung = nguoidung;
	}

	public KhoaHoc getKhoahoc() {
		return khoahoc;
	}

	public void setKhoahoc(KhoaHoc khoahoc) {
		this.khoahoc = khoahoc;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		GVKHId that = (GVKHId) o;
		if (nguoidung != null ? !nguoidung.equals(that.nguoidung) : that.nguoidung != null)
			return false;
		if (khoahoc != null ? !khoahoc.equals(that.khoahoc) : that.khoahoc != null)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result;
		result = (nguoidung != null ? nguoidung.hashCode() : 0);
		result = 31 * result + (khoahoc != null ? khoahoc.hashCode() : 0);
		return result;
	}
	
}
