package com.example.duan1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.duan1.model.HoaDon;

import java.util.List;

@Dao
public interface HoaDonDAO {
    @Query("SELECT * FROM hoadon")
    List<HoaDon> getAllHoaDon();

    @Insert
    long[] insertHoaDon(HoaDon... hoaDons);

    @Update
    void updateHoaDon(HoaDon hoaDon);

    @Delete
    void deleteHoaDon(HoaDon hoaDon);
}
