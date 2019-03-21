package entity;

import constant.Battle;
import constant.Character;

import java.util.ArrayList;

public class ActionResult {

    //region フィールド
    public boolean finishBattle;
    public int damageValue;
    public String skillName;
    public ArrayList<Battle.EFFECT_TYPE> effectType;
    public String battleInfo;
    public CharacterEntity player;
    public CharacterEntity enemy;
    //endregion

    //region コンストラクタ
    public ActionResult(){
        this.damageValue = 0;
        this.skillName = "";
        this.battleInfo = "";
        this.effectType = new ArrayList();
    }
    //endregion
}
