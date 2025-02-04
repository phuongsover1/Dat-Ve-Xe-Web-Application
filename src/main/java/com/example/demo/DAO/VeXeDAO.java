package com.example.demo.DAO;

import java.util.List;

import com.example.demo.entity.Ve_Xe;

public interface VeXeDAO {
	public void luuVe(Ve_Xe ve);

	public List<Ve_Xe> getVeXeList(int userId);

	public List<Ve_Xe> getAllVeXeChuaThanhToanList();

	public List<Ve_Xe> timKiemVe(int idKH,String idVeString, String dateInput, String tuyenDuongInput, String trangThaiInput);

	public List<Ve_Xe> timKiemVeNV(String idVeString, String dateInput, String tuyenDuongInput,String trangThaiInput);

	public Ve_Xe getVeXe(int idVe);

	public void updateVe(Ve_Xe tempVeXe);


    List<Ve_Xe> getAllVeXeDaThanhToanVaHuy(int idNhanVien);
}