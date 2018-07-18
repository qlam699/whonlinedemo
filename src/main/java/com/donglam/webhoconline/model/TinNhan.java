package com.donglam.webhoconline.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tinnhan")
public class TinNhan implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "matn", nullable = false)
	private int matn;

	@Column(name = "tieude", nullable = true)
	private String tieude;

	@Column(name = "tggui", nullable = true)
	private String tggui;

	@Column(name = "noidung", nullable = true)
	private String noidung;
	
	@OneToMany(mappedBy = "ndtnid.tinnhan",cascade=CascadeType.ALL)
    private List<NDTN> ndtns=new ArrayList<NDTN>();
	
	public TinNhan() {
	}

	public TinNhan(String tieude, String tggui, String noidung) {
		super();
		this.tieude = tieude;
		this.tggui = tggui;
		this.noidung = noidung;
	}

	public int getMatn() {
		return matn;
	}

	public void setMatn(int matn) {
		this.matn = matn;
	}

	public String getTieude() {
		return tieude;
	}

	public void setTieude(String tieude) {
		this.tieude = tieude;
	}

	public String getTggui() {
		return tggui;
	}

	public void setTggui(String tggui) {
		this.tggui = tggui;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}
	
}
