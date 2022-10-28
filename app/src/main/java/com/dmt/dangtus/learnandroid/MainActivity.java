package com.dmt.dangtus.learnandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvDanhBa;
    private ContentResolver resolver;
    private List<String> danhBaList = new ArrayList<>();
    private List<Long> idList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvDanhBa = (ListView) findViewById(R.id.danhBaListView);

        if(checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.READ_CONTACTS}, 100);
        }

        resolver = getContentResolver();

        //select
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts._ID},
                null, null, null);
        while(cursor.moveToNext()) {
            danhBaList.add(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
            idList.add(cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID)));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, danhBaList);
        lvDanhBa.setAdapter(adapter);

        //set su kien khi click vao list view
        lvDanhBa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                long id = idList.get(i);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }
}