package model;

public class Lesson implements Comparable<Lesson> {
	private String ma_lop = "";
	private String ma_mon = "";
	private String ma_nhom = "";
	private String ma_phong = "";
	private String ngay_hoc = "";
	private int so_tiet = -1;
	private String so_tin_chi = "";
	private String ten_mon = "";
	private String ten_giang_vien = "";
	private int thu_kieu_so = -1;
	private int tiet_bat_dau = -1;
	private Lesson lessonLab = null;

	public Lesson(String ma_lop, String ma_mon, String ma_nhom, String ma_phong, String ngay_hoc, int so_tiet,
			String so_tin_chi, String ten_mon, String ten_giang_vien, int thu_kieu_so, int tiet_bat_dau) {
		super();
		this.ma_lop = ma_lop;
		this.ma_mon = ma_mon;
		this.ma_nhom = ma_nhom;
		this.ma_phong = ma_phong;
		this.ngay_hoc = ngay_hoc;
		this.so_tiet = so_tiet;
		this.so_tin_chi = so_tin_chi;
		this.ten_mon = ten_mon;
		this.ten_giang_vien = ten_giang_vien;
		this.thu_kieu_so = thu_kieu_so;
		this.tiet_bat_dau = tiet_bat_dau;
	}

	public String getMa_lop() {
		return ma_lop;
	}

	public void setMa_lop(String ma_lop) {
		this.ma_lop = ma_lop;
	}

	public String getMa_mon() {
		return ma_mon;
	}

	public void setMa_mon(String ma_mon) {
		this.ma_mon = ma_mon;
	}

	public String getMa_nhom() {
		return ma_nhom;
	}

	public void setMa_nhom(String ma_nhom) {
		this.ma_nhom = ma_nhom;
	}

	public String getMa_phong() {
		return ma_phong;
	}

	public void setMa_phong(String ma_phong) {
		this.ma_phong = ma_phong;
	}

	public String getNgay_hoc() {
		return ngay_hoc;
	}

	public void setNgay_hoc(String ngay_hoc) {
		this.ngay_hoc = ngay_hoc;
	}

	public int getSo_tiet() {
		return so_tiet;
	}

	public void setSo_tiet(int so_tiet) {
		this.so_tiet = so_tiet;
	}

	public String getSo_tin_chi() {
		return so_tin_chi;
	}

	public void setSo_tin_chi(String so_tin_chi) {
		this.so_tin_chi = so_tin_chi;
	}

	public String getTen_mon() {
		return ten_mon;
	}

	public void setTen_mon(String ten_mon) {
		this.ten_mon = ten_mon;
	}

	public String getTen_giang_vien() {
		return ten_giang_vien;
	}

	public void setTen_giang_vien(String ten_giang_vien) {
		this.ten_giang_vien = ten_giang_vien;
	}

	public int getThu_kieu_so() {
		return thu_kieu_so;
	}

	public void setThu_kieu_so(int thu_kieu_so) {
		this.thu_kieu_so = thu_kieu_so;
	}

	public int getTiet_bat_dau() {
		return tiet_bat_dau;
	}

	public void setTiet_bat_dau(int tiet_bat_dau) {
		this.tiet_bat_dau = tiet_bat_dau;
	}

	@Override
	public String toString() {
		return "ma_lop=" + ma_lop + ", ma_mon=" + ma_mon + ", ma_nhom=" + ma_nhom + ", ma_phong=" + ma_phong
				+ ", ngay_hoc=" + ngay_hoc + ", so_tiet=" + so_tiet + ", so_tin_chi=" + so_tin_chi + ", ten_mon="
				+ ten_mon + ", ten_giang_vien=" + ten_giang_vien + ", thu_kieu_so=" + thu_kieu_so + ", tiet_bat_dau="
				+ tiet_bat_dau + "\n";
	}

	@Override
	public int compareTo(Lesson o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.tiet_bat_dau, o.tiet_bat_dau);
	}
	public Lesson setLessonLab(Lesson lessonLab) {
		this.lessonLab=lessonLab;
		return this;
	}
	public Lesson getLessonLab() {
		return lessonLab;
	}

}
