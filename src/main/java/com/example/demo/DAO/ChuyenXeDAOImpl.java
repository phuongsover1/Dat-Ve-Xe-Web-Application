package com.example.demo.DAO;

import com.example.demo.entity.Chuyen_Xe;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChuyenXeDAOImpl implements ChuyenXeDAO {

  private final SessionFactory sessionFactory;

  @Override
  public List<Chuyen_Xe> getChuyenXeThoaMan(String ngayDi, int idTuyenXe) {
    List<Chuyen_Xe> result;
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      NativeQuery<Chuyen_Xe> query = currentSession.createNativeQuery("CALL Lay_Chuyen_Xe_Trong_Ngay(:Ngay,:idTuyenXe)", Chuyen_Xe.class)
          .setParameter("Ngay", ngayDi)
          .setParameter("idTuyenXe", idTuyenXe);
      result = query.list();
      currentSession.getTransaction().commit();
    }
    return result;
  }

  @Override
  public Chuyen_Xe getChuyenXe(int idChuyenXe) {
    Chuyen_Xe result;
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      result = currentSession.get(Chuyen_Xe.class, idChuyenXe);
      currentSession.getTransaction().commit();
    }
    return result;
  }

  @Override
  public void save(Chuyen_Xe chuyenXeMoi) {
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      currentSession.persist(chuyenXeMoi);
      currentSession.getTransaction().commit();
    }
  }

  @Override
  public List<Chuyen_Xe> getListChuyenXeThuocIdXe(int idXe) {
    List<Chuyen_Xe> result;
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      String sHQL = "FROM Chuyen_Xe WHERE maXe.idXe = :idXe" + " AND daHoanThanh = 0";
      Query<Chuyen_Xe> query = currentSession.createQuery(sHQL, Chuyen_Xe.class)
          .setParameter("idXe", idXe);
      result = query.getResultList();
      currentSession.getTransaction().commit();
    }
    if (result.isEmpty())
      return null;
    return result;

  }

  @Override
  public List<Chuyen_Xe> getListChuyenXeChuaHoanThanh() {
    // TODO Auto-generated method stub
    List<Chuyen_Xe> result;
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      String sHQL = "FROM Chuyen_Xe WHERE daHoanThanh = 0";
      Query<Chuyen_Xe> query = currentSession.createQuery(sHQL, Chuyen_Xe.class);
      result = query.getResultList();
      currentSession.getTransaction().commit();
    }
    if (result.isEmpty())
      return null;
    return result;
  }

  @Override
  public void update(Chuyen_Xe chuyenXeTemp) {
    // TODO Auto-generated method stub
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      currentSession.update(chuyenXeTemp);
      currentSession.getTransaction().commit();
    }
  }
}
