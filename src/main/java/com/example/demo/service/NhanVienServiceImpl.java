package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DAO.NhanVienDAO;
import com.example.demo.entity.Nhan_Vien;

@Service
@Transactional
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienDAO nhanVienDAO;

    @Override
    public Nhan_Vien getNhanVienFromUsername(String username) {
        return nhanVienDAO.getNhanVienFromUsername(username);
    }

    @Override
    public Nhan_Vien getNhanVienFromId(int idNhanVien) {
        return nhanVienDAO.getNhanVienFromId(idNhanVien);
    }

    @Override
    public void updateNV(Nhan_Vien tempNV) {
        nhanVienDAO.updateNV(tempNV);

    }

    @Override
    public List<Nhan_Vien> getListNV() {
        // TODO Auto-generated method stub
        return nhanVienDAO.getListNV();
    }

    @Override
    public void xoaNV(int nvID) {
        // TODO Auto-generated method stub
        nhanVienDAO.xoaNV(nvID);
    }

    @Override
    public void themNV(Nhan_Vien nhanVien) {
        // TODO Auto-generated method stub
        nhanVienDAO.themNV(nhanVien);
    }

}
