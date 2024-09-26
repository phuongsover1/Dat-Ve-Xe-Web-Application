package com.example.demo.DAO;

import com.example.demo.entity.Tuyen_Xe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TuyenXeDAOImpl implements TuyenXeDAO {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public Tuyen_Xe getTuyenXe(int idDiemDi, int idDiemDen) {
    List<Tuyen_Xe> tuyenXeList;
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      String sHQL = "FROM Tuyen_Xe TX WHERE TX.diaDiemDi.idDiaDiem = :idDiemDi"
          + " AND TX.diaDiemDen.idDiaDiem = :idDiemDen";
      Query<Tuyen_Xe> query = currentSession.createQuery(sHQL, Tuyen_Xe.class);
      query.setParameter("idDiemDi", idDiemDi);
      query.setParameter("idDiemDen", idDiemDen);
      tuyenXeList = query.getResultList();
      currentSession.getTransaction().commit();
    }
    if (tuyenXeList.isEmpty())
      return null;
    return tuyenXeList.get(0);
  }

  @Override
  public List<Tuyen_Xe> getTuyenXeList() {
    List<Tuyen_Xe> tuyenXeList;
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      String sHQL = "FROM Tuyen_Xe";
      Query<Tuyen_Xe> query = currentSession.createQuery(sHQL, Tuyen_Xe.class);
      tuyenXeList = query.getResultList();
      currentSession.getTransaction().commit();
    }
    return tuyenXeList;
  }

  @Override
  public void save(Tuyen_Xe tuyenXe) {
    try(Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      currentSession.persist(tuyenXe);
      currentSession.getTransaction().commit();
    }
  }

  @Override
  public Tuyen_Xe getTuyenXe(int idTuyenXe) {
    Tuyen_Xe tuyenXe;
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      tuyenXe = currentSession.get(Tuyen_Xe.class, idTuyenXe);
      currentSession.getTransaction().commit();
    }
    return tuyenXe;

  }

  @Override
  public void deleteTuyenXe(int idTuyenXe) {
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      Tuyen_Xe tuyenXe = currentSession.get(Tuyen_Xe.class, idTuyenXe);
      currentSession.delete(tuyenXe);
      currentSession.getTransaction().commit();
    }
  }

  @Override
  public void update(Tuyen_Xe tuyenXe) {
    System.out.println("id Tuyen Xe update: " + tuyenXe.getIdTuyen());
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      currentSession.update(tuyenXe);
      currentSession.getTransaction().commit();
    }
  }

}
