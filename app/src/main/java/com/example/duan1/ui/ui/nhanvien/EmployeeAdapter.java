package com.example.duan1.ui.ui.nhanvien;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.duan1.R;
import com.example.duan1.dao.MyOnItemClickListener;
import com.example.duan1.database.AppDatabase;
import com.example.duan1.model.Employee;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.holder> {
    List<Employee> employeeList;
    Context context;
    AppDatabase appDatabase;
    MyOnItemClickListener myOnItemClickListener;

    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }

    public EmployeeAdapter(List<Employee> employeeList, Context context) {
        this.employeeList = employeeList;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lv_nhanvien, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, final int position) {
        appDatabase= Room.databaseBuilder(context, AppDatabase.class,"duan1.db").allowMainThreadQueries().build();
        final Employee employee=employeeList.get(position);

        holder.tvPhoneNhanVien.setText(employeeList.get(position).soDienThoai+"");
        holder.tvNameNhanVien.setText(employeeList.get(position).hoVaTen);
        holder.imgDuoiNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Bạn đã đuổi việc "+employeeList.get(position).hoVaTen, Toast.LENGTH_SHORT).show();
                        employeeList.remove(employee);
                        appDatabase.employeeDAO().deleteNhanVien(employee);
                        notifyDataSetChanged();
                    }
                }).setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setMessage("Bạn có muốn đuổi việc "+employeeList.get(position).hoVaTen+" không ?");
                builder.create().show();
            }
        });
        holder.imgSuaNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog alertDialog=new AlertDialog.Builder(context).create();
                final View alert =LayoutInflater.from(context).inflate(R.layout.update_nhanvien,null);
                alertDialog.setView(alert);
                final TextInputEditText tietID,tietName,tietDiaChi,tietSoDienThoai,tiedSoCMND,tietLuong;
                Button btnSuaNhanVien;
                tietID=alert.findViewById(R.id.tietID);
                tiedSoCMND=alert.findViewById(R.id.tietSoCMND);
                tietLuong=alert.findViewById(R.id.tietLuong);
                tietDiaChi=alert.findViewById(R.id.tietDiaChi);
                tietName=alert.findViewById(R.id.tietName);
                tietSoDienThoai=alert.findViewById(R.id.tietsoDienThoai);
                btnSuaNhanVien=alert.findViewById(R.id.btnSuaNhanVien);
                tietID.setText(employeeList.get(position).id);
                tietID.setEnabled(false);
                tiedSoCMND.setText(employeeList.get(position).soCMND+"");
                tietLuong.setText(employeeList.get(position).luong+"");
                tiedSoCMND.setEnabled(false);
                tietDiaChi.setText(employeeList.get(position).diaChi);
                tietName.setText(employeeList.get(position).hoVaTen);
                tietSoDienThoai.setText(employeeList.get(position).soDienThoai);
                btnSuaNhanVien.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        employee.hoVaTen=tietName.getText().toString();
                        employee.soDienThoai=tietSoDienThoai.getText().toString();
                        employee.diaChi=tietDiaChi.getText().toString();
                        employee.luong=Integer.parseInt(tietLuong.getText().toString()+"");
                        appDatabase.employeeDAO().updateNhanVien(employee);
                        Toast.makeText(context, "Bạn đã sửa thông tin nhân viên thành công", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                        notifyDataSetChanged();

                    }
                });
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class holder extends RecyclerView.ViewHolder {
        ImageView imgNhanVien, imgPhoneNhanVien, imgDuoiNhanVien, imgSuaNhanVien;
        TextView tvNameNhanVien, tvPhoneNhanVien;

        public holder(@NonNull View itemView) {
            super(itemView);
            imgNhanVien=itemView.findViewById(R.id.imgNhanVien);
            imgDuoiNhanVien=itemView.findViewById(R.id.imgDuoiNhanvien);
            imgPhoneNhanVien=itemView.findViewById(R.id.imgPhoneNhanVien);
            imgSuaNhanVien=itemView.findViewById(R.id.imgSuaNhanVien);
            tvNameNhanVien=itemView.findViewById(R.id.tvNameNhanVien);
            tvPhoneNhanVien=itemView.findViewById(R.id.tvPhoneNhanhVien);
        }
    }

    public void notifyDataSetChange() {
        super.notifyDataSetChanged();
    }

    public void onDataSetChange(List<Employee> employeeList) {
        this.employeeList = employeeList;
        notifyDataSetChange();
    }
}
