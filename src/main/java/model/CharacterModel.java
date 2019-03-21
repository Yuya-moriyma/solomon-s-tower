package model;

import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.*;

import constant.Character;
import constant.Status;
import entity.ActionResult;
import entity.CharacterEntity;

public class CharacterModel {

    //region フィールド

    private StatusModel status;
    private BattleStatusModel battleStatus;

    //endregion

    public CharacterEntity getCharacter(int id) {
        return new CharacterEntity(findById(id));
    }

    private SQLiteCursor findById(int id) {
        SQLiteCursor c = null;
        try {
            DbOpenHelper helper = new DbOpenHelper();
            SQLiteDatabase db = helper.getReadableDatabase();
            String sql = "select * from m_character where id = " + id + ";";
            c = (SQLiteCursor) db.rawQuery(sql, null);
            c.moveToFirst();
        } catch (Exception e) {
            Exception _e = e;
        }
        return c;
    }

    //region メソッド

    /**
     * スキル封印
     *
     * @param turnCount
     */
    public void sealed(int turnCount) {
        this.battleStatus.setSkillCount(Status.STATUS_SKILL_AWAKABLE, turnCount);
    }

    /**
     * スキルカウント設定
     *
     * @param turnCount
     */
    public void setSkillCount(String property, int turnCount) {
        this.battleStatus.setSkillCount(property, turnCount);
    }


    /**
     * ステータス初期化
     */
    public void refresh() {
        createBattleStatus();
    }

    /**
     * バトル用ステータス作成
     */
    public void createBattleStatus() {
        HashMap status = (HashMap) this.status.getValueStatus().clone();
        this.battleStatus = new BattleStatusModel(status);
    }

    /**
     * ステータス取得
     *
     * @param key
     * @return
     */
    public int getStatus(String key) {
        return this.status.getStatus(key);
    }

    /**
     * 戦闘中ステータス取得
     *
     * @param key
     * @return
     */
    public int getBattleStatus(String key) {
        return this.battleStatus.getStatus(key);
    }

    /**
     * 戦闘中ステータス更新
     *
     * @param key
     * @return
     */
    public void updateBattleStatus(String key, int value) {
        this.battleStatus.updateStatus(key, value);
    }

    /**
     * スキル使用可否判定
     *
     * @return
     */
    public boolean isSkillActive() {
        if (this.battleStatus.getSkillCounts().isEmpty()) {
            return true;
        }
        return true;
        //this.battleStatus.getSkillCount(Status.STATUS_SKILL_AWAKABLE) == 0;
    }
    //endregion

    //region アクセサー

    public String getName() {
        return this.status.getName();
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public BattleStatusModel getBattleStatus() {
        return battleStatus;
    }

    //endregion
}
