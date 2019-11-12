package com.example.duan1.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.duan1.dao.EmployeeDAO;
import com.example.duan1.model.Employee;

@Database(entities = {Employee.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EmployeeDAO employeeDAO();
}
