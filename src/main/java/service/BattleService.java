package service;

import library.SkillLibrary;
import registory.CharacterRegistry;

import java.util.List;
import java.util.Map;

import constant.Character;
import constant.Skill;
import entity.ActionResult;
import model.*;
import util.LogUtil;

public class BattleService {

    //region メソッド

    /**
     * バトル準備
     *
     * @param id
     */
    public void init(int id) {
        try {
            UserModel user = ApplicationModel.getUser();
            CharacterRegistry registry = new CharacterRegistry();
            //CharacterModel enemy = registry.createCharacter(index);
            CharacterModel enemy = registry.createCharacter(56);
            BattleModel battle = new BattleModel(user.getParty(), enemy);
            ApplicationModel.setBattle(battle);
            battle.ready();
        } catch (RuntimeException e) {
            LogUtil.printStackTrace(e);
            return;
        }
    }

    /**
     * スキル発動
     *
     * @param isPlayer
     */
    public ActionResult activateSkill(boolean isPlayer, Skill.TIMING timing) {
        try {
            BattleModel battle = ApplicationModel.getBattle();
            CharacterModel currentCharacter = battle.getCurrentUserCharacter();
            CharacterModel enemy = battle.getEnemy();
            CharacterModel offence = currentCharacter;
            CharacterModel defence = currentCharacter;
            if (!isPlayer) {
                offence = enemy;
                defence = currentCharacter;
            }
            if (!offence.isSkillActive()) {
                return null;
            }
            ActionResult result = executeSkillLogic(
                    offence.getBattleStatus(Character.STATUS_SKILL),
                    timing,
                    offence,
                    defence);
            result.isPlayer = isPlayer;
            return result;
        } catch (RuntimeException e) {
            LogUtil.printStackTrace(e);
            return null;
        }
    }

    /**
     * 攻撃実行
     */
    public ActionResult attack(boolean isPlayer) {
        BattleModel battle = ApplicationModel.getBattle();
        CharacterModel currentCharacter = battle.getCurrentUserCharacter();
        CharacterModel enemy = battle.getEnemy();
        if (isPlayer) {
            return attack(currentCharacter, enemy);
        }
        return attack(enemy, currentCharacter);
    }

    /**
     * 継続ターン処理
     */
    public void turnCount(CharacterModel character) {
        try {
            for (Map.Entry<String, Integer> entry : character.getBattleStatus().getSkillCounts().entrySet()) {
                entry.setValue(entry.getValue() - 1);
                if (entry.getValue() < 0) {
                    entry.setValue(0);
                }
                if (entry.getValue() != 0) {
                    character.updateBattleStatus(entry.getKey(), character.getStatus(entry.getKey()));
                }
            }
        } catch (RuntimeException e) {
            LogUtil.printStackTrace(e);
        }
    }

    /**
     * 使用アイテム設定
     */
    public void setUseItem(int index) {
        try {
            BattleModel battle = ApplicationModel.getBattle();
            UserModel user = ApplicationModel.getUser();
            List<ItemModel> list = user.getItemList();
            battle.setUseItem(list.get(index));
        } catch (RuntimeException e) {
            LogUtil.printStackTrace(e);
            return;
        }
    }

    /**
     * 使用アイテム設定(未使用)
     */
    public void setUseItem() {
        try {
            BattleModel battle = ApplicationModel.getBattle();
            battle.setUseItem(null);
        } catch (RuntimeException e) {
            LogUtil.printStackTrace(e);
        }
    }

    /**
     * アイテム使用
     */
    public ActionResult activateItem() {
        try {
            return new ActionResult();
        } catch (RuntimeException e) {
            LogUtil.printStackTrace(e);
            return null;
        }
    }

    /**
     * 攻撃実行(本処理)
     *
     * @param offence
     * @param defence
     */
    private ActionResult attack(CharacterModel offence, CharacterModel defence) {
        try {
            ActionResult result = new ActionResult();
            int damage = calcByElement(
                    offence.getBattleStatus(Character.STATUS_ATK),
                    offence.getBattleStatus(Character.STATUS_TYPE),
                    defence.getBattleStatus(Character.STATUS_TYPE));
            defence.damage(damage, result);
            if(result.killed){
                result.finishBattle = isFinishBattle();
            }
            return result;
        } catch (RuntimeException e) {
            LogUtil.printStackTrace(e);
            return null;
        }
    }

    /**
     * スキル処理実行
     *
     * @param skillId
     * @param timing
     * @param offence
     * @param defence
     * @return
     */
    private ActionResult executeSkillLogic(
            int skillId,
            Skill.TIMING timing,
            CharacterModel offence,
            CharacterModel defence) {
        SkillLibrary library = new SkillLibrary();
        if (skillId == 72) {
            if (timing != Skill.TIMING.Attack) {
                return null;
            }
            return library.getSkillResult72(offence, defence);
        }
        if (skillId == 71) {
            if (timing != Skill.TIMING.TurnEnd) {
                return null;
            }
            ActionResult result = library.getSkillResult71(defence);
            if(result.killed){
                result.finishBattle = isFinishBattle();
            }
            return library.getSkillResult72(offence, defence);
        }
        if (skillId == 56) {
            if (timing != Skill.TIMING.Attack) {
                return null;
            }
            return library.getSkillResult56(defence);
        }
        return null;
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
     * @return
     */
    private boolean isFinishBattle(){
        UserModel user = ApplicationModel.getUser();
        return user.getParty().existBattleCharacter();
    }
    //endregion
}
