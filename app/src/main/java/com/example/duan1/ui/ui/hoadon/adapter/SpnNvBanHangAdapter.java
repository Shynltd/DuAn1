package com.example.duan1.ui.ui.hoadon.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.duan1.R;
import com.example.duan1.model.Employee;

import java.util.List;

public class SpnNvBanHangAdapter implements SpinnerAdapter {
    List<Employee> employeeList;
    Context context;

    public SpnNvBanHangAdapter(List<Employee> employeeList, Context context) {
        this.employeeList = employeeList;
        this.context = context;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView tvTenNguoiBan;
        convertView= LayoutInflater.from(context).inflate(R.layout.spn_nguoiban,parent,false);
        tvTenNguoiBan=convertView.findViewById(R.id.tvNguoiBan);
        tvTenNguoiBan.setText(employeeList.get(position).hoVaTen);
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
        return employeeList.size();
    }

    @Override
    public Employee getItem(int position) {
        return employeeList.get(position);
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
        TextView tvTenNguoiBan;
            convertView= LayoutInflater.from(context).inflate(R.layout.spn_nguoiban,parent,false);
            tvTenNguoiBan=convertView.findViewById(R.id.tvNguoiBan);
        tvTenNguoiBan.setText(employeeList.get(position).hoVaTen);
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
