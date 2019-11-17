package com.example.duan1.ui.ui.monan;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.duan1.R;
import com.example.duan1.database.AppDatabase;
import com.example.duan1.model.MonAn;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class SlideshowFragment extends Fragment {
    RecyclerView rvListMonAn;
    FloatingActionButton fabAddMonAn;
    List<MonAn> monAnList;
    AppDatabase appDatabase;
    MonAnAdapter monAnAdapter;

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        rvListMonAn=root.findViewById(R.id.rvListMonAn);
        fabAddMonAn=root.findViewById(R.id.fabAddMonAn);
        appDatabase= Room.databaseBuilder(getContext(),AppDatabase.class,"duan1.db").allowMainThreadQueries().build();
        monAnList=appDatabase.monAnDAO().getAllMonAn();

        monAnAdapter=new MonAnAdapter(monAnList,getContext());
        rvListMonAn.setHasFixedSize(true);
        fabAddMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog alertDialog=new AlertDialog.Builder(getContext()).create();
                View alert =LayoutInflater.from(getContext()).inflate(R.layout.add_monan,null);
                alertDialog.setView(alert);
                final EditText edIDMonAn,edTenMonAn,edGiaMonAn;
                Button btnThemMonAn;
                edIDMonAn=alert.findViewById(R.id.edIDMonAn);
                edTenMonAn=alert.findViewById(R.id.edTenMonAn);
                edGiaMonAn=alert.findViewById(R.id.edGiaMonAn);
                btnThemMonAn=alert.findViewById(R.id.btnThemMonAn);
                btnThemMonAn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MonAn monAn=new MonAn();
                        monAn.IDMonAn=edIDMonAn.getText().toString();
                        monAn.tenMonAn=edTenMonAn.getText().toString();
                        monAn.giaMonAn=Integer.parseInt(edGiaMonAn.getText().toString());
                        long[] result = appDatabase.monAnDAO().insertMonAn(monAn);
                        if (result != null) {
                            Toast.makeText(getContext(), "Bạn thêm món ăn mới thành công", Toast.LENGTH_SHORT).show();
                            monAnAdapter.notifyDataSetChanged();
                            alertDialog.dismiss();
                        } else {
                            Toast.makeText(getContext(), "Món ăn không hợp lệ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alertDialog.show();
            }
        });
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3,RecyclerView.VERTICAL,false);
        rvListMonAn.setLayoutManager(gridLayoutManager);
        rvListMonAn.setAdapter(monAnAdapter);
        return root;
    }
}