package util.builder;

import android.text.TextUtils;

import java.util.ArrayList;

import constant.Database;

public class QueryBuilder {

    public ArrayList<String[]> list = new ArrayList();

    /**
     * 値をセット
     *
     * @param key
     * @param type
     */
    public void put(String key, Database.Type type) {
        String[] column = {key, type.toString()};
        list.add(column);
    }

    /**
     * CREATE文生成
     *
     * @param tableName
     * @return
     */
    public String build(String tableName) {
        String query = "create table " + tableName+ " (";
        ArrayList<String> columnList =  new ArrayList<>();
        for (String[] column : list) {
            columnList.add(column[0] + " " + column[1]);
        }
        return query + TextUtils.join(",", columnList) + ");";
    }

    /**
     * 値をリセット
     */
    public void clear(){
        list.clear();
    }
}
