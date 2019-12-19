package com.example.duan1.ui.ui.monan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.duan1.R;
import com.example.duan1.dao.MyOnItemClickListener;
import com.example.duan1.database.AppDatabase;
import com.example.duan1.model.MonAn;

import java.util.List;


public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.MonAnHolder> {
List<MonAn> monAnList;

    public static Context context;
    private AppDatabase appDatabase;
    private MyOnItemClickListener myOnItemClickListener;

    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }

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
    public void onBindViewHolder(@NonNull MonAnHolder holder, final int position) {
        final MonAn monAn=monAnList.get(position);
        holder.tvTenMonAn.setText(monAn.tenMonAn);
        holder.tvGiaMonAn.setText("Giá: "+monAn.giaMonAn+"đ");
        appDatabase= Room.databaseBuilder(context,AppDatabase.class,"duan1.db").allowMainThreadQueries().build();


        holder.imgMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog alertDialog=new AlertDialog.Builder(context).create();
                 v = LayoutInflater.from(context).inflate(R.layout.update_monan, null);
                alertDialog.setView(v);
                final EditText edIDMonAn, edTenMonAn, edGiaMonAn;
                Button btnUpdateMonAn;
                edGiaMonAn = v.findViewById(R.id.edGiaMonAn);
                edIDMonAn = v.findViewById(R.id.edIDMonAn);
                edTenMonAn = v.findViewById(R.id.edTenMonAn);
                btnUpdateMonAn = v.findViewById(R.id.btnUpdateMonAn);
                edGiaMonAn.setText(monAn.giaMonAn + "");
                edIDMonAn.setText(monAn.IDMonAn);
                edTenMonAn.setText(monAn.tenMonAn);
                edIDMonAn.setEnabled(false);
                edTenMonAn.setEnabled(false);
                btnUpdateMonAn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (edGiaMonAn.getText().toString().isEmpty()) {
                            Toast.makeText(context, "Bạn chưa nhập giá", Toast.LENGTH_SHORT).show();
                        } else {
                            monAn.giaMonAn = Integer.parseInt(edGiaMonAn.getText().toString() + "");
                            appDatabase.monAnDAO().updateMonAn(monAn);
                            Toast.makeText(context, "Bạn đã sửa thông tin món ăn thành công", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                            notifyDataSetChanged();
                        }
                    }
                });
                alertDialog.show();
            }

        });
        holder.imgDeleteMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Bạn đã xóa món ăn "+monAn.tenMonAn+" ra khỏi menu", Toast.LENGTH_SHORT).show();
                        monAnList.remove(monAn);
                        appDatabase.monAnDAO().deleteMonAn(monAn);
                        notifyDataSetChange();

                    }
                }).setMessage("Bạn có muốn bỏ "+monAn.tenMonAn+" ra khỏi menu không ?");
                builder.create().show();
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


