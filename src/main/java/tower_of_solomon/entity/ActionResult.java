package tower_of_solomon.entity;

import tower_of_solomon.constant.Battle;

public class ActionResult {

    //region フィールド
    public boolean finishBattle;
    public int damageValue;
    public String skillName;
    public Battle.EFFECT_TYPE effectType;
    public String battleInfo;
    public CharacterEntity player;
    public CharacterEntity enemy;
    //endregion

    //region コンストラクタ
    public ActionResult(){
        this.damageValue = 0;
        this.skillName = "";
        this.battleInfo = "";
        this.effectType = Battle.EFFECT_TYPE.NONE;
        this.finishBattle = false;
    }
    //endregion
}
