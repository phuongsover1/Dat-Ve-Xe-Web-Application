package com.example.demo.DAO;

import java.util.List;

import com.example.demo.entity.Loai_Xe;

public interface LoaiXeDAO {
	public List<Loai_Xe> getList();

	public Loai_Xe getLoaiXeFromId(int idLoaiXe);
}
