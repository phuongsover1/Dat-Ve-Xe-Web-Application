package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DAO.LoaiXeDAO;
import com.example.demo.entity.Loai_Xe;

@Service
@Transactional
public class LoaiXeServiceImpl implements LoaiXeService {

	@Autowired
	private LoaiXeDAO loaiXeDAO;
	
	
	@Override
	public List<Loai_Xe> getList() {
		// TODO Auto-generated method stub
		return loaiXeDAO.getList();
	}

	@Override
	public Loai_Xe getLoaiXeFromId(int idLoaiXe) {
		// TODO Auto-generated method stub
		return loaiXeDAO.getLoaiXeFromId(idLoaiXe);
	}

}
