package constant;

import java.util.HashMap;
import java.util.Map;

public class Battle {
    public static final String PLAYER_ATK_INFO_TEXT = "ATK：%s";
    public static final String PLAYER_HP_INFO_TEXT = "HP：%s";
    public static final String PLAYER_TYPE_INFO_TEXT = "属性：%s";
    public static final String ENEMY_HP_INFO_TEXT = "HP：%s";

    public static final int SELECTED_ITEM_INDEX_DEFAULT = 0;
    public static final int SKILL_SEALED_FLG = 1;
    public static final int CHARACTER_PLAYABLE_FLG = 1;


    public enum TIMING {
        TURN_START,
        ATTACK,
        DEFENSE,
        TURN_END
    }

    public enum EFFECT_TYPE {
        BLOW,
        SLASH,
        FIRE,
        WATER,
        WIND,
        LAND,
        DARK,
        LIGHT,
        UP,
        DOWN,
        SEALED,
        MISS,
    }
}
