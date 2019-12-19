package com.example.duan1.ui.ui.hoadon;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.duan1.R;
import com.example.duan1.dao.MyOnItemClickListener;
import com.example.duan1.database.AppDatabase;
import com.example.duan1.model.HoaDon;
import com.example.duan1.ui.ui.hoadon.adapter.HoaDonAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ToolsFragment extends Fragment {
    FloatingActionButton fabAddHoaDon;
    RecyclerView rvListHoaDon;
    HoaDonAdapter hoaDonAdapter;
    List<HoaDon> hoaDonList;
    AppDatabase appDatabase;
    private ToolsViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        fabAddHoaDon=root.findViewById(R.id.fabAddHoaDon);
        rvListHoaDon=root.findViewById(R.id.rvListHoaDon);
        appDatabase= Room.databaseBuilder(getContext(),AppDatabase.class,"duan1.db").allowMainThreadQueries().build();
        hoaDonList=appDatabase.hoaDonDAO().getAllHoaDon();
        hoaDonAdapter=new HoaDonAdapter(hoaDonList,getContext());
        rvListHoaDon.setHasFixedSize(true);
        fabAddHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),ThemHoaDonActivity.class));
            }
        });
        hoaDonAdapter.setMyOnItemClickListener(new MyOnItemClickListener() {
            @Override
            public void onClick(HoaDon hoaDon) {
                AlertDialog alertDialog=new AlertDialog.Builder(getContext()).create();
                View view=LayoutInflater.from(getContext()).inflate(R.layout.dialog_hoa_don,null);
                alertDialog.setView(view);
                TextView tvNgayMua,tvNguoiBan,tvMaHoaDon,tvMonAn,tvSoLuong,tvTongTien;
                tvNgayMua=view.findViewById(R.id.tvNgayMua);
                tvNguoiBan=view.findViewById(R.id.tvNguoiBan);
                tvSoLuong=view.findViewById(R.id.tvSoLuong);
                tvTongTien=view.findViewById(R.id.tvTongTien);
                tvMaHoaDon=view.findViewById(R.id.tvMaHoaDon);
                tvMonAn=view.findViewById(R.id.tvMonAn);
                tvNgayMua.setText(hoaDon.ngayMua);
                tvMaHoaDon.setText(hoaDon.maHoaDon);
                tvNguoiBan.setText(hoaDon.nhanVienBanHang);
                tvMonAn.setText(hoaDon.monHang);
                tvSoLuong.setText("Số lượng: "+hoaDon.soLuong);
                tvTongTien.setText("Tổng tiền: "+hoaDon.tongTien+"đ");
                alertDialog.show();
            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvListHoaDon.setLayoutManager(linearLayoutManager);
        rvListHoaDon.setAdapter(hoaDonAdapter);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        hoaDonAdapter.notifyDataSetChanged();
        hoaDonList.clear();
        appDatabase = Room.databaseBuilder(getContext(),AppDatabase.class,"duan1.db").allowMainThreadQueries().build();
        hoaDonList=appDatabase.hoaDonDAO().getAllHoaDon();
        hoaDonAdapter.onDataSetChange(hoaDonList);
    }
}