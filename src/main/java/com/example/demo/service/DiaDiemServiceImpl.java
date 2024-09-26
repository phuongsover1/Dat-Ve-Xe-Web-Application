package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DAO.DiaDiemDAO;
import com.example.demo.entity.Dia_Diem;

@Service
@Transactional
public class DiaDiemServiceImpl implements DiaDiemService {
    @Autowired
    private DiaDiemDAO diaDiemDAO;

    @Override
    public List<Dia_Diem> tatCaDiaDiem() {
        return diaDiemDAO.tatCaDiaDiem();
    }

    @Override
    public Dia_Diem getDiaDiem(String tenDiaDiem) {
        // TODO Auto-generated method stub
        return diaDiemDAO.getDiaDiem(tenDiaDiem);
    }

    @Override
    public void save(Dia_Diem diaDiem) {
        // TODO Auto-generated method stub
        diaDiemDAO.save(diaDiem);
    }

    @Override
    public Dia_Diem getDiaDiem(int idDiaDiem) {
        // TODO Auto-generated method stub

        return diaDiemDAO.getDiaDiem(idDiaDiem);
    }

    @Override
    public void delete(Dia_Diem diaDiem) {
        // TODO Auto-generated method stub
        diaDiemDAO.delete(diaDiem);
    }

    @Override
    public void update(Dia_Diem diaDiem) {
        // TODO Auto-generated method stub
        diaDiemDAO.update(diaDiem);
    }

}
