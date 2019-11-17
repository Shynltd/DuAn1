package com.example.duan1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.duan1.model.Employee;
import com.example.duan1.model.MonAn;

import java.util.List;

@Dao
public interface EmployeeDAO {
    @Query("SELECT * FROM employee")
    List<Employee> getAllNhanVien();

    @Insert
    long[] insertNhanVien(Employee... employees);

    @Delete
    void deleteNhanVien(Employee employee);

    @Update
    void updateNhanVien(Employee employee);

}
