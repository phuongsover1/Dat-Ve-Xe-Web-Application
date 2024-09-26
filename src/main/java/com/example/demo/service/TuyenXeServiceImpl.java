package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DAO.TuyenXeDAO;
import com.example.demo.entity.Tuyen_Xe;

@Service
@Transactional
public class TuyenXeServiceImpl implements TuyenXeService {

    @Autowired
    TuyenXeDAO tuyenXeDAO;

    @Override
    public Tuyen_Xe getTuyenXe(int idDiemDi, int idDiemDen) {
        // TODO Auto-generated method stub
        return tuyenXeDAO.getTuyenXe(idDiemDi, idDiemDen);
    }

    @Override
    public List<Tuyen_Xe> getTuyenXeList() {
        // TODO Auto-generated method stub
        return tuyenXeDAO.getTuyenXeList();
    }

    @Override
    public void save(Tuyen_Xe tuyenXe) {
        // TODO Auto-generated method stub
        tuyenXeDAO.save(tuyenXe);
    }

    @Override
    public Tuyen_Xe getTuyenXe(int idTuyenXe) {
        // TODO Auto-generated method stub
        return tuyenXeDAO.getTuyenXe(idTuyenXe);
    }

    @Override
    public void deleteTuyenXe(int idTuyenXe) {
        // TODO Auto-generated method stub
        tuyenXeDAO.deleteTuyenXe(idTuyenXe);
    }

    @Override
    public void update(Tuyen_Xe tuyenXe) {
        // TODO Auto-generated method stub
        tuyenXeDAO.update(tuyenXe);
    }

}
