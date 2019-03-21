package registory;

import java.util.ArrayList;
import java.util.List;

import constant.Database;
import model.CharacterModel;
import model.StatusModel;

public class CharacterRegistry extends BaseRegistry {

    //region コンストラクタ

    /**
     * コンストラクタ
     */
    public CharacterRegistry() {
        super(Database.TABLE_NAME_CHARACTER);
    }

    //endregion

    //region メソッド

    /**
     * キャラクターデータ取得
     * @param id
     * @return
     */
    public CharacterModel createCharacter(int id) {
        //TODO:idに一致するキャラクターのステータス情報を取得
        if (id == 72) {
            String[] status = {"アンドロマリウス", String.valueOf(id), "1", "20", "5", "72"};
            //return new CharacterModel(new StatusModel(status));
        }
        if (id == 71) {
            String[] status = {"ダンタリオン", String.valueOf(id), "2", "23", "8", "71"};
//            return new CharacterModel(new StatusModel(status));
        }
        if (id == 56) {
            String[] status = {"グレモリー", String.valueOf(id), "2", "46", "20", "56"};
//            return new CharacterModel(new StatusModel(status));
        }
        if (id == 30) {
            String[] status = {"ダンタリオン", String.valueOf(id), "2", "23", "8", "71"};
//            return new CharacterModel(new StatusModel(status));
        }
        //helper.testInsert(db);
        String[] status = {"enemy", String.valueOf(id), "0", "9", "9", "0"};
        return null;
    }

    /**
     * キャラクターリスト取得
     * @param ids
     * @return
     */
    public List<CharacterModel> createCharacters(List<String> ids) {
        List<CharacterModel> list = new ArrayList<CharacterModel>();
        for(String id : ids) {
            list.add(createCharacter(Integer.parseInt(id)));
        }
        return list;
    }

    //endregion
}
