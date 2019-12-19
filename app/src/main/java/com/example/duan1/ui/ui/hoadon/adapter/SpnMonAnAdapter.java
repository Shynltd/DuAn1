package com.example.duan1.ui.ui.hoadon.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.duan1.R;
import com.example.duan1.model.MonAn;
import com.example.duan1.ui.ui.monan.MonAnAdapter;
import com.example.duan1.ui.ui.nhanvien.NhanVienAdapter;

import java.util.List;

public class SpnMonAnAdapter implements SpinnerAdapter {
    List<MonAn>monAnList;
    Context context;

    public SpnMonAnAdapter(List<MonAn> monAnList, Context context) {
        this.monAnList = monAnList;
        this.context = context;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.spn_monan, parent, false);
        MonAn monAn=monAnList.get(position);
        TextView tvSpnTen,tvSpnGia;
        tvSpnGia=convertView.findViewById(R.id.tvSpnGia);
        tvSpnTen=convertView.findViewById(R.id.tvSpnTen);
        tvSpnTen.setText(monAn.tenMonAn);
        tvSpnGia.setText("Giá: "+monAn.giaMonAn+"đ");
        return convertView;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return monAnList.size();
    }

    @Override
    public MonAn getItem(int position) {
        return monAnList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


            convertView = LayoutInflater.from(context).inflate(R.layout.spn_monan, parent, false);
            MonAn monAn=monAnList.get(position);
            TextView tvSpnTen,tvSpnGia;
            tvSpnGia=convertView.findViewById(R.id.tvSpnGia);
            tvSpnTen=convertView.findViewById(R.id.tvSpnTen);
            tvSpnTen.setText(monAn.tenMonAn);
            tvSpnGia.setText("Giá: "+monAn.giaMonAn+"đ");
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


}
