package tower_of_solomon.library;

import android.support.annotation.Nullable;

import tower_of_solomon.constant.Battle;
import tower_of_solomon.constant.Skill;
import tower_of_solomon.constant.Status;
import tower_of_solomon.entity.ActionResult;
import tower_of_solomon.entity.CharacterEntity;
import tower_of_solomon.model.BattleModel;
import tower_of_solomon.model.CharacterModel;
import util.MathUtil;

import static tower_of_solomon.constant.Battle.CHARACTER_INVINCIBLE_FLG;
import static tower_of_solomon.constant.Battle.CHARACTER_PLAYABLE_FLG;

public class SkillLibrary {

    //region メソッド

    public ActionResult activateSkill(
            CharacterEntity offense,
            CharacterEntity defense,
            Skill.Timing timing
    ) {
        switch (offense.getInt("id")) {
            case 72:
                return getSkillResult72(offense, timing);
            case 71:
                return getSkillResult71(offense, defense, timing);
            case 70:
                return getSkillResult70(offense, timing);
        }
        return null;
    }

    /**
     * スキル番号72
     *
     * @return ActionResult
     */
    @Nullable
    private ActionResult getSkillResult72(CharacterEntity offense, Skill.Timing timing) {
        if (!(timing == Skill.Timing.PLAYER_ATTACK_SKILL || timing == Skill.Timing.ENEMY_ATTACK_SKILL)) {
            return null;
        }
        if (offense.getInt("sealed") == Battle.SKILL_SEALED_FLG) {
            return null;
        }
        ActionResult result = new ActionResult();
        offense.putValue("atk", offense.getInt("atk") * 2);
        BattleModel battle = new BattleModel();
        battle.updateStatus(
                offense.getInt("playable") == CHARACTER_PLAYABLE_FLG,
                "atk",
                offense.getInt("atk")
        );
        //TODO:ターン制御データベース更新
        result.skillName = "邪悪な炎";
        result.battleInfo = "攻撃力が増加した！";
        result.effectType = Battle.EFFECT_TYPE.UP;
        return result;
    }

    /**
     * スキル番号71
     *
     * @param defense
     * @return
     */
    private ActionResult getSkillResult71(CharacterEntity offense, CharacterEntity defense, Skill.Timing timing) {
        if (!(timing == Skill.Timing.PLAYER_ATTACK_SKILL || timing == Skill.Timing.ENEMY_ATTACK_SKILL)) {
            return null;
        }
        if (offense.getInt("sealed") == Battle.SKILL_SEALED_FLG) {
            return null;
        }
        ActionResult result = new ActionResult();
        if (!awakable(25)) {
            result.skillName = "死のメッセージ";
            result.battleInfo = "発動失敗";
            result.effectType = Battle.EFFECT_TYPE.NONE;
            return result;
        }
        defense.putValue("atk", defense.getInt("atk") - 3);
        BattleModel battle = new BattleModel();
        battle.updateStatus(
                defense.getInt("playable") == CHARACTER_PLAYABLE_FLG,
                "atk",
                defense.getInt("atk")
        );
        result.skillName = "死のメッセージ";
        result.battleInfo = String.format("力を奪われる...", 3);
        result.effectType = Battle.EFFECT_TYPE.DARK;
        return result;
    }

    private ActionResult getSkillResult70(CharacterEntity offense, Skill.Timing timing) {
        if (!(timing == Skill.Timing.PLAYER_DAMAGED_SKILL || timing == Skill.Timing.ENEMY_DAMAGED)) {
            return null;
        }
        if (offense.getInt("sealed") == Battle.SKILL_SEALED_FLG) {
            return null;
        }
        ActionResult result = new ActionResult();
        if (!awakable(20)) {
            result.skillName = "聖なる結界";
            result.battleInfo = "発動失敗";
            return result;
        }
        BattleModel battle = new BattleModel();
        battle.updateStatus(
                offense.getInt("playable") == CHARACTER_PLAYABLE_FLG,
                "invincible",
                CHARACTER_INVINCIBLE_FLG
        );
        //TODO:ターン制御データベース更新
        result.skillName = "死のメッセージ";
        result.battleInfo = String.format("%dのダメージ！", 10);
        result.effectType = Battle.EFFECT_TYPE.DARK;
        return result;
    }

    /**
     * スキル番号56
     *
     * @param defence
     * @return
     */
    public ActionResult getSkillResult56(CharacterModel defence) {
        ActionResult result = new ActionResult();
//        if (!awakable(50)) {
//            return result;
//        }
        defence.setSkillCount(Status.STATUS_SKILL_AWAKABLE, 1);
        result.skillName = "霧の誘惑";
        result.effectType = Battle.EFFECT_TYPE.SEALED;
        result.battleInfo = defence.getName() + "はスキルが封印された！";
        return result;
    }

    /**
     * スキル発動判定
     *
     * @return
     */
    private boolean awakable(int probability) {
        int num = MathUtil.nextInt(100);
        return num < probability;
    }
    //endregion
}
