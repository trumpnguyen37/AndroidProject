package com.example.quanlysinhvienfirebase.QuanLySinhVienFirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlysinhvienfirebase.R;
import com.example.quanlysinhvienfirebase.model.SinhVien;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThemSvActivity extends AppCompatActivity {

    private EditText edtMssv, edtTen, edtEmail, edtSdt;
    private Button btnThem, btnHuy, btnTroVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sv);
        addControls(); //khai báo các control trên giao diện
        addEvents(); //khai báo các sự kiện vd: btnThem click, btnHuy click..
    }

    private void addEvents() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //trở về lại màn hình trước đó
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hủy thì sẽ xóa hết các text trên edt
                edtMssv.setText("");
                edtEmail.setText("");
                edtSdt.setText("");
                edtTen.setText("");
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sự kiện thêm. Thêm sinh viên vào firebase database
                //lấy mssv, ten, email, sdt trên view.
                String mssv = edtMssv.getText().toString();
                String ten = edtTen.getText().toString();
                String email = edtEmail.getText().toString();
                String sdt = edtSdt.getText().toString();
                //ở đây không nhập ID vì Firebase database nó tự sinh cho mình một cái Id
                SinhVien sinhVien = new SinhVien(mssv, ten, email, sdt);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("DbSinhVien");
                //tạo một Id ngẫu nhiên trên firebase database/DbSinhVien/
                String id = myRef.push().getKey();
                //dựa vào Id này, mình sẽ thêm dữ liệu sinh viên vào
                myRef.child(id).setValue(sinhVien).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //nếu thêm thành công sẽ nhảy vào đây
                        Toast.makeText(getApplicationContext(),"Thêm thành công!",Toast.LENGTH_LONG).show();
                        finish(); //thoát màn hình thêm, trở về màn hình danh sách sv
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //nếu thêm thất bại sẽ nhảy vào đây
                        Toast.makeText(getApplicationContext(),"Thêm thất bại! " + e.toString(),Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    private void addControls() {
        edtMssv = findViewById(R.id.edtMssv);
        edtTen = findViewById(R.id.edtHoTen);
        edtEmail = findViewById(R.id.edtEmail);
        edtSdt = findViewById(R.id.edtSoDienThoai);

        btnHuy = findViewById(R.id.btnHuy);
        btnThem = findViewById(R.id.btnThem);
        btnTroVe = findViewById(R.id.btnTroVe);
    }
}