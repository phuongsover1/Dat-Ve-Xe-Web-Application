package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Tuyen_Xe;

public interface TuyenXeService {
    public Tuyen_Xe getTuyenXe(int idDiemDi, int idDiemDen);

    public List<Tuyen_Xe> getTuyenXeList();

    public void save(Tuyen_Xe tuyenXe);

    public Tuyen_Xe getTuyenXe(int idTuyenXe);

    public void deleteTuyenXe(int idTuyenXe);

    public void update(Tuyen_Xe tuyenXe);
}
