package com.example.duan1.ui.ui.nhanvien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.room.Room;

import com.example.duan1.R;
import com.example.duan1.database.AppDatabase;
import com.example.duan1.model.Employee;

import java.util.List;

public class NhanVienAdapter extends BaseAdapter {
    List<Employee> employeeList;
    Context context;
    private AppDatabase appDatabase;

    public NhanVienAdapter(List<Employee> employeeList, Context context) {
        this.employeeList = employeeList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return employeeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NhanVienHolder nhanVienHolder=null;
        if (convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_nhanvien, parent, false);
            nhanVienHolder=new NhanVienHolder();
            nhanVienHolder.imgNhanVien=convertView.findViewById(R.id.imgNhanVien);
            nhanVienHolder.imgDuoiNhanVien=convertView.findViewById(R.id.imgDuoiNhanvien);
            nhanVienHolder.imgPhoneNhanVien=convertView.findViewById(R.id.imgPhoneNhanVien);
            nhanVienHolder.imgSuaNhanVien=convertView.findViewById(R.id.imgSuaNhanVien);
            nhanVienHolder.tvNameNhanVien=convertView.findViewById(R.id.tvNameNhanVien);
            nhanVienHolder.tvPhoneNhanVien=convertView.findViewById(R.id.tvPhoneNhanhVien);
            convertView.setTag(nhanVienHolder);
        } else {
            nhanVienHolder= (NhanVienHolder) convertView.getTag();
        }
        appDatabase= Room.databaseBuilder(context,AppDatabase.class,"duan1.db").allowMainThreadQueries().build();
        Employee employee=employeeList.get(position);
        nhanVienHolder.tvPhoneNhanVien.setText(employeeList.get(position).soDienThoai);
        nhanVienHolder.tvNameNhanVien.setText(employeeList.get(position).hoVaTen);
        nhanVienHolder.imgDuoiNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        nhanVienHolder.imgSuaNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }

    public class NhanVienHolder{
         ImageView imgNhanVien,imgPhoneNhanVien,imgDuoiNhanVien,imgSuaNhanVien;
         TextView tvNameNhanVien,tvPhoneNhanVien;
    }
}
