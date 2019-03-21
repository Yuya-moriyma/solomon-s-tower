package library;

import constant.Battle;
import constant.Character;
import constant.Skill;
import constant.Status;
import entity.ActionResult;
import entity.CharacterEntity;
import model.BattleModel;
import model.CharacterModel;
import util.MathUtil;

import static constant.Battle.CHARACTER_PLAYABLE_FLG;

public class SkillLibrary {

    //region メソッド

    public ActionResult activateSkill(
            CharacterEntity offense,
            CharacterEntity defense,
            Skill.TIMING timing
    ) {
        switch (offense.getInt("id")) {
            case 72:
                return getSkillResult72(offense, timing);
            case 71:
                return getSkillResult71(defense, timing);
        }
        return null;
    }

    /**
     * スキル番号72
     *
     * @return ActionResult
     */
    public ActionResult getSkillResult72(CharacterEntity offense, Skill.TIMING timing) {
        if (timing != Skill.TIMING.Attack) {
            return new ActionResult();
        }
        ActionResult result = new ActionResult();
        offense.putValue("atk", offense.getInt("atk") * 2);
        BattleModel battle = new BattleModel();
        battle.updateStatus(
                offense.getInt("playable") == CHARACTER_PLAYABLE_FLG,
                "atk",
                offense.getInt("atk")
        );
        result.skillName = "邪悪な炎";
        result.battleInfo = "攻撃力が増加した！";
        result.effectType.add(Battle.EFFECT_TYPE.UP);
        return result;
    }

    /**
     * スキル番号71
     *
     * @param defense
     * @return
     */
    public ActionResult getSkillResult71(CharacterEntity defense, Skill.TIMING timing) {
        if (timing != Skill.TIMING.Attack) {
            return new ActionResult();
        }
        ActionResult result = new ActionResult();
        if (!awakable(25)) {
            result.skillName = "死のメッセージ";
            result.battleInfo = "発動失敗";
            return result;
        }
        defense.putValue("hp", defense.getInt("hp") - 10);
        BattleModel battle = new BattleModel();
        battle.updateStatus(
                defense.getInt("playable") == CHARACTER_PLAYABLE_FLG,
                "hp",
                defense.getInt("hp")
        );
        result.skillName = "死のメッセージ";
        result.battleInfo = String.format("%dのダメージ！", 10);
        result.effectType.add(Battle.EFFECT_TYPE.DARK);
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
        result.effectType.add(Battle.EFFECT_TYPE.SEALED);
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
