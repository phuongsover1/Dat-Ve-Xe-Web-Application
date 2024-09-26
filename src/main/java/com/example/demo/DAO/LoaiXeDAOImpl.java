package com.example.demo.DAO;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Loai_Xe;

@Repository
public class LoaiXeDAOImpl implements LoaiXeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Loai_Xe> getList() {
		List<Loai_Xe> list;
		try (Session currentSession = sessionFactory.openSession()) {
			currentSession.beginTransaction();
			String sHQL = "FROM Loai_Xe";
			Query<Loai_Xe> query = currentSession.createQuery(sHQL, Loai_Xe.class);
			list = query.list();
			currentSession.getTransaction().commit();
		}
		return list;
	}

	@Override
	public Loai_Xe getLoaiXeFromId(int idLoaiXe) {
		Loai_Xe loaiXe;
		try (Session currentSession = sessionFactory.openSession()) {
				currentSession.beginTransaction();
				loaiXe = currentSession.get(Loai_Xe.class, idLoaiXe);
				currentSession.getTransaction().commit();
		}
		return loaiXe;
	}

}
