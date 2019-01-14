package library;

import constant.Battle;
import constant.Character;
import constant.Status;
import entity.ActionResult;
import model.CharacterModel;
import util.MathUtil;

public class SkillLibrary {

    //region メソッド

    /**
     * スキル番号72
     *
     * @param offence
     * @param defence
     * @return
     */
    public ActionResult getSkillResult72(CharacterModel offence, CharacterModel defence) {
        ActionResult result = new ActionResult();
        int atk = offence.getBattleStatus(Character.STATUS_ATK);
        offence.updateBattleStatus(Character.STATUS_ATK, atk * 2);
        offence.setSkillCount(Character.STATUS_ATK, 1);
        result.skillName = "邪悪な炎";
        result.battleInfo = "攻撃力が増加した！";
        result.effectType.add(Battle.EFFECT_TYPE.UP);
        return result;
    }

    /**
     * スキル番号71
     *
     * @param defence
     * @return
     */
    public ActionResult getSkillResult71(CharacterModel defence) {
        ActionResult result = new ActionResult();
        if (!awakable(25)) {
            return result;
        }
        defence.damage(defence.getBattleStatus(Character.STATUS_ATK), result);
        result.skillName = "死のメッセージ";
        result.battleInfo = String.valueOf(result.damageValue) + "のダメージ！";
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
