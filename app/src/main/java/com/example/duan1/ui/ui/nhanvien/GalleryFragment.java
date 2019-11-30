package com.example.duan1.ui.ui.nhanvien;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
AppDatabase appDatabase;
GalleryFragment galleryFragment;
    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        lvListNhanVien=root.findViewById(R.id.lvListNhanVien);
        fabAddNhanVien=root.findViewById(R.id.fabAddNhanVien);
        appDatabase = Room.databaseBuilder(getContext(),AppDatabase.class,"duan1.db").allowMainThreadQueries().build();
        employeeList=appDatabase.employeeDAO().getAllNhanVien();
        nhanVienAdapter=new NhanVienAdapter(employeeList,getContext());
        lvListNhanVien.setAdapter(nhanVienAdapter);
        fabAddNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),TuyenNhanVienActivity.class));
            }

        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        nhanVienAdapter.notifyDataSetChanged();
        employeeList.clear();
        appDatabase = Room.databaseBuilder(getContext(),AppDatabase.class,"duan1.db").allowMainThreadQueries().build();
        employeeList=appDatabase.employeeDAO().getAllNhanVien();
        nhanVienAdapter.onDataSetChange(employeeList);
    }



}