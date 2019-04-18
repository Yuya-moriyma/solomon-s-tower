package tower_of_solomon.constant;

import com.yanmercircle.tower_of_solomon.R;

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


    public static final HashMap<Integer, String> STATUS_TYPE_NAME = new HashMap<Integer, String>() {
        {
            put(0, "不明");
            put(1, "火");
            put(2, "水");
            put(3, "風");
            put(4, "土");
            put(5, "光");
            put(6, "闇");
        }
    };

    public static final HashMap<Integer, Integer> TYPE_IMAGE_LIST = new HashMap<Integer, Integer>() {
        {
            put(0, null);
            put(1, R.drawable.fire);
            put(2, R.drawable.water);
            put(3, R.drawable.wind);
            put(4, R.drawable.land);
        }
    };

    public static final HashMap<Integer, Integer> IMAGE_ID_LIST = new HashMap<Integer, Integer>() {
        {
            put(56, R.drawable.image_56);
            put(57, R.drawable.image_57);
            put(58, R.drawable.image_58);
            put(59, R.drawable.image_59);
            put(60, R.drawable.image_60);
            put(61, R.drawable.image_61);
            put(62, R.drawable.image_62);
            put(63, R.drawable.image_63);
            put(64, R.drawable.image_64);
            put(65, R.drawable.image_65);
            put(66, R.drawable.image_66);
            put(67, R.drawable.image_67);
            put(68, R.drawable.image_68);
            put(69, R.drawable.image_69);
            put(70, R.drawable.image_70);
            put(71, R.drawable.image_71);
            put(72, R.drawable.image_72);
        }
    };

    public static final HashMap<Integer, Integer> ICON_ID_LIST = new HashMap<Integer, Integer>() {
        {
            put(56, R.drawable.image_56);
            put(71, R.drawable.image_71);
            put(72, R.drawable.image_72);
        }
    };
}
