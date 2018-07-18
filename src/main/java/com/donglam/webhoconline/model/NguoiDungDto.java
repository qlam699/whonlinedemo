package com.donglam.webhoconline.model;

import java.util.List;

public class NguoiDungDto {

	private List<GVKH> gvkhs;

	private List<HVKH> hvkhs;

	private List<GiaoTrinh> giaotrinhs;

	private int mand;

	private String hovaten;

	private boolean phai;

	private String ngaysinh;

	private String diachi;

	private String cmnd;

	private String email;

	private String matkhau;

	private String hinh;

	private String mota;

	private boolean kichhoat;

	private String khoabimat;

	private String loaitaikhoan;

	private String ngaycap;

	private Quyen quyen;

	private boolean isdeleted;

	public NguoiDungDto() {
	}

	public NguoiDungDto(int mand, String hovaten, boolean phai, String ngaysinh, String diachi, String cmnd,
			String email, String hinh, String mota, Quyen quyen) {
		super();
		this.mand = mand;
		this.hovaten = hovaten;
		this.phai = phai;
		this.ngaysinh = ngaysinh;
		this.diachi = diachi;
		this.cmnd = cmnd;
		this.email = email;
		this.hinh = hinh;
		this.mota = mota;
		this.quyen = quyen;
	}

	public NguoiDungDto(String hovaten, boolean phai, String ngaysinh, String diachi, String cmnd, String email,
			String hinh, String mota, Quyen quyen) {
		super();
		this.hovaten = hovaten;
		this.phai = phai;
		this.ngaysinh = ngaysinh;
		this.diachi = diachi;
		this.cmnd = cmnd;
		this.email = email;
		this.hinh = hinh;
		this.mota = mota;
		this.quyen = quyen;
	}

	public NguoiDungDto(List<GVKH> gvkhs, List<HVKH> hvkhs, List<GiaoTrinh> giaotrinhs, int mand, String hovaten,
			String ten, boolean phai, String ngaysinh, String diachi, String cmnd, String email, String matkhau,
			String hinh, String mota, boolean kichhoat, String khoabimat, String loaitaikhoan, Quyen quyen) {
		super();
		this.gvkhs = gvkhs;
		this.hvkhs = hvkhs;
		this.giaotrinhs = giaotrinhs;
		this.mand = mand;
		this.hovaten = hovaten;
		this.phai = phai;
		this.ngaysinh = ngaysinh;
		this.diachi = diachi;
		this.cmnd = cmnd;
		this.email = email;
		this.matkhau = matkhau;
		this.hinh = hinh;
		this.mota = mota;
		this.kichhoat = kichhoat;
		this.khoabimat = khoabimat;
		this.loaitaikhoan = loaitaikhoan;
		this.quyen = quyen;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getHovaten() {
		return hovaten;
	}

	public void setHovaten(String hovaten) {
		this.hovaten = hovaten;
	}

	public String getNgaycap() {
		return ngaycap;
	}

	public void setNgaycap(String ngaycap) {
		this.ngaycap = ngaycap;
	}

	public boolean isKichhoat() {
		return kichhoat;
	}

	public void setKichhoat(boolean kichhoat) {
		this.kichhoat = kichhoat;
	}

	public String getKhoabimat() {
		return khoabimat;
	}

	public void setKhoabimat(String khoabimat) {
		this.khoabimat = khoabimat;
	}

	public String getLoaitaikhoan() {
		return loaitaikhoan;
	}

	public void setLoaitaikhoan(String loaitaikhoan) {
		this.loaitaikhoan = loaitaikhoan;
	}

	public Quyen getQuyen() {
		return quyen;
	}

	public void setQuyen(Quyen quyen) {
		this.quyen = quyen;
	}

	public List<GVKH> getGvkhs() {
		return gvkhs;
	}

	public void setGvkhs(List<GVKH> gvkhs) {
		this.gvkhs = gvkhs;
	}

	public List<HVKH> getHvkhs() {
		return hvkhs;
	}

	public void setHvkhs(List<HVKH> hvkhs) {
		this.hvkhs = hvkhs;
	}

	public int getMand() {
		return mand;
	}

	public void setMand(int mand) {
		this.mand = mand;
	}

	public String gethovaten() {
		return hovaten;
	}

	public void sethovaten(String hovaten) {
		this.hovaten = hovaten;
	}

	public boolean isPhai() {
		return phai;
	}

	public void setPhai(boolean phai) {
		this.phai = phai;
	}

	public String getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public String getHinh() {
		return hinh;
	}

	public void setHinh(String hinh) {
		this.hinh = hinh;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public List<GiaoTrinh> getGiaotrinhs() {
		return giaotrinhs;
	}

	public void setGiaotrinhs(List<GiaoTrinh> giaotrinhs) {
		this.giaotrinhs = giaotrinhs;
	}

}
