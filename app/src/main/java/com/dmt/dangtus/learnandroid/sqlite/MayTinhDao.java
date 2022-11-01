package com.dmt.dangtus.learnandroid.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dmt.dangtus.learnandroid.model.Category;
import com.dmt.dangtus.learnandroid.model.Computer;

import java.util.ArrayList;
import java.util.List;

public class MayTinhDao {
    private SQLiteDatabase db;

    public MayTinhDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<Computer> get(String sql, String ...doiSo) {
        List<Computer> computerList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, doiSo);

        while (cursor.moveToNext()) {
            Computer computer = new Computer();
            computer.setId(cursor.getInt(cursor.getColumnIndex("MayTinh.id")));
            computer.setName(cursor.getString(cursor.getColumnIndex("ten_maytinh")));
            //set category
            Category category = new Category(cursor.getInt(cursor.getColumnIndex("id_danhmuc")), cursor.getString(cursor.getColumnIndex("ten_danhmuc")));
            computer.setCategory(category);

            computerList.add(computer);
        }

        return computerList;
    }

    public List<Computer> getAll() {
        String sql =    "SELECT * " +
                        "FROM MayTinh, DanhMuc " +
                        "WHERE DanhMuc.id = MayTinh.id_danhmuc";

        return get(sql);
    }

    public Computer getById(String id) {
        String sql =    "SELECT MayTinh.id as id_maytinh, ten_maytinh, id_danhmuc, ten_danhmuc " +
                        "FROM MayTinh, DanhMuc " +
                        "WHERE DanhMuc.id = MayTinh.id_danhmuc " +
                        "and id = ?";
        List<Computer> computerList = get(sql, id);

        return computerList.get(0);
    }

    public long insert(Computer computer) {
        ContentValues values = new ContentValues();
        values.put("id", computer.getId());
        values.put("ten_maytinh", computer.getName());
        values.put("id_danhmuc", computer.getCategory().getId());

        return db.insert("MayTinh", null, values);
    }

    public long update(Computer computer) {
        ContentValues values = new ContentValues();
        values.put("ten_maytinh", computer.getName());
        values.put("id_danhmuc", computer.getCategory().getId());

        return db.update("MayTinh", values, "id = ?", new String[]{String.valueOf(computer.getId())});
    }

    public long delete(String id) {
        return db.delete("MayTinh", "id = ?", new String[]{id});
    }
}
