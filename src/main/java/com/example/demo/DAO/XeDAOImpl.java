package com.example.demo.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Xe;

@Repository
public class XeDAOImpl implements XeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Xe getXe(int idXe) {
		Xe tempXe;
		try (Session currentSession = sessionFactory.getCurrentSession()) {
			currentSession.beginTransaction();
			tempXe = currentSession.get(Xe.class, idXe);
			currentSession.getTransaction().commit();
		}
		return tempXe;
	}

	@Override
	public Xe getXeTuBienSo(String bienSoXe) {
		// TODO Auto-generated method stub
		List<Xe> result;
		try(Session currentSession = sessionFactory.openSession()) {
			currentSession.beginTransaction();
			String sHQL = "FROM Xe WHERE bienSoXe = :bienSoXe";
			Query<Xe> query = currentSession.createQuery(sHQL, Xe.class)
					.setParameter("bienSoXe", bienSoXe);
			result = query.list();
			currentSession.getTransaction().commit();
		}

		if (result == null || result.isEmpty())
			return null;
		return result.get(0);
	}

	@Override
	public void save(Xe xeMoi) {
		try (Session currentSession = sessionFactory.getCurrentSession()) {
			currentSession.beginTransaction();
			currentSession.persist(xeMoi);
			currentSession.getTransaction().commit();
		}
	}

}
