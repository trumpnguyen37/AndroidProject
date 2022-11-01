package com.dmt.dangtus.learnandroid.sqlite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dmt.dangtus.learnandroid.model.Category;
import com.dmt.dangtus.learnandroid.model.Computer;

import java.util.ArrayList;
import java.util.List;

public class DanhMucDao {
    private SQLiteDatabase db;

    public DanhMucDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<Category> get(String sql, String ...doiSo) {
        List<Category> categoryList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, doiSo);

        while (cursor.moveToNext()) {
            Category category = new Category();
            category.setId(cursor.getInt(cursor.getColumnIndex("id")));
            category.setName(cursor.getString(cursor.getColumnIndex("ten_danhmuc")));

            categoryList.add(category);
        }

        return categoryList;
    }

    public List<Category> getAll() {
        String sql =    "SELECT * " +
                        "FROM DanhMuc";

        return get(sql);
    }
}
