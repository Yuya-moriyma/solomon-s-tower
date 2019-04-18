package tower_of_solomon.service;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tower_of_solomon.constant.Database;
import tower_of_solomon.entity.QueryContentValue;
import tower_of_solomon.model.*;
import util.MathUtil;
import util.builder.QueryBuilder;

public class UserService {

    //region メソッド

    /**
     * DB初期化
     */
    public void initDB() {
        try {
            DbOpenHelper helper = new DbOpenHelper();
            SQLiteDatabase db = helper.getWritableDatabase();
            //全テーブル初期化
            db.execSQL("DROP TABLE IF EXISTS " + Database.TABLE_NAME_CHARACTER + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Database.TABLE_NAME_USER + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Database.TABLE_NAME_BATTLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Database.TABLE_NAME_SKILL_TIMER + ";");
            QueryBuilder builder = new QueryBuilder();
            builder.put("id", Database.Type.INTEGER);
            builder.put("name", Database.Type.TEXT);
            builder.put("type", Database.Type.INTEGER);
            builder.put("hp", Database.Type.INTEGER);
            builder.put("atk", Database.Type.INTEGER);
            builder.put("skill_detail", Database.Type.INTEGER);
            db.execSQL(builder.build(Database.TABLE_NAME_CHARACTER));
            builder.clear();
            builder.put("name", Database.Type.TEXT);
            builder.put("party", Database.Type.TEXT);
            builder.put("floor_point", Database.Type.INTEGER);
            builder.put("current_floor", Database.Type.INTEGER);
            builder.put("current_structure", Database.Type.TEXT);
            builder.put("achievement", Database.Type.TEXT);
            builder.put("known_enemys", Database.Type.TEXT);
            db.execSQL(builder.build(Database.TABLE_NAME_USER));
            builder.clear();
            builder.put("playable", Database.Type.INTEGER);
            builder.put("id", Database.Type.INTEGER);
            builder.put("type", Database.Type.INTEGER);
            builder.put("atk", Database.Type.INTEGER);
            builder.put("hp", Database.Type.INTEGER);
            builder.put("sealed", Database.Type.INTEGER);
            builder.put("invincible", Database.Type.INTEGER);
            db.execSQL(builder.build(Database.TABLE_NAME_BATTLE));
            builder.clear();
            builder.put("skill_id", Database.Type.INTEGER);
            builder.put("turn", Database.Type.INTEGER);
            db.execSQL(builder.build(Database.TABLE_NAME_SKILL_TIMER));
//            int firstCharacter = MathUtil.getRandomValue(MathUtil.getRandomList(63, 10));
            int firstCharacter = 71;
            String structure = createFloorConstruct(firstCharacter);
            ContentValues cv = new ContentValues();
            cv.put("name", "テスト");
            cv.put("party", String.valueOf(firstCharacter));
            cv.put("floor_point", 0);
            cv.put("current_floor", 71);
            cv.put("current_structure", structure);
            cv.put("achievement", "");
            cv.put("known_enemys", structure);
            db.insert(Database.TABLE_NAME_USER, null, cv);
            for (QueryContentValue value : Database.ddl) {
                db.insert(Database.TABLE_NAME_CHARACTER, null, value.generate());
            }
//            ContentValues cv2 = new ContentValues();
//            cv2.put("name", "テスト２");
//            db.update(Database.TABLE_NAME_USER, cv2, "", null);
        } catch (Exception e) {
            Exception _e = e;
            Log.e("error", e.getMessage());
        }
    }

    public int getNextFloor(){
        UserModel user = new UserModel();
        return user.getIntData("current_floor")-1;
    }

    public int getNextEnemyId(){
        UserModel user = new UserModel();
        int currentFloor = user.getIntData("current_floor")-1;
        String currentStructure = user.getStringData("current_structure");
        List<String> knownEnemys = Arrays.asList(user.getStringData("known_enemys").split(","));
        String[] _currentStructure = currentStructure.split(",");
        String nextEnemyId = _currentStructure[70-currentFloor];
        if(!knownEnemys.contains(nextEnemyId)){
            nextEnemyId = "0";
        }
        return Integer.valueOf(nextEnemyId);
    }

    public int getFirstCharacter(){
        UserModel user = new UserModel();
        String party = user.getStringData("party");
        return Integer.valueOf(party.split(",")[0]);
    }

    /**
     * 階数作成
     *
     * @return
     */
    private String createFloorConstruct(int firstCharacter) {
        ArrayList<Integer> list = new ArrayList(72);
        list.addAll(MathUtil.getRandomList(63, 10));
        list.add(7);
        list.addAll(MathUtil.getRandomList(53, 10));
        list.add(6);
        list.addAll(MathUtil.getRandomList(44, 9));
        list.add(5);
        list.addAll(MathUtil.getRandomList(35, 9));
        list.add(4);
        list.addAll(MathUtil.getRandomList(26, 9));
        list.add(3);
        list.addAll(MathUtil.getRandomList(17, 9));
        list.add(2);
        list.addAll(MathUtil.getRandomList(8, 9));
        list.add(1);
        list.remove(list.indexOf(firstCharacter));
        list.remove(list.indexOf(55));
        return TextUtils.join(",", list);
    }
}
