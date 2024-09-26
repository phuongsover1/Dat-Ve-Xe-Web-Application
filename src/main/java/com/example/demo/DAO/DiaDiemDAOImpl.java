package com.example.demo.DAO;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Dia_Diem;

@Repository
@RequiredArgsConstructor
public class DiaDiemDAOImpl implements DiaDiemDAO {

    private final SessionFactory sessionFactory;

    @Override
    public List<Dia_Diem> tatCaDiaDiem() {
        List<Dia_Diem> diaDiemList;
        try (Session currentSession = sessionFactory.openSession()) {
            currentSession.beginTransaction();
            String sHQL = "FROM Dia_Diem";
            Query<Dia_Diem> query = currentSession.createQuery(sHQL, Dia_Diem.class);
             diaDiemList = query.getResultList();
        }
        return diaDiemList;
    }

    @Override
    public Dia_Diem getDiaDiem(String tenDiaDiem) {
        // TODO Auto-generated method stub
        List<Dia_Diem> diaDiemList;
        try(Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            String sHQL = "FROM Dia_Diem WHERE tenDiaDiem = '" + tenDiaDiem + "'";
            Query<Dia_Diem> query = currentSession.createQuery(sHQL, Dia_Diem.class);
            diaDiemList = query.getResultList();
        }
        if (diaDiemList.isEmpty())
            return null;
        return diaDiemList.get(0);
    }

    @Override
    public void save(Dia_Diem diaDiem) {
        // TODO Auto-generated method stub
        try (Session currentSession = sessionFactory.openSession()) {
            currentSession.beginTransaction();
            currentSession.persist(diaDiem);
            currentSession.getTransaction().commit();
        }
    }

    @Override
    public Dia_Diem getDiaDiem(int idDiaDiem) {
        // TODO Auto-generated method stub
        Dia_Diem temp;
        try (Session currentSession = sessionFactory.openSession()) {
           currentSession.beginTransaction();
            temp = currentSession.get(Dia_Diem.class, idDiaDiem);
            Hibernate.initialize(temp.getTuyenXeDiList());
            Hibernate.initialize(temp.getTuyenXeDenList());
        }
        return temp;
    }

    @Override
    public void delete(Dia_Diem diaDiem) {
        // TODO Auto-generated method stub
        try (Session currentSession = sessionFactory.openSession()) {
            currentSession.beginTransaction();
            currentSession.delete(diaDiem);
            currentSession.getTransaction().commit();
        }
    }

    @Override
    public void update(Dia_Diem diaDiem) {
        // TODO Auto-generated method stub
        try (Session currentSession = sessionFactory.openSession()) {
            currentSession.beginTransaction();
            currentSession.update(diaDiem);
            currentSession.getTransaction().commit();
        }
    }
}
