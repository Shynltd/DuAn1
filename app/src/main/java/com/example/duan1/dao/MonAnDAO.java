package com.example.duan1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.duan1.model.MonAn;

import java.util.List;
@Dao
public interface MonAnDAO {
    @Query("SELECT * FROM monan")
    List<MonAn> getAllMonAn();

    @Insert
    long[] insertMonAn(MonAn... monAns);

    @Delete
    void deleteMonAn(MonAn monAn);

    @Update
    void updateMonAn(MonAn monAn);

}
