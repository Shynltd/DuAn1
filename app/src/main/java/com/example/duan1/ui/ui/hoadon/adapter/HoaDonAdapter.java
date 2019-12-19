package com.example.duan1.ui.ui.hoadon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.dao.MyOnItemClickListener;
import com.example.duan1.model.HoaDon;

import java.util.List;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.HoaDonHolder> {
    List<HoaDon> hoaDonList;
    Context context;
    MyOnItemClickListener myOnItemClickListener;

    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }

    public HoaDonAdapter(List<HoaDon> hoaDonList, Context context) {
        this.hoaDonList = hoaDonList;
        this.context = context;
    }



    @NonNull
    @Override
    public HoaDonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.rv_hoadon,parent,false);
        return new HoaDonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HoaDonHolder holder, int position) {
        final HoaDon hoaDon=hoaDonList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnItemClickListener.onClick(hoaDon);
            }
        });

        holder.tvMaHoaDon.setText(hoaDon.maHoaDon);
        holder.tvNgayMua.setText(hoaDon.ngayMua);



    }

    @Override
    public int getItemCount() {
        return hoaDonList.size();
    }

    public static class HoaDonHolder extends RecyclerView.ViewHolder {
        TextView tvMaHoaDon,tvNgayMua;
        ImageView imgHoaDon;
        public HoaDonHolder(@NonNull View itemView) {
            super(itemView);
            tvMaHoaDon=itemView.findViewById(R.id.tvMaHoaDon);
            tvNgayMua=itemView.findViewById(R.id.tvNgayMua);
            imgHoaDon=itemView.findViewById(R.id.imgHoaDon);
        }
    }
    public void notifyDataSetChange() {
        super.notifyDataSetChanged();
    }

    public void onDataSetChange(List<HoaDon> hoaDonList) {
        this.hoaDonList = hoaDonList;
        notifyDataSetChange();
    }
}
