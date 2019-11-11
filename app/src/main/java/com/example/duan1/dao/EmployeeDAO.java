package com.example.duan1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.duan1.model.Employee;

import java.util.List;

@Dao
public interface EmployeeDAO {
@Query("SELECT * FROM employee")
    List<Employee> getAll();
@Insert
    long[] insert(Employee...employees);
@Delete
    void delete(Employee employee);
@Update
    void update(Employee employee);

}
