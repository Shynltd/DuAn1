package com.example.duan1.ui.ui.monan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.database.AppDatabase;
import com.example.duan1.model.Employee;
import com.example.duan1.model.MonAn;
import com.example.duan1.ui.ui.nhanvien.NhanVienAdapter;

import java.util.List;

public class ThemMonAnActivity extends AppCompatActivity {
    EditText edIDMonAn,edTenMonAn,edGiaMonAn;
    Button btnThemMonAn;
    AppDatabase appDatabase;
    MonAnAdapter monAnAdapter;
    List<MonAn> monAnList;
    NhanVienAdapter nhanVienAdapter;
    List<Employee>employeeList;
    Spinner spnNV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mon_an);
        edIDMonAn=findViewById(R.id.edIDMonAn);
        spnNV=findViewById(R.id.spnNV);

        monAnAdapter=new MonAnAdapter(monAnList,ThemMonAnActivity.this);
        edTenMonAn=findViewById(R.id.edTenMonAn);

        edGiaMonAn=findViewById(R.id.edGiaMonAn);
        btnThemMonAn=findViewById(R.id.btnThemMonAn);
        appDatabase= Room.databaseBuilder(ThemMonAnActivity.this,AppDatabase.class,"duan1.db").allowMainThreadQueries().build();
        employeeList = appDatabase.employeeDAO().getAllNhanVien();
        nhanVienAdapter=new NhanVienAdapter(employeeList,this);
        spnNV.setAdapter(nhanVienAdapter);
        btnThemMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonAn monAn=new MonAn();
                monAn.IDMonAn=edIDMonAn.getText().toString();
                monAn.tenMonAn=edTenMonAn.getText().toString();
                monAn.giaMonAn=Integer.parseInt(edGiaMonAn.getText().toString());
                long[] result = appDatabase.monAnDAO().insertMonAn(monAn);
                if (result != null) {
                    Toast.makeText(getApplicationContext(), "Bạn thêm món ăn mới thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Món ăn không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
