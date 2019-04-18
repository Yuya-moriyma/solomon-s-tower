package tower_of_solomon.entity;

import android.database.sqlite.SQLiteCursor;

import java.util.HashMap;

import tower_of_solomon.constant.Database;

public class CharacterEntity {

    protected HashMap<String, Integer> intValue;
    protected HashMap<String, String> stringValue;

    public CharacterEntity(SQLiteCursor cursor) {
        this.intValue = new HashMap();
        this.stringValue = new HashMap();
        putValue(cursor, "name", Database.Type.TEXT);
        putValue(cursor, "id", Database.Type.INTEGER);
        putValue(cursor, "hp", Database.Type.INTEGER);
        putValue(cursor, "atk", Database.Type.INTEGER);
        putValue(cursor, "type", Database.Type.INTEGER);
        putValue(cursor, "skill_detail", Database.Type.TEXT);
        putValue(cursor, "playable", Database.Type.INTEGER);
        putValue(cursor, "sealed", Database.Type.INTEGER);
    }

    private void putValue(SQLiteCursor cursor, String key, Database.Type type) {
        int index = cursor.getColumnIndex(key);
        if (index < 0) {
            return;
        }
        if (type == Database.Type.INTEGER) {
            this.intValue.put(key, cursor.getInt(index));
            return;
        }
        this.stringValue.put(key, cursor.getString(index));
    }

    public void putValue(String key, String value) {
        this.stringValue.put(key, value);
    }

    public void putValue(String key, int value) {
        this.intValue.put(key, value);
    }

    public String getString(String key) {
        return this.stringValue.get(key);
    }

    public int getInt(String key) {
        return this.intValue.get(key);
    }

    public Boolean isDied(){
        return this.intValue.get("hp") <= 0;
    }
}
