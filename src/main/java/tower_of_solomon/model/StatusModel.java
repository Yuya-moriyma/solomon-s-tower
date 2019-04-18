package tower_of_solomon.model;

import android.util.Log;

import java.util.HashMap;

import tower_of_solomon.constant.Character;

public class StatusModel {

    //region フィールド
    private String name;
    protected HashMap<String, Integer> valueStatus;
    //endregion

    //region メソッド
    /**
     * コンストラクタ
     * @param status
     */
    public StatusModel(String[] status)
    {
        if(status.length != 6)
        {
            Log.i("AppError", "No much status column");
        }
        this.name = status[0];
        this.valueStatus = new HashMap();
        this.valueStatus.put(Character.STATUS_ID, Integer.parseInt(status[1]));
        this.valueStatus.put(Character.STATUS_TYPE, Integer.parseInt(status[2]));
        this.valueStatus.put(Character.STATUS_HP, Integer.parseInt(status[3]));
        this.valueStatus.put(Character.STATUS_ATK, Integer.parseInt(status[4]));
        this.valueStatus.put(Character.STATUS_SKILL, Integer.parseInt(status[5]));
    }

    /**
     * コンストラクタ(戦闘用)
     * @param valueStatus
     */
    public StatusModel(HashMap<String, Integer> valueStatus) {
        this.valueStatus = valueStatus;
    }
    //endregion

    //region アクセサー
    public String getName() {
        return this.name;
    }

    public int getStatus(String key) {
        return this.valueStatus.get(key);
    }

    public int updateStatus(String key, int value) {
        return this.valueStatus.put(key, value);
    }

    public HashMap<String, Integer> getValueStatus() {
        return this.valueStatus;
    }

    public void setValueStatus(HashMap<String, Integer> valueStatus) {
        this.valueStatus = valueStatus;
    }
    //endregion
}
