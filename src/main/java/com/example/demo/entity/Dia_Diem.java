package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Dia_Diem")
public class Dia_Diem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dia_diem")
    private int idDiaDiem;

    @Column(name = "ten_dia_diem")
    private String tenDiaDiem;

    @OneToMany(mappedBy = "diaDiemDi")
    private List<Tuyen_Xe> tuyenXeDiList;

    @OneToMany(mappedBy = "diaDiemDen")
    private List<Tuyen_Xe> tuyenXeDenList;

    public Dia_Diem() {

    }

    public Dia_Diem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }

    public int getIdDiaDiem() {
        return idDiaDiem;
    }

    public void setIdDiaDiem(int idDiaDiem) {
        this.idDiaDiem = idDiaDiem;
    }

    public String getTenDiaDiem() {
        return tenDiaDiem;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }

    @Override
    public String toString() {
        return "Dia_Diem [idDiaDiem=" + idDiaDiem + ", tenDiaDiem=" + tenDiaDiem + "]";
    }

    public List<Tuyen_Xe> getTuyenXeDiList() {
        return tuyenXeDiList;
    }

    public void setTuyenXeDiList(List<Tuyen_Xe> tuyenXeDiList) {
        this.tuyenXeDiList = tuyenXeDiList;
    }

    public List<Tuyen_Xe> getTuyenXeDenList() {
        return tuyenXeDenList;
    }

    public void setTuyenXeDenList(List<Tuyen_Xe> tuyenXeDenList) {
        this.tuyenXeDenList = tuyenXeDenList;
    }

}
