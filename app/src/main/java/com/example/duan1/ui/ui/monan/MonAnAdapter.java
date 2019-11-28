package com.example.duan1.ui.ui.monan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.database.AppDatabase;
import com.example.duan1.model.Employee;
import com.example.duan1.model.MonAn;

import java.util.List;


public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.MonAnHolder> {
List<MonAn> monAnList;
Context context;

    public MonAnAdapter(List<MonAn> monAnList, Context context) {
        this.monAnList = monAnList;
        this.context = context;
    }

    @NonNull
    @Override
    public MonAnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.rv_monan,parent,false);
        return new MonAnHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnHolder holder, int position) {
        holder.tvTenMonAn.setText(monAnList.get(position).tenMonAn);
        holder.tvGiaMonAn.setText("Giá: "+monAnList.get(position).giaMonAn+"đ");
        holder.imgDeleteMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.imgMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return monAnList.size();
    }

    public static class MonAnHolder extends RecyclerView.ViewHolder {
        public TextView tvTenMonAn, tvGiaMonAn;
        public ImageView imgMonAn,imgDeleteMonAn;
        public MonAnHolder(@NonNull View itemView) {
            super(itemView);

            tvTenMonAn=itemView.findViewById(R.id.tvTenMonAn);
            tvGiaMonAn=itemView.findViewById(R.id.tvGiaMonAn);
            imgMonAn=itemView.findViewById(R.id.imgMonAn);
            imgDeleteMonAn=itemView.findViewById(R.id.imgDeleteMonAn);

        }

    }
 public void notifyDataSetChange(){
        super.notifyDataSetChanged();
 }
    public void onDataSetChange(List<MonAn> monAnList){
        this.monAnList=monAnList;
        notifyDataSetChange();
    }

}

