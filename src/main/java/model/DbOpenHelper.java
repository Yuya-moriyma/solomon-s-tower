package model;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import constant.Database;

import static java.sql.Types.NULL;

public class DbOpenHelper extends SQLiteOpenHelper {

    //region コンストラクタ
    public DbOpenHelper() {
        super(ApplicationModel.getInstance(), Database.DB_NAME, null, Database.DB_VERSION);
    }
    //endregion

    //region メソッド
    @Override
    public void onCreate(SQLiteDatabase db) {
        //全テーブル初期化
        db.execSQL(Database.CREATE_TABLE_CHARACTER_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Database.TABLE_NAME_CHARACTER + ";");
        onCreate(db);
        testCharacterInsert(db);
    }
    public void testInsert(SQLiteDatabase db) {
        testCharacterInsert(db);
    }
    public void insert(SQLiteDatabase db, String tableName, ContentValues cv){
        db.insert(tableName, null, cv);
    }
    public void testCharacterInsert(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put("id", 0);
        cv.put("name", "テスト");
        cv.put("type", 0);
        cv.put("hp", 9);
        cv.put("atk", 9);
        cv.put("skill", 9);
        db.insert(Database.TABLE_NAME_CHARACTER, null, cv);
    }
    //endregion
}

