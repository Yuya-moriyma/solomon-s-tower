package registory;

import android.database.sqlite.SQLiteDatabase;

import model.DbOpenHelper;

public class BaseRegistry {

    //region フィールド
    DbOpenHelper helper = null;
    SQLiteDatabase db = null;
    String tableName;
    //endregion

    //region コンストラクタ
    public BaseRegistry(String tableName) {
        this.helper = new DbOpenHelper();
        this.db = helper.getReadableDatabase();
        this.tableName = tableName;
}
    //endregion
}
