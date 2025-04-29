package com.example.listview_nangcao;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Phone> {
    Activity context;
    int idLayout;
    ArrayList<Phone> myList;

    //constructor

    public MyArrayAdapter(@NonNull  Activity context, int idLayout, ArrayList<Phone> myList) {
        super(context, idLayout, myList);
        this.context = context;
        this.idLayout = idLayout;
        this.myList = myList;
    }
    //gọi hàm getview để tiến hành sắp xếp dữ liệu


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Tạo view chứa layout
        LayoutInflater inflater = context.getLayoutInflater();
        //Đặt layout vào view
        convertView = inflater.inflate(idLayout, null);
        //Lấy ra dữ liệu từ mảng
        Phone myPhone = myList.get(position);
        //Khai báo tham chiếu id và hiển thị dữ liệu lên ImageView
        ImageView imgPhone = convertView.findViewById(R.id.imgPhone);
        imgPhone.setImageResource(myPhone.getImage());
        //Khai báo tham chiếu id và hiển thị dữ liệu lên TextView
        TextView txtPhone = convertView.findViewById(R.id.txtPhone);
        txtPhone.setText(myPhone.getName());
        return convertView;
    }
}
