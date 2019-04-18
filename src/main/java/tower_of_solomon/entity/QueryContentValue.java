package tower_of_solomon.entity;

import android.content.ContentValues;

public class QueryContentValue {
    private ContentValues contentValues;

    public QueryContentValue() {
        contentValues = new ContentValues();
    }

    public QueryContentValue put(String key, int value) {
        contentValues.put(key, value);
        return this;
    }

    public QueryContentValue put(String key, String value) {
        contentValues.put(key, value);
        return this;
    }

    public ContentValues generate() {
        return contentValues;
    }
}
