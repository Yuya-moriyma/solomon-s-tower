package tower_of_solomon.registory;

import tower_of_solomon.constant.Database;
import tower_of_solomon.model.ItemModel;

public class ItemRegistry extends BaseRegistry{
    //region コンストラクタ
    /**
     * コンストラクタ
     */
    public ItemRegistry() {
        super(Database.TABLE_NAME_ITEM);
    }
    //endregion

    //region メソッド
    /**
     * キャラクターデータ取得
     * @param id
     * @return
     */
    public ItemModel getItem(String id) {
        int idValue = Integer.parseInt(id);
        //TODO:idに一致するアイテムを取得
        if(idValue == 1) {
            return new ItemModel(idValue, "アイテム１");
        }
        if(idValue == 2) {
            return new ItemModel(idValue, "アイテム2");
        }
        if(idValue == 3) {
            return new ItemModel(idValue, "アイテム3");
        }
        //helper.testInsert(db);
        return new ItemModel(idValue, "テスト");
    }
    //endregion
}
