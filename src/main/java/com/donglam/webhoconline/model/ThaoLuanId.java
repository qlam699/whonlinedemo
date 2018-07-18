package com.donglam.webhoconline.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ThaoLuanId implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "nguoidang", referencedColumnName = "mand")
	private NguoiDung nguoidung;
 
    @Column(name = "tgdang")
    private String tgdang;
    
    public ThaoLuanId() {}	

	public ThaoLuanId(NguoiDung nguoidung, String tgdang) {
		super();
		this.nguoidung = nguoidung;
		this.tgdang = tgdang;
	}
	
	public NguoiDung getNguoidung() {
		return nguoidung;
	}

	public void setNguoidung(NguoiDung nguoidung) {
		this.nguoidung = nguoidung;
	}



	public String getTgdang() {
		return tgdang;
	}



	public void setTgdang(String tgdang) {
		this.tgdang = tgdang;
	}



	/*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ThaoLuanId)) return false;
        ThaoLuanId that = (ThaoLuanId) o;
        return Objects.equals(getNguoidang(), that.getNguoidang()) &&
                Objects.equals(getTgdang(), that.getTgdang());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getNguoidang(), getTgdang());
    }*/
}
