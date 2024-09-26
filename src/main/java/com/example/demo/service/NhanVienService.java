package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Nhan_Vien;

public interface NhanVienService {
    public Nhan_Vien getNhanVienFromUsername(String username);

    public Nhan_Vien getNhanVienFromId(int idNhanVien);

    public void updateNV(Nhan_Vien tempNV);

    public List<Nhan_Vien> getListNV();

    public void xoaNV(int nvID);

    public void themNV(Nhan_Vien nhanVien);
}
