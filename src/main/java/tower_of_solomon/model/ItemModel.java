package tower_of_solomon.model;

public class ItemModel {

    //region フィールド
    private int id;
    private String name;
    //endregion

    //region コンストラクタ
    /**
     * コンストラクタ
     * @param name
     * @param id
     */
    public ItemModel(int id, String name) {
        this.setId(id);
        this.setName(name);
    }
    //endregion

    //region アクセサー
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion
}
