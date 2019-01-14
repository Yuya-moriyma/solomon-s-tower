package model;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import constant.Status;

public class BattleStatusModel extends StatusModel {

    private HashMap<String, Integer> skillCounts;

    //region コンストラクタ

    /**
     * コンストラクタ
     *
     * @param valueStatus
     */
    public BattleStatusModel(HashMap<String, Integer> valueStatus) {
        super(valueStatus);
        this.skillCounts = new HashMap();
    }

    //endregion

    //region メソッド

    //endregion

    //region アクセサー
    public int getSkillCount(String key) {
        return skillCounts.get(key);
    }

    public void setSkillCount(String property, int skillCount) {
        this.skillCounts.put(property, skillCount);
    }

    public HashMap<String, Integer> getSkillCounts() {
        return skillCounts;
    }

    //endregion
}
