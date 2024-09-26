package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DAO.VeXeDAO;
import com.example.demo.entity.Ve_Xe;

@Service
@Transactional
public class VeXeServiceImpl implements VeXeService {

	@Autowired
	private VeXeDAO veXeDAO;

	@Override
	public void luuVe(Ve_Xe ve) {
		veXeDAO.luuVe(ve);
	}


	@Override
	public List<Ve_Xe> getVeXeList(int userId) {
		// TODO Auto-generated method stub
		return veXeDAO.getVeXeList(userId);
	}

	@Override
	public Ve_Xe getVeXe(int idVe) {
		return veXeDAO.getVeXe(idVe);
	}

	@Override
	public List<Ve_Xe> getAllVeXeChuaThanhToanList() {
		return veXeDAO.getAllVeXeChuaThanhToanList();
	}

	@Override
	public void updateVe(Ve_Xe tempVeXe) {
		veXeDAO.updateVe(tempVeXe);
	}

	@Override
	public List<Ve_Xe> getAllVeXeDaThanhToanVaHuy(int idNhanVien) {
		return veXeDAO.getAllVeXeDaThanhToanVaHuy(idNhanVien);
	}

	@Override
	public List<Ve_Xe> timKiemVe(int idKH,String idVeString, String dateInput, String tuyenDuongInput, String trangThaiInput) {

		return veXeDAO.timKiemVe(idKH,idVeString, dateInput, tuyenDuongInput, trangThaiInput);
	}

	@Override
	public List<Ve_Xe> timKiemVeNV(String idVeString, String dateInput, String tuyenDuongInput, String trangThaiInput) {
		// TODO Auto-generated method stub
		return veXeDAO.timKiemVeNV(idVeString, dateInput, tuyenDuongInput, trangThaiInput);
	}

}