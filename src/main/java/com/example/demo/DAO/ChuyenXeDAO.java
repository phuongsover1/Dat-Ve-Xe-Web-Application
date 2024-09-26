package com.example.demo.DAO;

import java.util.List;

import com.example.demo.entity.Chuyen_Xe;

public interface ChuyenXeDAO {
	public List<Chuyen_Xe> getChuyenXeThoaMan(String ngayDi, int idTuyenXe);

	public Chuyen_Xe getChuyenXe(int idChuyenXe);

	public void save(Chuyen_Xe chuyenXeMoi);

	public List<Chuyen_Xe> getListChuyenXeThuocIdXe(int idXe);

	public List<Chuyen_Xe> getListChuyenXeChuaHoanThanh();

	public void update(Chuyen_Xe chuyenXeTemp);
}
