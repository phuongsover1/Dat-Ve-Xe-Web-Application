package com.example.demo.DAO;

import com.example.demo.entity.Ve_Xe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VeXeDAOImpl implements VeXeDAO {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void luuVe(Ve_Xe ve) {
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      currentSession.persist(ve);
      currentSession.getTransaction().commit();
    }

  }

  @Override
  public List<Ve_Xe> getVeXeList(int userId) {
    List<Ve_Xe> result;
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      NativeQuery<Ve_Xe> query = currentSession.createNativeQuery("CALL GET_VE_XE_LIST(:userId)", Ve_Xe.class)
          .setParameter("userId", userId);
      result = query.list();
      currentSession.getTransaction().commit();
    }
    return result;
  }

  @Override
  public Ve_Xe getVeXe(int idVe) {
    Ve_Xe tempVeXe;
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      tempVeXe = currentSession.get(Ve_Xe.class, idVe);
      currentSession.getTransaction().commit();
    }
    return tempVeXe;
  }

  @Override
  public List<Ve_Xe> getAllVeXeChuaThanhToanList() {
    List<Ve_Xe> result;
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      NativeQuery<Ve_Xe> query = currentSession.createNativeQuery("CALL GET_ALL_VE_XE_CHUA_THANH_TOAN()", Ve_Xe.class);
      result = query.list();
      currentSession.getTransaction().commit();
    }
    return result;
  }

  @Override
  public void updateVe(Ve_Xe tempVeXe) {
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      currentSession.update(tempVeXe);
      currentSession.getTransaction().commit();
    }
  }

  @Override
  public List<Ve_Xe> getAllVeXeDaThanhToanVaHuy(int idNhanVien) {
    List<Ve_Xe> veXeList;
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      String SHQL = "FROM Ve_Xe vx WHERE vx.idNhanVien.idNhanVien = :idNhanVien";
      Query<Ve_Xe> query = currentSession.createQuery(SHQL, Ve_Xe.class).setParameter("idNhanVien", idNhanVien);
      veXeList = query.list();
      currentSession.getTransaction().commit();
    }
    return veXeList;
  }

  @Override
  public List<Ve_Xe> timKiemVe(int idKH, String idVeString, String dateInput, String tuyenDuongInput,
                               String trangThaiInput) {
    List<Ve_Xe> result;
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      if (!idVeString.isEmpty()) {
        int idVe = Integer.parseInt(idVeString);
        NativeQuery<Ve_Xe> query = currentSession.createNativeQuery("CALL TIM_KIEM_VE(:idKH ,:maVe, null, null, null, null)", Ve_Xe.class)
            .setParameter("maVe", idVe).setParameter("idKH", idKH);
        result = query.list();
      } else {
        if (!dateInput.isEmpty() && tuyenDuongInput.equals("default") && trangThaiInput.equals("default")) // o x x
        {
          NativeQuery<Ve_Xe> query = currentSession.createNativeQuery("CALL TIM_KIEM_VE(:idKH, null, :dateInput, null, null, null)", Ve_Xe.class)
              .setParameter("idKH", idKH)
              .setParameter("dateInput", dateInput);
          result = query.list();
          ///////////////////////////////////////// sửa lại không dùng setParameter nữa
        } else if (!dateInput.isEmpty() && !tuyenDuongInput.equals("default")
            && trangThaiInput.equals("default")) { // o o x
          // tách tuyến đường input ra thành 2 địa điểm đi đến
          String[] tuyenDuongArray = tuyenDuongInput.split(",");
          int diaDiemDi = Integer.parseInt(tuyenDuongArray[0]);
          int diaDiemDen = Integer.parseInt(tuyenDuongArray[1]);
          NativeQuery<Ve_Xe> query = currentSession
              .createNativeQuery(
                  "CALL TIM_KIEM_VE (:idKH,null,:dateInput,:diaDiemDi,:diaDiemDen,null)", Ve_Xe.class)
              .setParameter("idKH", idKH)
              .setParameter("dateInput", dateInput)
              .setParameter("diaDiemDi", diaDiemDi)
              .setParameter("diaDiemDen", diaDiemDen);
          result = query.list();
        } else if (!dateInput.isEmpty() && tuyenDuongInput.equals("default")
            && !trangThaiInput.equals("default")) { // o x o

          NativeQuery<Ve_Xe> query = currentSession.createNativeQuery("CALL TIM_KIEM_VE(:idKH,null, :dateInput,null,null,:trangThaiInput)", Ve_Xe.class)
              .setParameter("idKH", idKH)
              .setParameter("dateInput", dateInput)
              .setParameter("trangThaiInput", trangThaiInput);
          result = query.list();
        } else if (!dateInput.equals("") && !tuyenDuongInput.equals("default")
            && !trangThaiInput.equals("default")) { // o o o
          // tách tuyến đường input ra thành 2 địa điểm đi đến
          String[] tuyenDuongArray = tuyenDuongInput.split(",");
          int diaDiemDi = Integer.parseInt(tuyenDuongArray[0]);
          int diaDiemDen = Integer.parseInt(tuyenDuongArray[1]);

          NativeQuery<Ve_Xe> query = currentSession
              .createNativeQuery("CALL TIM_KIEM_VE(:idKH, null, :dateInput, :diaDiemDi,:diaDiemDen, :trangThaiInput)", Ve_Xe.class)
              .setParameter("idKH", idKH)
              .setParameter("dateInput", dateInput)
              .setParameter("diaDiemDi", diaDiemDi)
              .setParameter("diaDiemDen", diaDiemDen)
              .setParameter("trangThaiInput", trangThaiInput);
          result = query.list();
        } else if (dateInput.equals("") && !tuyenDuongInput.equals("default") && trangThaiInput.equals("default")) { // x
          // o
          // x
          String[] tuyenDuongArray = tuyenDuongInput.split(",");
          int diaDiemDi = Integer.parseInt(tuyenDuongArray[0]);
          int diaDiemDen = Integer.parseInt(tuyenDuongArray[1]);

          NativeQuery<Ve_Xe> query = currentSession
              .createNativeQuery("CALL TIM_KIEM_VE(:idKH,null, null,:diaDiemDi,:diaDiemDen,:trangThaiInput)", Ve_Xe.class)
              .setParameter("idKH", idKH)
              .setParameter("diaDiemDi", diaDiemDi)
              .setParameter("diaDiemDen", diaDiemDen)
              .setParameter("trangThaiInput", trangThaiInput);
          result = query.list();

        } else if (dateInput.equals("") && !tuyenDuongInput.equals("default")
            && !trangThaiInput.equals("default")) { // x o o
          // tách tuyến đường input ra thành 2 địa điểm đi đến
          String[] tuyenDuongArray = tuyenDuongInput.split(",");
          int diaDiemDi = Integer.parseInt(tuyenDuongArray[0]);
          int diaDiemDen = Integer.parseInt(tuyenDuongArray[1]);

          NativeQuery<Ve_Xe> query = currentSession.createNativeQuery("CALL TIM_KIEM_VE(:idKH, null, null, :diaDiemDi, :diaDiemDen, :trangThaiInput)", Ve_Xe.class)
              .setParameter("idKH", idKH)
              .setParameter("diaDiemDi", diaDiemDi)
              .setParameter("diaDiemDen", diaDiemDen)
              .setParameter("trangThaiInput", trangThaiInput);
          result = query.list();
        } else if (dateInput.equals("") && tuyenDuongInput.equals("default") && !trangThaiInput.equals("default")) { // x
          // x
          // o
          NativeQuery<Ve_Xe> query = currentSession
              .createNativeQuery(
                  "CALL TIM_KIEM_VE(:idKH, null, null, null, null, :trangThaiInput)", Ve_Xe.class)
              .setParameter("idKH", idKH)
              .setParameter("trangThaiInput", trangThaiInput);
          result = query.list();
        } else {
          NativeQuery<Ve_Xe> query = currentSession.createNativeQuery("CALL TIM_KIEM_VE(:idKH, null, null, null, null, null)", Ve_Xe.class)
              .setParameter("idKH", idKH);
          result = query.list();
        }
      }
      currentSession.getTransaction().commit();
    }
    return result;
  }


  @Override
  public List<Ve_Xe> timKiemVeNV(String idVeString, String dateInput, String tuyenDuongInput, String trangThaiInput) {
    try (Session currentSession = sessionFactory.openSession()) {
      currentSession.beginTransaction();
      List<Ve_Xe> result;
      if (!idVeString.isEmpty()) {
        int idVe = Integer.parseInt(idVeString);
        NativeQuery<Ve_Xe> query = currentSession.createNativeQuery("CALL TIM_KIEM_VE_NV (:maVe, null, null, null,null)", Ve_Xe.class)
            .setParameter("maVe", idVe);
        result = query.list();
      } else {
        if (!dateInput.isEmpty() && tuyenDuongInput.equals("default") && trangThaiInput.equals("default")) // o x x
        {
          NativeQuery<Ve_Xe> query = currentSession.createNativeQuery("CALL TIM_KIEM_VE_NV (null, :dateInput, null, null, null)", Ve_Xe.class)
              .setParameter("dateInput", dateInput);
          result = query.list();
          ///////////////////////////////////////// sửa lại không dùng setParameter nữa
        } else if (!dateInput.isEmpty() && !tuyenDuongInput.equals("default")
            && trangThaiInput.equals("default")) { // o o x
          // tách tuyến đường input ra thành 2 địa điểm đi đến
          String[] tuyenDuongArray = tuyenDuongInput.split(",");
          int diaDiemDi = Integer.parseInt(tuyenDuongArray[0]);
          int diaDiemDen = Integer.parseInt(tuyenDuongArray[1]);
          NativeQuery<Ve_Xe> query = currentSession
              .createNativeQuery(
                  "CALL TIM_KIEM_VE_NV (null, :dateInput, :diaDiemDi, :diaDiemDen, null)", Ve_Xe.class)
              .setParameter("dateInput", dateInput)
              .setParameter("diaDiemDi", diaDiemDi)
              .setParameter("diaDiemDen", diaDiemDen);
          result = query.list();
        } else if (!dateInput.isEmpty() && tuyenDuongInput.equals("default")
            && !trangThaiInput.equals("default")) { // o x o
          NativeQuery<Ve_Xe> query = currentSession.createNativeQuery("CALL TIM_KIEM_VE_NV(null, :dateInput, null, null, :trangThaiInput)", Ve_Xe.class)
              .setParameter("dateInput", dateInput)
              .setParameter("trangThaiInput", trangThaiInput);
          result = query.list();
        } else if (!dateInput.isEmpty() && !tuyenDuongInput.equals("default")
            && !trangThaiInput.equals("default")) { // o o o
          // tách tuyến đường input ra thành 2 địa điểm đi đến
          String[] tuyenDuongArray = tuyenDuongInput.split(",");
          int diaDiemDi = Integer.parseInt(tuyenDuongArray[0]);
          int diaDiemDen = Integer.parseInt(tuyenDuongArray[1]);

          NativeQuery<Ve_Xe> query = currentSession
              .createNativeQuery("CALL TIM_KIEM_VE_NV (null, :dateInput, :diaDiemDi, :diaDiemDen, :trangThaiInput)", Ve_Xe.class)
              .setParameter("dateInput", dateInput)
              .setParameter("diaDiemDi", diaDiemDi)
              .setParameter("diaDiemDen", diaDiemDen)
              .setParameter("trangThaiInput", trangThaiInput);
          result = query.list();
        } else if (dateInput.isEmpty() && !tuyenDuongInput.equals("default") && trangThaiInput.equals("default")) { // x
          // o
          // x
          String[] tuyenDuongArray = tuyenDuongInput.split(",");
          int diaDiemDi = Integer.parseInt(tuyenDuongArray[0]);
          int diaDiemDen = Integer.parseInt(tuyenDuongArray[1]);

          NativeQuery<Ve_Xe> query = currentSession
              .createNativeQuery("CALL TIM_KIEM_VE_NV (null, null, :diaDiemDi, :diaDiemDen, null)", Ve_Xe.class)
              .setParameter("diaDiemDi", diaDiemDi)
              .setParameter("diaDiemDen", diaDiemDen);
          result = query.list();

        } else if (dateInput.isEmpty() && !tuyenDuongInput.equals("default")
            && !trangThaiInput.equals("default")) { // x o o
          // tách tuyến đường input ra thành 2 địa điểm đi đến
          String[] tuyenDuongArray = tuyenDuongInput.split(",");
          int diaDiemDi = Integer.parseInt(tuyenDuongArray[0]);
          int diaDiemDen = Integer.parseInt(tuyenDuongArray[1]);

          NativeQuery<Ve_Xe> query = currentSession.createNativeQuery("CALL TIM_KIEM_VE_NV(null, null, :diaDiemDi, :diaDiemDen, :trangThaiInput)", Ve_Xe.class)
              .setParameter("diaDiemDi", diaDiemDi)
              .setParameter("diaDiemDen", diaDiemDen)
              .setParameter("trangThaiInput", trangThaiInput);
          result = query.list();
        } else if (dateInput.isEmpty() && tuyenDuongInput.equals("default") && !trangThaiInput.equals("default")) { // x
          // x
          // o
          NativeQuery<Ve_Xe> query = currentSession
              .createNativeQuery(
                  "CALL TIM_KIEM_VE_NV(null, null, null, null, :trangThaiInput)", Ve_Xe.class)
              .setParameter("trangThaiInput", trangThaiInput);
          result = query.list();
        } else {
          NativeQuery<Ve_Xe> query = currentSession.createNativeQuery("CALL TIM_KIEM_VE_NV(null, null, null, null, null)", Ve_Xe.class);
          result = query.list();
        }
      }
      currentSession.getTransaction().commit();
      return result;
    }
  }
}