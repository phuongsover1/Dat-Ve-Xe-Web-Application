package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Loai_Xe;

public interface LoaiXeService {
	
	public List<Loai_Xe> getList();

	public Loai_Xe getLoaiXeFromId(int idLoaiXe);
}
