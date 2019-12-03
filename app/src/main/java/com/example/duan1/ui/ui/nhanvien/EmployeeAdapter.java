package com.example.duan1.ui.ui.nhanvien;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeHolder> {
    @NonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class EmployeeHolder extends RecyclerView.ViewHolder {
        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
