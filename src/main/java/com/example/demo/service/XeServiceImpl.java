package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DAO.XeDAO;
import com.example.demo.entity.Xe;

@Service
@Transactional
public class XeServiceImpl implements XeService {

	@Autowired
	private XeDAO xeDAO;
	
	@Override
	public Xe getXe(int idXe) {
		// TODO Auto-generated method stub
		return xeDAO.getXe(idXe);
	}

	@Override
	public Xe layXeTuBienSoXe(String bienSoXe) {
		// TODO Auto-generated method stub
		return xeDAO.getXeTuBienSo(bienSoXe);
	}

	@Override
	public void save(Xe xeMoi) {
		xeDAO.save(xeMoi);
		
	}

}
