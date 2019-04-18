package tower_of_solomon.model;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yanmercircle.tower_of_solomon.R;

import tower_of_solomon.constant.Database;

public class DbOpenHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public DbOpenHelper() {
        super(ApplicationModel.getInstance(), Database.DB_NAME, null, Database.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Database.TABLE_NAME_CHARACTER + ";");
        init();
    }

    public void init() {

    }

    public void insert() {
        ContentValues cv = new ContentValues();
        cv.put("id", 0);
        cv.put("name", "テスト");
        cv.put("type", 0);
        cv.put("hp", 9);
        cv.put("atk", 9);
        cv.put("skill", 9);
        db.insert(Database.TABLE_NAME_CHARACTER, null, cv);
    }
}

