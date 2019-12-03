package com.example.duan1.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity
public class HoaDon {
    @PrimaryKey @NonNull
    public String maHoaDon;
    @ColumnInfo(name = "ngayMua")
    public String ngayMua;
    @ColumnInfo(name = "nhanVienBanHang")
    public String nhanVienBanHang;
    @ColumnInfo(name = "nguoiMua")
    public String nguoiMua;
    @ColumnInfo(name = "monHang")
    public String monHang;

}
