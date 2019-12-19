package com.example.duan1.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Employee {
    @PrimaryKey
    @NonNull
    public String id;
    @ColumnInfo(name = "hoVaTen")
    public String hoVaTen;
    @ColumnInfo(name = "soDienThoai")
    public String soDienThoai;
    @ColumnInfo(name = "soCMND")
    public int soCMND;
    @ColumnInfo(name = "luong")
    public int luong;
    @ColumnInfo(name = "diaChi")
    public String diaChi;

}
