package com.example.duan1.ui.ui.nhanvien;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.example.duan1.R;
import com.example.duan1.database.AppDatabase;
import com.example.duan1.model.Employee;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class GalleryFragment extends Fragment {
ListView lvListNhanVien;
NhanVienAdapter nhanVienAdapter;
List<Employee> employeeList;
FloatingActionButton fabAddNhanVien;
    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        lvListNhanVien=root.findViewById(R.id.lvListNhanVien);
        fabAddNhanVien=root.findViewById(R.id.fabAddNhanVien);
        final AppDatabase db= Room.databaseBuilder(getContext(),AppDatabase.class,"duan1.db").allowMainThreadQueries().build();

        employeeList=db.employeeDAO().getAll();
        nhanVienAdapter=new NhanVienAdapter(employeeList,getContext());
        lvListNhanVien.setAdapter(nhanVienAdapter);
        fabAddNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                final View alert = LayoutInflater.from(getContext()).inflate(R.layout.add_nhanvien,null);
                alertDialog.setView(alert);
                final TextInputEditText tietID,tietName,tietDiaChi,tietSoDienThoai,tiedSoCMND;
                Button btnTuyenNhanVien;
                tietID=alert.findViewById(R.id.tietID);
                tiedSoCMND=alert.findViewById(R.id.tietSoCMND);
                tietDiaChi=alert.findViewById(R.id.tietDiaChi);
                tietName=alert.findViewById(R.id.tietName);
                tietSoDienThoai=alert.findViewById(R.id.tietsoDienThoai);
                btnTuyenNhanVien=alert.findViewById(R.id.btnTuyenNhanVien);
                btnTuyenNhanVien.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tietID.getText().toString().isEmpty()||tietName.getText().toString().isEmpty()||tietSoDienThoai.getText().toString().isEmpty()||tiedSoCMND.getText().toString().isEmpty()||tietDiaChi.getText().toString().isEmpty()){
                            Toast.makeText(getContext(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                        } else {
                            Employee employee=new Employee();
                            employee.hoVaTen=tietName.getText().toString();
                            employee.id=tietID.getText().toString();
                            employee.soDienThoai= tietSoDienThoai.getText().toString();
                            employee.diaChi=tietDiaChi.getText().toString();
                            employee.soCMND= Integer.parseInt(tiedSoCMND.getText().toString());
                            long[] result= db.employeeDAO().insert(employee);
                            if (result != null) {
                                Toast.makeText(getContext(), "Chúc mừng bạn vừa tuyển nhân viên mới", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                                onResume();
                            } else {
                                Toast.makeText(getContext(), "Không thể tuyển người này", Toast.LENGTH_SHORT).show();
                            }
                        }



                    }
                });
                alertDialog.show();
            }

        });



        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}