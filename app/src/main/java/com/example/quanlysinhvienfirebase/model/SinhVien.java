package com.example.quanlysinhvienfirebase.model;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private String id;
    private String Mssv;
    private String HoTen;
    private String Email;
    private String SoDienThoai;

    public SinhVien(String mssv, String hoTen, String email, String soDienThoai) {
        Mssv = mssv;
        HoTen = hoTen;
        Email = email;
        SoDienThoai = soDienThoai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMssv() {
        return Mssv;
    }

    public void setMssv(String mssv) {
        Mssv = mssv;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public SinhVien() {
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "id='" + id + '\'' +
                ", Mssv='" + Mssv + '\'' +
                ", HoTen='" + HoTen + '\'' +
                ", Email='" + Email + '\'' +
                ", SoDienThoai='" + SoDienThoai + '\'' +
                '}';
    }
}
