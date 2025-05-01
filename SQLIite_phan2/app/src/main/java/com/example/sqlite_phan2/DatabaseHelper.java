package com.example.sqlite_phan2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "QLSV.db";
    public static final String TABLE_NAME = "SinhVien";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "AGE";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2 + " TEXT, " +
                COL_3 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String ten, String tuoi){
        if (ten.isEmpty() || tuoi.isEmpty()) return false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, ten);
        values.put(COL_3, tuoi);

        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public boolean deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, "ID=?", new String[]{id});
        return result > 0;
    }

    public boolean updateData(String id, String ten, String tuoi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, ten);
        values.put(COL_3, tuoi);

        int result = db.update(TABLE_NAME, values, "ID=?", new String[]{id});
        return result > 0;
    }

    public ArrayList<String> selectData(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> dataList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            Log.d("DatabaseHelper", "Đang xử lý một hàng dữ liệu...");
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String age = cursor.getString(2);
            String data = id + " - " + name + " - " + age;
            dataList.add(data);
        }
        cursor.close();
        return dataList;
    }
}
