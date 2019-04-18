package service;

import java.util.HashMap;

import constant.Battle;
import entity.CharacterEntity;
import library.SkillLibrary;

import constant.Character;
import constant.Skill;
import entity.ActionResult;
import model.*;
import util.MathUtil;

public class BattleService {

    //region メソッド

    /**
     * 戦闘初期化
     *
     * @param player
     * @param enemy
     */
    public void init(CharacterEntity player, CharacterEntity enemy) {
        BattleModel battle = new BattleModel();
        battle.clear();
        battle.registerCharacter(player, true);
        battle.registerCharacter(enemy, false);
    }

    public CharacterEntity getStatus(boolean playable) {
        BattleModel battle = new BattleModel();
        return battle.getCharacter(playable);
    }

    /**
     * 攻撃実行(本処理)
     */
    public HashMap<String, ActionResult> actionTurn() {
        HashMap<String, ActionResult> list = new HashMap();
        //前処理
        BattleModel battle = new BattleModel();
        CharacterEntity currentPlayerStatus = battle.getCharacter(true);
        CharacterEntity currentEnemyStatus = battle.getCharacter(false);
        //プレイヤー攻撃時スキル
        activateSkill(list, currentPlayerStatus, currentEnemyStatus, Skill.Timing.PLAYER_ATTACK_SKILL, true);
        if (canContinue()) {
            return list;
        }
        //プレイヤー攻撃
        attack(list, currentPlayerStatus, currentEnemyStatus, true);
        if (canContinue()) {
            return list;
        }
        //敵攻撃時スキル
        activateSkill(list, currentPlayerStatus, currentEnemyStatus, Skill.Timing.ENEMY_ATTACK_SKILL, false);
        if (canContinue()) {
            return list;
        }
        //敵攻撃

        attack(list, currentPlayerStatus, currentEnemyStatus, false);
        if (currentPlayerStatus.getInt("hp") <= 0) {
            list.get(list.size() - 1).finishBattle = true;
        }
        return list;
    }

    private void activateSkill(
            HashMap<String, ActionResult> list,
            CharacterEntity currentPlayerStatus,
            CharacterEntity currentEnemyStatus,
            Skill.Timing timing,
            Boolean playable
    ) {
        SkillLibrary lib = new SkillLibrary();
        ActionResult turnStartSkillResult = lib.activateSkill(
                playable ? currentPlayerStatus : currentEnemyStatus,
                playable ? currentEnemyStatus : currentPlayerStatus,
                timing
        );
        if (turnStartSkillResult != null) {
            list.put(timing.toString(), turnStartSkillResult);
        }
    }

    private void attack(HashMap list, CharacterEntity playerStatus, CharacterEntity enemyStatus, boolean playable) {
        ActionResult result = new ActionResult();
        BattleModel battle = new BattleModel();
        CharacterEntity offenseStatus = playable ? playerStatus : enemyStatus;
        CharacterEntity defenseStatus = playable ? enemyStatus : playerStatus;
        int damage = calcByElement(
                offenseStatus.getInt("atk"),
                offenseStatus.getInt("type"),
                defenseStatus.getInt("type")
        );
        int resultHp = defenseStatus.getInt("hp") - damage;
        defenseStatus.putValue("hp", resultHp);
        battle.updateStatus(!playable, "hp", resultHp);
        //攻撃結果出力
        result.damageValue = damage;
        result.effectType = Battle.convertTypeNum(offenseStatus.getInt("type"));
        result.finishBattle = resultHp < 0;
        list.put(playable ? "playerAttack" : "enemyAttack", result);
    }

    /**
     * 属性ダメージ計算
     *
     * @param damage
     * @param offenceType
     * @param defenceType
     * @return
     */
    private int calcByElement(int damage, int offenceType, int defenceType) {
        if (offenceType == Character.TYPE_NONE) {
            return damage;
        }
        if (offenceType == 1 && defenceType == 3) {
            return damage * 2;
        }
        if (offenceType == 2 && defenceType == 1) {
            return damage * 2;
        }
        if (offenceType == 3 && defenceType == 4) {
            return damage * 2;
        }
        if (offenceType == 4 && defenceType == 2) {
            return damage * 2;
        }
        if (offenceType == 1 && defenceType == 2) {
            return damage / 2;
        }
        if (offenceType == 2 && defenceType == 4) {
            return damage / 2;
        }
        if (offenceType == 3 && defenceType == 1) {
            return damage / 2;
        }
        if (offenceType == 4 && defenceType == 3) {
            return damage / 2;
        }
        return damage;
    }

    /**
     * バトル終了判定
     *
     * @return
     */
    private boolean isFinishBattle() {
        UserModel user = ApplicationModel.getUser();
        return true;
    }

    private boolean awakable(int probability) {
        int num = MathUtil.nextInt(100);
        return num < probability;
    }

    private boolean canContinue() {
        CharacterEntity player = getStatus(true);
        CharacterEntity enemy = getStatus(false);
        return player.getInt("hp") <= 0 || enemy.getInt("hp") <= 0;
    }
    //endregion
}
