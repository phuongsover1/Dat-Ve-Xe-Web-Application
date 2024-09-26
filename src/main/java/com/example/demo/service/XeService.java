package com.example.demo.service;

import com.example.demo.entity.Xe;

public interface XeService {
	public Xe getXe(int idXe);

	public Xe layXeTuBienSoXe(String bienSoXe);

	public void save(Xe xeMoi);
}
