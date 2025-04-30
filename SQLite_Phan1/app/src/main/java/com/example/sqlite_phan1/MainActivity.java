package com.example.sqlite_phan1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //khai báo các biến
    EditText edtMaLop, edtTenLop, edtSiSo;
    Button btnInsert, btnDelete, btnUpdate, btnQuery;
    ListView lvHienThi;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //gắn các biến
        edtMaLop = findViewById(R.id.edtMaLop);
        edtTenLop = findViewById(R.id.edtTenLop);
        edtSiSo = findViewById(R.id.edtSiSo);
        btnDelete = findViewById(R.id.btnDelete);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnQuery = findViewById(R.id.btnQuery);
        lvHienThi = findViewById(R.id.lvHienThi);

        //Tạo list view
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lvHienThi.setAdapter(myadapter);

        //Tạo database
        db = openOrCreateDatabase("QL_LopHoc.db", MODE_PRIVATE, null);

        //Tao bảng chứa dữ liệu
        try {
            String query = "CREATE TABLE IF NOT EXISTS LopHoc(MaLop TEXT PRIMARY KEY, TenLop TEXT, SiSo INTEGER)";
            db.execSQL(query);
        } catch (Exception e) {
            Log.e("Error", "Loi tao bang");
        }

        //Xử lý sự kiện insert
        btnInsert.setOnClickListener(v -> {
            String maLop = edtMaLop.getText().toString();
            String tenLop = edtTenLop.getText().toString();
            String siSo = edtSiSo.getText().toString();
            //Đưa vào bản tin
            ContentValues values = new ContentValues();
            values.put("malop", maLop);
            values.put("tenlop", tenLop);
            values.put("siso", siSo);

            //Chuỗi thông báo
            String msg = "";

            //Kiểm tra Insert
            if (db.insert("LopHoc", null, values) == -1) {
                msg = "Insert is failes";
            } else {
                msg = "Insert is success";
            }

            //Toast thông báo
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        });
        //Xử lý sự kiện delete
        btnDelete.setOnClickListener(v -> {
            String maLop = edtMaLop.getText().toString();
            //Chuỗi thông báo
            String msg = "";
            //Kiểm tra delete
            if (db.delete("LopHoc", "malop=?", new String[]{maLop}) == 0) {
                msg = "Delete is failes";
            } else {
                msg = "Delete is success";
            }
            //Toast thông báo
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        });

        //Xử lý sự kiện update
        btnUpdate.setOnClickListener(v -> {
            String maLop = edtMaLop.getText().toString();
            String tenLop = edtTenLop.getText().toString();
            String siSo = edtSiSo.getText().toString();
            //Chuỗi thông báo
            String msg = "";
            //Content cập nhật
            ContentValues values = new ContentValues();
            values.put("tenlop", tenLop);
            values.put("siso", siSo);
            //Kiểm tra update
            if (db.update("LopHoc", values, "malop=?", new String[]{maLop}) == 0) {
                msg = "Update is failes";
            } else {
                msg = "Update is success";
                }
            //Toast thông báo
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        });

        //Xử lý sự kiện query
        btnQuery.setOnClickListener(v -> {
            mylist.clear();
            //Tạo con trỏ để truy xuất dữ liệu
            Cursor cursor = db.query("LopHoc", null, null, null, null, null, null);
            String data = "";
            while(cursor.moveToNext()){
                data = cursor.getString(0) + " | " + cursor.getString(1) + " | " + cursor.getString(2);
                mylist.add(data);
            }
            myadapter.notifyDataSetChanged();
            cursor.close();
        });
    }
}