package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Tuyen_Xe")
public class Tuyen_Xe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tuyen")
    private int idTuyen;

    @ManyToOne
    @JoinColumn(name = "dia_diem_di")
    private Dia_Diem diaDiemDi;

    @ManyToOne
    @JoinColumn(name = "dia_diem_den")
    private Dia_Diem diaDiemDen;

    // Tạo list giữa tất cả các chuyến xe của 1 tuyến xe đó (nghĩa là các chuyến xe
    // cùng tuyến
    // nhưng chạy khác giờ )
    @OneToMany(mappedBy = "maTuyen", fetch = FetchType.EAGER)
    List<Chuyen_Xe> chuyenXeList;

    @Column(name = "dia_diem_len_xe")
    private String diaDiemLenXe;

    @Column(name = "dia_diem_xuong_xe")
    private String diaDiemXuongXe;

    @Column(name = "thoi_gian_ton")
    private int thoiGianTon;

    @Column(name = "so_km")
    private int soKm;

    public Tuyen_Xe() {

    }

    public int getIdTuyen() {
        return idTuyen;
    }

    public void setIdTuyen(int idTuyen) {
        this.idTuyen = idTuyen;
    }

    public Dia_Diem getDiaDiemDi() {
        return diaDiemDi;
    }

    public void setDiaDiemDi(Dia_Diem diaDiemDi) {
        this.diaDiemDi = diaDiemDi;
    }

    public Dia_Diem getDiaDiemDen() {
        return diaDiemDen;
    }

    public void setDiaDiemDen(Dia_Diem diaDiemDen) {
        this.diaDiemDen = diaDiemDen;
    }

    public String getDiaDiemLenXe() {
        return diaDiemLenXe;
    }

    public void setDiaDiemLenXe(String diaDiemLenXe) {
        this.diaDiemLenXe = diaDiemLenXe;
    }

    public String getDiaDiemXuongXe() {
        return diaDiemXuongXe;
    }

    public void setDiaDiemXuongXe(String diaDiemXuongXe) {
        this.diaDiemXuongXe = diaDiemXuongXe;
    }

    public int getThoiGianTon() {
        return thoiGianTon;
    }

    public void setThoiGianTon(int thoiGianTon) {
        this.thoiGianTon = thoiGianTon;
    }

    public int getSoKm() {
        return soKm;
    }

    public void setSoKm(int soKm) {
        this.soKm = soKm;
    }

    public List<Chuyen_Xe> getChuyenXeList() {
        return chuyenXeList;
    }

    public void setChuyenXeList(List<Chuyen_Xe> chuyenXeList) {
        this.chuyenXeList = chuyenXeList;
    }

    @Override
    public String toString() {
        return "Tuyen_Xe [idTuyen=" + idTuyen + ", diaDiemDi=" + diaDiemDi + ", diaDiemDen=" + diaDiemDen
                + ", diaDiemLenXe=" + diaDiemLenXe + ", diaDiemXuongXe=" + diaDiemXuongXe + ", thoiGianTon="
                + thoiGianTon + ", soKm=" + soKm + "]";
    }

}
