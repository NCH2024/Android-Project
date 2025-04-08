package com.example.ontap1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //Khai báo biến
    EditText edtHoTen, edtCCCD, edtSoThich;
    RadioGroup idGroup;
    CheckBox ckbDocSach, ckbNgheNhac, ckbXemPhim;
    Button btnGui;
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

        //Ánh xạ id
        edtHoTen = findViewById(R.id.edtHoTen);
        edtCCCD = findViewById(R.id.edtCCCD);
        edtSoThich = findViewById(R.id.edtSoThich);
        idGroup = findViewById(R.id.rdGroup);
        ckbDocSach = findViewById(R.id.ckbDocSach);
        ckbNgheNhac = findViewById(R.id.ckbNgheNhac);
        ckbXemPhim = findViewById(R.id.ckbXemPhim);
        btnGui = findViewById(R.id.btnGui);

        //Xử lý sự kiện click vào Button
        btnGui.setOnClickListener(v -> {
            //Lấy thông tin họ tên
            String hoTen = edtHoTen.getText().toString();
            if(hoTen.isEmpty() || hoTen.length() < 3){
                Toast.makeText(this, "Họ tên không được trống hoặc lớn hơn 3 ký tự", Toast.LENGTH_LONG).show();
                edtHoTen.requestFocus();
                edtHoTen.selectAll();
                return;
            }
            //Lấy thông tin cccd
            String cccd = edtCCCD.getText().toString();
            if(cccd.length() != 12){
                Toast.makeText(this, "CCCD không được trống hoặc không đúng định dạng", Toast.LENGTH_LONG).show();
                edtCCCD.requestFocus();
                edtCCCD.selectAll();
                return;
            }
            //Lấy thông tin bằng cấp
            int id = idGroup.getCheckedRadioButtonId();
            RadioButton radselected = findViewById(id);
            String bangCap = radselected.getText().toString();

            //Lấy thông tin sở thích
            String soThich = "";
            if(ckbDocSach.isChecked()){
                soThich += ckbDocSach.getText().toString() + ", ";
            };
            if(ckbNgheNhac.isChecked()){
                soThich += ckbNgheNhac.getText().toString() + ", ";
            };
            if(ckbXemPhim.isChecked()){
                soThich += ckbXemPhim.getText().toString() + ", ";
            };

            //Lấy thông tin bổ sung
            String boSung = edtSoThich.getText().toString();

            //Hiển thị thông tin
            String tongHop = hoTen+"\n"+cccd+"\n"+bangCap+"\n"+soThich+"\n"+"------------THÔNG TIN BỔ SUNG------------\n"+boSung+"\n------------------------------------------------------------";

            //tạo Dialog hiển thị
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Thông tin cá nhân");
            builder.setMessage(tongHop);
            builder.setPositiveButton("ĐÓNG", (dialog, which) -> { dialog.dismiss(); });
            builder.create().show();
        });
    }
}