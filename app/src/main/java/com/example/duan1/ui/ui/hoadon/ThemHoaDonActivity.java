package com.example.duan1.ui.ui.hoadon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.database.AppDatabase;
import com.example.duan1.model.Employee;
import com.example.duan1.model.HoaDon;
import com.example.duan1.model.MonAn;
import com.example.duan1.ui.ui.hoadon.adapter.SpnMonAnAdapter;
import com.example.duan1.ui.ui.hoadon.adapter.SpnNvBanHangAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ThemHoaDonActivity extends AppCompatActivity {
    EditText edMaHoaDon;
    TextView tvNgayMua,tvSoLuong;
    Spinner spnNvBanHang, spnMonAn;
    Button btnThemHoaDon,btnGiam,btnTang;
    List<MonAn> monAnList;
    List<Employee> employeeList;
    List<HoaDon> hoaDonList;
    SpnMonAnAdapter spnMonAnAdapter;
    SpnNvBanHangAdapter spnNvBanHangAdapter;
    AppDatabase appDatabase;
    int sl=0;
    int tongTien=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hoa_don);
        setTitle("Thêm Hóa Đơn");
        init();
        iconBack();
        Calendar calendar = Calendar.getInstance();
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "duan1.db").allowMainThreadQueries().build();
        monAnList = appDatabase.monAnDAO().getAllMonAn();
        employeeList = appDatabase.employeeDAO().getAllNhanVien();
        hoaDonList = appDatabase.hoaDonDAO().getAllHoaDon();
        spnMonAnAdapter = new SpnMonAnAdapter(monAnList, this);
        spnNvBanHangAdapter = new SpnNvBanHangAdapter(employeeList, this);
        spnMonAn.setAdapter(spnMonAnAdapter);
        spnNvBanHang.setAdapter(spnNvBanHangAdapter);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tvNgayMua.setText(simpleDateFormat.format(calendar.getTime()));

        tvSoLuong.setText(sl+"");
        btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sl++;
                tvSoLuong.setText(sl+"");
            }
        });
        btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sl<=0){
                    sl=0;
                }else {
                    sl-=1;
                    tvSoLuong.setText(sl+"");
                }
            }
        });
        btnThemHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog alertDialog=new AlertDialog.Builder(ThemHoaDonActivity.this).create();
                v= LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_thanh_toan,null);
                alertDialog.setView(v);
                TextView tvMaHoaDon1,tvTongTien1,tvNgayMua1,tvNguoiBanHang1,tvNguoiMuaHang1,tvSoLuong1,tvMonAn1;
                Button btnHuy1,btnThanhToan1;
                tvMaHoaDon1=v.findViewById(R.id.tvMaHoaDon);
                tvTongTien1=v.findViewById(R.id.tvTongTien);
                tvNgayMua1=v.findViewById(R.id.tvNgayMua);
                tvNguoiBanHang1=v.findViewById(R.id.tvNguoiBan);
                tvNguoiMuaHang1=v.findViewById(R.id.tvNguoiMuaHang);
                tvSoLuong1=v.findViewById(R.id.tvSoLuong);
                tvMonAn1=v.findViewById(R.id.tvMonAn);
                btnHuy1=v.findViewById(R.id.btnHuy);
                tvNgayMua1.setText(tvNgayMua.getText().toString());
                btnThanhToan1=v.findViewById(R.id.btnThanhToan);
                Employee employee= (Employee) spnNvBanHang.getSelectedItem();
                tvNguoiMuaHang1.setText("");
                tvNguoiBanHang1.setText(employee.hoVaTen);
                tvMaHoaDon1.setText(edMaHoaDon.getText().toString());
                MonAn monAn= (MonAn) spnMonAn.getSelectedItem();
                tvSoLuong1.setText(sl+"");
                tvMonAn1.setText(monAn.tenMonAn);
                tongTien=monAn.giaMonAn*sl;
                tvTongTien1.setText("Tổng tiền: "+tongTien+"đ");

                btnHuy1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                btnThanhToan1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        themHoaDon();
                    }
                });
                alertDialog.show();
//                themHoaDon();
            }
        });
    }

    private void themHoaDon() {
        if (edMaHoaDon.getText().toString().isEmpty()){
            Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
        } else {
            HoaDon hoaDon = new HoaDon();
            hoaDon.maHoaDon = edMaHoaDon.getText().toString();
            hoaDon.ngayMua = tvNgayMua.getText().toString();
            MonAn monAn = (MonAn) spnMonAn.getSelectedItem();
            Employee employee = (Employee) spnNvBanHang.getSelectedItem();
            hoaDon.monHang = monAn.tenMonAn;
            hoaDon.nhanVienBanHang = employee.hoVaTen;
            hoaDon.soLuong = sl;
            hoaDon.tongTien = tongTien;
            long[] result = appDatabase.hoaDonDAO().insertHoaDon(hoaDon);
            if (result != null) {
                Toast.makeText(ThemHoaDonActivity.this, "Thêm hóa đơn thành công", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(ThemHoaDonActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void init() {
        btnGiam=findViewById(R.id.btnGiam);
        btnTang=findViewById(R.id.btnTang);
        tvSoLuong = findViewById(R.id.tvSoLuong);
        edMaHoaDon = findViewById(R.id.edMaHoaDon);
        tvNgayMua = findViewById(R.id.tvNgayMua);
        spnMonAn = findViewById(R.id.spnMonAn);
        spnNvBanHang = findViewById(R.id.spnNvBanHang);
        btnThemHoaDon = findViewById(R.id.btnThemHoaDon);
    }

    @Override
    protected void onResume() {
        Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Toast.makeText(this, "Restart", Toast.LENGTH_SHORT).show();
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onStart() {
        Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show();
        super.onStart();
    }
    private void iconBack() {
        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getResources().getDrawable(R.drawable.icon_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

    }

    //    public void btnThemHoaDon(View view) {
////        if (edMaHoaDon.getText().toString().isEmpty()) {
////            Toast.makeText(ThemHoaDonActivity.this, "Vui lòng nhập mã hóa đơn", Toast.LENGTH_SHORT).show();
////        } else {
//
//
//            if (hoaDonList.size() > 0) {
//
//                for (int i = 0; i < hoaDonList.size(); i++) {
//                    if (edMaHoaDon.getText().toString().equals(hoaDonList.get(i).maHoaDon)) {
//                        Toast.makeText(ThemHoaDonActivity.this, "ID đã trùng", Toast.LENGTH_SHORT).show();
//                        break;
//                    } else {
//                        themHoaDon();
//                    }
//                }
//
//            } else {
//                themHoaDon();
//            }
//        }
}

//}
