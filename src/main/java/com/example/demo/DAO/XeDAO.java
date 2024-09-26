package com.example.demo.DAO;

import com.example.demo.entity.Xe;

public interface XeDAO {
	public Xe getXe(int idXe);

	public Xe getXeTuBienSo(String bienSoXe);

	public void save(Xe xeMoi);
}
