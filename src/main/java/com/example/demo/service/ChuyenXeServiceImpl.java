package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DAO.ChuyenXeDAO;
import com.example.demo.entity.Chuyen_Xe;


@Service
@Transactional
public class ChuyenXeServiceImpl implements ChuyenXeService {
	@Autowired
	ChuyenXeDAO chuyenXeDAO;
	
	
	@Override
	public List<Chuyen_Xe> getChuyenXeThoaMan(String ngayDi, int idChuyenXe) {
		return chuyenXeDAO.getChuyenXeThoaMan(ngayDi, idChuyenXe);
	}

	@Override
	public Chuyen_Xe getChuyenXe(int idChuyenXe) {
		
		return chuyenXeDAO.getChuyenXe(idChuyenXe);
	}

	@Override
	public void save(Chuyen_Xe chuyenXeMoi) {
		chuyenXeDAO.save(chuyenXeMoi);
	}

	
	@Override
	public List<Chuyen_Xe> getListChuyenXeThuocIdXe(int idXe) {
		// TODO Auto-generated method stub
		return chuyenXeDAO.getListChuyenXeThuocIdXe(idXe);
	}
	
	@Override
	public List<Chuyen_Xe> getListChuyenXeChuaHoanThanh() {
		// TODO Auto-generated method stub
		return chuyenXeDAO.getListChuyenXeChuaHoanThanh();
	}

	@Override
	public void update(Chuyen_Xe chuyenXeTemp) {
		// TODO Auto-generated method stub
		chuyenXeDAO.update(chuyenXeTemp);
	}

}
