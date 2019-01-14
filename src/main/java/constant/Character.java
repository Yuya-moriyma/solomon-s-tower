package constant;

import java.util.HashMap;

public class Character {
    public static final int MODEL_TYPE_ENEMY = 0;
    public static final int MODEL_TYPE_PLAYABLE = 1;

    public static final String UNKNOWN_ENEMY_NAME = "????";
    public static final String STATUS_NAME = "name";
    public static final String STATUS_ID = "id";
    public static final String STATUS_TYPE = "type";
    public static final String STATUS_HP_MAX = "hp";
    public static final String STATUS_HP = "hp";
    public static final String STATUS_ATK = "atk";
    public static final String STATUS_SKILL = "skill";
    public static final String CHARACTER_STATUS_IMAGE = "image";
    public static final String CHARACTER_STATUS_ICON = "icon";

    public static final int TYPE_NONE = 0;
    public static final int TYPE_FIRE = 1;
    public static final int TYPE_WATER = 2;
    public static final int TYPE_WIND = 3;
    public static final int TYPE_LAND = 4;
    public static final int TYPE_LIGHT = 5;
    public static final int TYPE_DARK = 6;


    public static final HashMap<Integer, String> STATUS_TYPE_NAME = new HashMap<Integer, String>(){
        {
            put(0, "無");
            put(1, "火");
            put(2, "水");
            put(3, "風");
            put(4, "土");
            put(5, "光");
            put(6, "闇");
        }
    };
}
