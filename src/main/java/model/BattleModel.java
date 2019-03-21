package model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteCursor;

import constant.Database;
import entity.CharacterEntity;
import util.LogUtil;

public class BattleModel extends BaseModel {

    public BattleModel() {
        this.tableName = Database.TABLE_NAME_BATTLE;
        DbOpenHelper helper = new DbOpenHelper();
        db = helper.getReadableDatabase();
    }

    public void registerCharacter(CharacterEntity entity, boolean playable) {
        ContentValues cv = new ContentValues();
        cv.put("playable", playable ? 1 : 0);
        cv.put("id", entity.getInt("id"));
        cv.put("type", entity.getInt("type"));
        cv.put("atk", entity.getInt("atk"));
        cv.put("hp", entity.getInt("hp"));
        cv.put("sealed", 0);
        db.insert(tableName, null, cv);
    }

    public void clear() {
        db.delete(tableName, null, null);
    }

    public int getStatus(boolean playable, String key) {
        SQLiteCursor c = getRecord(playable);
        return c.getInt(c.getColumnIndex(key));
    }

    public CharacterEntity getCharacter(boolean playable) {
        return new CharacterEntity(getRecord(playable));
    }

    public void updateStatus(boolean playable, String key, int value) {
        ContentValues cv2 = new ContentValues();
        cv2.put(key, value);
        db.update(tableName, cv2, "playable = " + (playable ? 1 : 0), null);
    }

    private SQLiteCursor getRecord(boolean playable) {
        SQLiteCursor c = null;
        try {
            String sql = "select * from " + tableName + " where playable = " + (playable ? 1 : 0) + ";";
            LogUtil.out(sql);
            c = (SQLiteCursor) db.rawQuery(sql, null);
            c.moveToFirst();
        } catch (Exception e) {
            Exception _e = e;
        }
        return c;
    }
}
