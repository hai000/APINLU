package model;

public class Subject {
	private String ma_MH = "";
	private String nhom_to = "";
	private String tin_chi = "";
	private String name_subject = "";
	private String tkb = "";
	private String ngayDangKy;
	private String idMon = "";

	public Subject(String ma_MH, String nhom_to, String tin_chi, String name_subject, String tkb, String ngayDangKy) {
		this.ma_MH = ma_MH;
		this.nhom_to = nhom_to;
		this.tin_chi = tin_chi;
		this.name_subject = name_subject;
		this.tkb = tkb;
		this.ngayDangKy = ngayDangKy;
	}

	@Override
	public String toString() {
		return "ma_MH: " + ma_MH + "\tnhom_to: " + nhom_to + "\ttin_chi: " + tin_chi + "\ttenMH:" + name_subject
				+ "\ttkb:" + tkb + ", ngayDangKy=" + ngayDangKy + "\n";
	}

	public String getMa_MH() {
		return ma_MH;
	}

	public void setMa_MH(String ma_MH) {
		this.ma_MH = ma_MH;
	}

	public String getNhom_to() {
		return nhom_to;
	}

	public void setNhom_to(String nhom_to) {
		this.nhom_to = nhom_to;
	}

	public String getTin_chi() {
		return tin_chi;
	}

	public void setTin_chi(String tin_chi) {
		this.tin_chi = tin_chi;
	}

	public String getName_subject() {
		return name_subject;
	}

	public Subject setIDMon(String idMon) {
		this.idMon = idMon;
		return this;
	}

	public void setName_subject(String name_subject) {
		this.name_subject = name_subject;
	}

	public String getTkb() {
		return tkb;
	}

	public void setTkb(String tkb) {
		this.tkb = tkb;
	}

	public String getNgayDangKyOrSLCL() {
		return ngayDangKy;
	}

	public void setNgayDangKy(String ngayDangKy) {
		this.ngayDangKy = ngayDangKy;
	}

	public String getIdMon() {
		return idMon;
	}

}
