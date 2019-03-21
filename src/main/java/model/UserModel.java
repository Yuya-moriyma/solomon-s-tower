package model;

import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import constant.Character;

public class UserModel {

    public String getStringData(String key) {
        SQLiteCursor c = getRecord();
        return c.getString(c.getColumnIndex(key));
    }

    public int getIntData(String key) {
        SQLiteCursor c = getRecord();
        return c.getInt(c.getColumnIndex(key));
    }

    private SQLiteCursor getRecord() {
        DbOpenHelper helper = new DbOpenHelper();
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from t_user;";
        SQLiteCursor c = (SQLiteCursor) db.rawQuery(sql, null);
        c.moveToFirst();
        return c;
    }
}