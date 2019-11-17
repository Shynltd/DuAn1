package com.example.duan1.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.duan1.dao.EmployeeDAO;
import com.example.duan1.dao.MonAnDAO;
import com.example.duan1.model.Employee;
import com.example.duan1.model.MonAn;

@Database(entities = {Employee.class, MonAn.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EmployeeDAO employeeDAO();
    public abstract MonAnDAO monAnDAO();
}
