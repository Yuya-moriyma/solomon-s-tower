package model;

import java.util.*;

import constant.Character;
import constant.Status;
import entity.ActionResult;

public class CharacterModel {

    //region フィールド

    private StatusModel status;
    private BattleStatusModel battleStatus;

    //endregion

    //region コンストラクタ

    /**
     * コンストラクタ
     *
     * @param status
     */
    public CharacterModel(StatusModel status) {
        this.status = status;
    }
    //endregion

    //region メソッド

    /**
     * ダメージ計算処理
     *
     * @param damageValue
     * @param result
     */
    public void damage(int damageValue, ActionResult result) {
        int currentHp = this.getStatus(Character.STATUS_HP);
        int damagedHp = currentHp - damageValue;
        if (damagedHp <= 0) {
            result.killed = true;
            damagedHp = 0;
        }
        this.updateBattleStatus(Character.STATUS_HP, damagedHp);
        result.damageValue = currentHp - damagedHp;
    }

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
        if(this.battleStatus.getSkillCounts().isEmpty()){
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
