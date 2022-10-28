package com.dmt.dangtus.learnandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    private ContentResolver resolver;
    private TextView txtName, txtSDT;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtSDT = (TextView) findViewById(R.id.sdt);
        txtName = (TextView) findViewById(R.id.name);

        Intent intent = getIntent();
        long id = intent.getLongExtra("id", 0);
        if(id != 0) {
            resolver = getContentResolver();
            //select
            Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    new String[]{String.valueOf(id)}, null);
            String phone = "";
            String name = "";
            while(cursor.moveToNext()) {
                phone += cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)) + " | ";
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            }

            txtSDT.setText(phone);
            txtName.setText(name);
        }
    }
}