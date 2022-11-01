package com.dmt.dangtus.learnandroid.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "quanlimaytinh";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //tao bang danh muc
        String sql =    "CREATE TABLE DanhMuc " +
                        "(id int primary key, " +
                        "ten_danhmuc nvarchar(50) not null)";
        sqLiteDatabase.execSQL(sql);

        //them du lieu vao bang danh muc
        sql =   "INSERT INTO DanhMuc " +
                "VALUES(1, 'Máy tính văn phòng'), " +
                "(2, 'Máy tính gamming')";
        sqLiteDatabase.execSQL(sql);

        //tao bang may tinh
        sql =   "CREATE TABLE MayTinh " +
                "(id int primary key, " +
                "ten_maytinh nvarchar(50) not null, " +
                "id_danhmuc int not null, " +
                "FOREIGN KEY (id_danhmuc) REFERENCES DanhMuc (id))";
        sqLiteDatabase.execSQL(sql);

        //them du lieu vao bang may tinh
        sql =   "INSERT INTO MayTinh " +
                "VALUES(1, 'Dell 1', 1), " +
                "(2, 'Dell 2', 2), " +
                "(3, 'Dell 3', 1)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS MayTinh";
        sqLiteDatabase.execSQL(sql);

        sql = "DROP TABLE IF EXISTS DanhMuc";
        sqLiteDatabase.execSQL(sql);

        onCreate(sqLiteDatabase);
    }
}
