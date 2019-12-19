package com.example.duan1.ui.ui.monan;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.duan1.R;
import com.example.duan1.dao.MyOnItemClickListener;
import com.example.duan1.database.AppDatabase;
import com.example.duan1.model.Employee;
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
                startActivity(new Intent(getContext(),ThemMonAnActivity.class));
            }
        });

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3,RecyclerView.VERTICAL,false);
        rvListMonAn.setLayoutManager(gridLayoutManager);
        rvListMonAn.setAdapter(monAnAdapter);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        monAnAdapter.notifyDataSetChanged();
        monAnList.clear();
        appDatabase = Room.databaseBuilder(getContext(),AppDatabase.class,"duan1.db").allowMainThreadQueries().build();
        monAnList=appDatabase.monAnDAO().getAllMonAn();
        monAnAdapter.onDataSetChange(monAnList);
    }

}