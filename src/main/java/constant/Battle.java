package constant;

import android.graphics.Color;

import java.util.ArrayList;

public class Battle {
    public static final String PLAYER_ATK_INFO_TEXT = "ATK：%s";
    public static final String PLAYER_HP_INFO_TEXT = "HP：%s";
    public static final String PLAYER_TYPE_INFO_TEXT = "属性：%s";
    public static final String ENEMY_HP_INFO_TEXT = "HP：%s";

    public static final int SELECTED_ITEM_INDEX_DEFAULT = 0;
    public static final int SKILL_SEALED_FLG = 1;
    public static final int CHARACTER_PLAYABLE_FLG = 1;
    public static final int CHARACTER_INVINCIBLE_FLG = 1;

    public enum Timing {
        TURN_START, ATTACK, DEFENSE, TURN_END

    }

    public enum EFFECT_TYPE {
        BLOW(android.graphics.Color.RED),
        SLASH(Color.RED),
        FIRE(Color.RED),
        WATER(Color.BLUE),
        WIND(Color.GREEN),
        LAND(Color.RED),
        DARK(AppColor.INDIGO),
        LIGHT(Color.RED),
        UP(AppColor.ORANGE),
        DOWN(AppColor.NAVY),
        SEALED(AppColor.DEEPSKYBLUE),
        NONE(Color.BLACK);

        private final int color;

        EFFECT_TYPE(final int color) {
            this.color = color;
        }

        public int getColor() {
            return this.color;
        }
    }

    public static EFFECT_TYPE convertTypeNum(int type){
        ArrayList<EFFECT_TYPE> list = new ArrayList() {
            {
                add(EFFECT_TYPE.NONE);
                add(EFFECT_TYPE.FIRE);
                add(EFFECT_TYPE.WATER);
                add(EFFECT_TYPE.WIND);
                add(EFFECT_TYPE.LAND);
            }
        };
        return list.get(type);
    }
}
