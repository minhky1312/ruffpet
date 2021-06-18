package com.example.myapplication.model;

public class loaisp {
    public int id;
    public String tenLoaisp;
    public String hinhAnhsp;

    public loaisp(int id, String tenLoaisp, String hinhAnhsp) {
        this.id = id;
        this.tenLoaisp = tenLoaisp;
        this.hinhAnhsp = hinhAnhsp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoaisp() {
        return tenLoaisp;
    }

    public void setTenLoaisp(String tenLoaisp) {
        this.tenLoaisp = tenLoaisp;
    }

    public String getHinhAnhsp() {
        return hinhAnhsp;
    }

    public void setHinhAnhsp(String hinhAnhsp) {
        this.hinhAnhsp = hinhAnhsp;
    }
}
