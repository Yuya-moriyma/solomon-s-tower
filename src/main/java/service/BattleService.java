package service;

import java.util.ArrayList;

import constant.Battle;
import entity.CharacterEntity;
import library.SkillLibrary;

import constant.Character;
import constant.Skill;
import entity.ActionResult;
import model.*;
import util.MathUtil;

import static constant.Battle.SKILL_SEALED_FLG;

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

    public  CharacterEntity getStatus(boolean playable){
        BattleModel battle = new BattleModel();
        return battle.getCharacter(playable);
    }

    /**
     * 攻撃実行(本処理)
     */
    public ArrayList<ActionResult> actionTurn() {
        ArrayList<ActionResult> list = new ArrayList<>();
        //前処理
        BattleModel battle = new BattleModel();
        CharacterEntity currentPlayerStatus = battle.getCharacter(true);
        CharacterEntity currentEnemyStatus = battle.getCharacter(false);
        SkillLibrary lib = new SkillLibrary();
        //プレイヤー攻撃時スキル
        if (currentPlayerStatus.getInt("sealed") != SKILL_SEALED_FLG) {
            list.add(lib.activateSkill(currentPlayerStatus, currentEnemyStatus, Skill.TIMING.Attack));
            if (currentEnemyStatus.getInt("hp") <= 0) {
                list.get(list.size() - 1).finishBattle = true;
                return list;
            }
        }
        //プレイヤー攻撃
        attack(true, currentPlayerStatus, currentEnemyStatus);
        if (currentEnemyStatus.getInt("hp") <= 0) {
            list.get(list.size() - 1).finishBattle = true;
            return list;
        }
        if (currentEnemyStatus.getInt("sealed") != SKILL_SEALED_FLG) {
            //敵攻撃時スキル
            lib.activateSkill(currentEnemyStatus, currentPlayerStatus, Skill.TIMING.Attack);
            if (currentPlayerStatus.getInt("hp") <= 0) {
                list.get(list.size() - 1).finishBattle = true;
                return list;
            }
        }
        //敵攻撃
        attack(false, currentPlayerStatus, currentEnemyStatus);
        if (currentPlayerStatus.getInt("hp") <= 0) {
            list.get(list.size() - 1).finishBattle = true;
        }
        return list;
    }

    private void attack(boolean playable, CharacterEntity playerStatus, CharacterEntity enemyStatus) {
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
        battle.updateStatus(playable, "hp", resultHp);
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
    //endregion
}
