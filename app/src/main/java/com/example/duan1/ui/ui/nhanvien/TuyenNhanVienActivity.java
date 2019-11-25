package com.example.duan1.ui.ui.nhanvien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.database.AppDatabase;
import com.example.duan1.model.Employee;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


public class TuyenNhanVienActivity extends AppCompatActivity {
    TextInputEditText tietID,tietName,tietDiaChi,tietSoDienThoai,tiedSoCMND, tietLuong;
    Button btnTuyenNhanVien;
    AppDatabase appDatabase;
    NhanVienAdapter nhanVienAdapter;
    List<Employee>employeeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuyen_nhan_vien);
        nhanVienAdapter=new NhanVienAdapter(employeeList,TuyenNhanVienActivity.this);
        appDatabase= Room.databaseBuilder(TuyenNhanVienActivity.this,AppDatabase.class,"duan1.db").allowMainThreadQueries().build();
        tietID=findViewById(R.id.tietID);
        tiedSoCMND=findViewById(R.id.tietSoCMND);
        tietDiaChi=findViewById(R.id.tietDiaChi);
        tietName=findViewById(R.id.tietName);
        tietSoDienThoai=findViewById(R.id.tietsoDienThoai);
        tietLuong=findViewById(R.id.tietLuong);
        btnTuyenNhanVien=findViewById(R.id.btnTuyenNhanVien);
        btnTuyenNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tietID.getText().toString().isEmpty() || tietName.getText().toString().isEmpty() || tietSoDienThoai.getText().toString().isEmpty() || tiedSoCMND.getText().toString().isEmpty() || tietDiaChi.getText().toString().isEmpty()) {
                    Toast.makeText(TuyenNhanVienActivity.this, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                } else {
                    Employee employee = new Employee();
                    employee.hoVaTen = tietName.getText().toString();
                    employee.id = tietID.getText().toString();
                    employee.soDienThoai = tietSoDienThoai.getText().toString();
                    employee.diaChi = tietDiaChi.getText().toString();
                    employee.soCMND = Integer.parseInt(tiedSoCMND.getText().toString());
                    employee.luong=Integer.parseInt(tietLuong.getText().toString());
                    long[] result = appDatabase.employeeDAO().insertNhanVien(employee);
                    nhanVienAdapter.notifyDataSetChanged();
                    if (result != null) {
                        Toast.makeText(getApplicationContext(), "Chúc mừng bạn vừa tuyển nhân viên mới", Toast.LENGTH_SHORT).show();
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), "Không thể tuyển người này", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}



