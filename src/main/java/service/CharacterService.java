package service;

import android.database.sqlite.SQLiteCursor;

import com.yanmercircle.skylight.R;

import java.util.HashMap;

import entity.CharacterEntity;
import registory.CharacterRegistry;
import constant.Character;
import model.ApplicationModel;
import model.BattleModel;
import model.CharacterModel;
import util.LogUtil;

public class CharacterService {

    //region メソッド

    public CharacterEntity findById(int id){
        CharacterModel character = new CharacterModel();
        return character.getCharacter(id);
    }

    /**
     * キャラクター名取得
     * @param id
     * @return
     */
    public String getCharacterName(int id){
        try{
            CharacterRegistry registry = new CharacterRegistry();
            CharacterModel model = registry.createCharacter(id);
            return model.getName();
        }
        catch (RuntimeException e) {
            LogUtil.printStackTrace(e);
            return "";
        }
    }

    /**
     * キャラクターアイコン名取得
     * @param id
     * @return
     */
    public int getImageId(int id) {
        if (id == 72) {
            return R.drawable.image_72;
        }
        if (id == 56) {
            return R.drawable.image_56;
        }
        if (id == 30) {
            return 0;
        }
        return 0;
    }

    /**
     * キャラクターイメージ名取得
     * @param id
     * @return
     */
    public int getIconId(int id) {
        if (id == 72) {
            return R.drawable.icon_72;
        }
        if (id == 56) {
            return R.drawable.icon_56;
        }
        if (id == 30) {
            return R.drawable.icon_72;
        }
        return 0;
    }

    /**
     * バトル中のキャラクターステータスを取得
     * @return
     */
    private HashMap<String, String> getStatusViewData(CharacterModel model) {
        HashMap status =  new HashMap<String, String>();
        status.put(Character.STATUS_ID, String.valueOf(model.getStatus(Character.STATUS_ID)));
        status.put(Character.STATUS_NAME, model.getName());
        status.put(Character.STATUS_HP_MAX,
                String.valueOf(model.getStatus(Character.STATUS_HP)));
        status.put(Character.STATUS_HP,
                String.valueOf(model.getBattleStatus(Character.STATUS_HP)));
        status.put(Character.STATUS_ATK,
                String.valueOf(model.getBattleStatus(Character.STATUS_ATK)));
        status.put(Character.STATUS_TYPE,
                Character.STATUS_TYPE_NAME.get(model.getStatus(Character.STATUS_TYPE)));
        return status;
    }

    //endregion
}
