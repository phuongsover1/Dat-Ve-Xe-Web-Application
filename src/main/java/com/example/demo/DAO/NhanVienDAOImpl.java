package com.example.demo.DAO;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Nhan_Vien;

@Repository
public class NhanVienDAOImpl implements NhanVienDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Nhan_Vien getNhanVienFromUsername(String username) {
        List<Nhan_Vien> nvList;
        try (Session currentSession = sessionFactory.openSession()) {
            currentSession.beginTransaction();
            String sHQL = "FROM Nhan_Vien NV WHERE NV.soDienThoai = :username";
            Query<Nhan_Vien> query = currentSession.createQuery(sHQL, Nhan_Vien.class);
            query.setParameter("username", username);
            nvList = query.getResultList();
           currentSession.getTransaction().commit();
        }
        if (nvList.isEmpty())
            return null;
        return nvList.get(0);
    }

    @Override
    public Nhan_Vien getNhanVienFromId(int idNhanVien) {
        Nhan_Vien tempNV;
        try (Session currentSession = sessionFactory.openSession()) {
            currentSession.beginTransaction();
            tempNV = currentSession.get(Nhan_Vien.class, idNhanVien);
            currentSession.getTransaction().commit();
        }
        return tempNV;
    }

    @Override
    public void updateNV(Nhan_Vien nhanVien) {
        try (Session currentSession = sessionFactory.openSession()) {
            currentSession.beginTransaction();
            currentSession.update(nhanVien);
           currentSession.getTransaction().commit();
        }
    }

    @Override
    public List<Nhan_Vien> getListNV() {
        // TODO Auto-generated method stub
        List<Nhan_Vien> nvList;
        try (Session currentSession = sessionFactory.openSession()) {
           currentSession.beginTransaction();
            // Không nghỉ việc vào role không phải là admin
            String hSQL = "FROM Nhan_Vien NV WHERE NV.daNghiViec=0 AND NOT NV.idTaiKhoan.idRole.authority='ROLE_MANAGER'";
            Query<Nhan_Vien> query = currentSession.createQuery(hSQL, Nhan_Vien.class);
            nvList = query.getResultList();
            currentSession.getTransaction().commit();
        }
        return nvList;
    }

    @Override
    public void xoaNV(int nvID) {
        // TODO Auto-generated method stub
        try (Session currentSession = sessionFactory.openSession()) {
           currentSession.beginTransaction();
            Nhan_Vien deleteNV = currentSession.get(Nhan_Vien.class, nvID);
            // Xử lý thêm phần account, với lại đánh dấu xóa chứ không xóa nv tại vì ràng
            // buộc khóa ngoai nếu nhân viên đó đã từng thanh toán vé,
            // nếu không có thanh toán vé nào thì xóa bình thường
            if (!deleteNV.getVeXeList().isEmpty()) {
                deleteNV.setDaNghiViec(1);
                // Lấy account để vô hiệu hóa
                deleteNV.getIdTaiKhoan().setAccountState(0);
                ;
                currentSession.update(deleteNV);
            } else {
                currentSession.delete(deleteNV.getIdTaiKhoan().getIdRole());
                currentSession.delete(deleteNV.getIdTaiKhoan());
                currentSession.delete(deleteNV);
            }
            currentSession.getTransaction().commit();
        }

    }

    @Override
    public void themNV(Nhan_Vien nhanVien) {
        // TODO Auto-generated method stub
        try (Session currentSession = sessionFactory.openSession()) {
            currentSession.beginTransaction();
            currentSession.persist(nhanVien);
            currentSession.getTransaction().commit();
        }
    }

}
