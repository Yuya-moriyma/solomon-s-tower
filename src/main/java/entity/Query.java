package entity;

import android.content.ContentValues;

import java.util.HashMap;
import java.util.Map;

import constant.Database;

public class Query {

    //region フィールド
    private Map<String, Integer> intValue;
    private Map<String, String> stringValue;
    //endregion

    //region コンストラクタ
    public Query() {
        intValue = new HashMap<>();
        stringValue = new HashMap<>();
    }
    //endregion

    //region メソッド
    public void putIntValue(String key, int value) {
        intValue.put(key, value);
    }

    public void putStringValue(String key, String value) {
        stringValue.put(key, value);

    }
    public <T> void putValue(String key, T value) {
        if(value instanceof Integer){
            intValue.put(key, (Integer)value);
            return;
        }
        if(value instanceof String){
            stringValue.put(key, (String)value);
            return;
        }
    }
    public ContentValues getInsertData(int type) {
        ContentValues cv = new ContentValues();
        if(type == Database.QUERY_TYPE_INSERT){
            for(Map.Entry<String, String> entry : stringValue.entrySet()){
                cv.put(entry.getKey(), entry.getValue());
            }
            for(Map.Entry<String, Integer> entry : intValue.entrySet()){
                cv.put(entry.getKey(), entry.getValue());
            }
        }
        return cv;
    }
}
