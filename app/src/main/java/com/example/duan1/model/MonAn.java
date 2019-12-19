package com.example.duan1.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MonAn {
    @PrimaryKey
    @NonNull
    public String IDMonAn;
    @ColumnInfo(name = "tenMonAn")
    public String tenMonAn;
    @ColumnInfo(name = "giaMonAn")
    public int giaMonAn;

}
