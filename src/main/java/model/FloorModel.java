package model;

public class FloorModel {

    //region フィールド

    private int num;
    private CharacterModel devil;

    //endregion

    //region コンストラクタ

    /**
     * コンストラクタ
     * @param num
     * @param devil
     */
    public FloorModel(int num, CharacterModel devil) {
        this.num = num;
        this.devil = devil;
    }

    //endregion

    //region メソッド
    //endregion

    //region アクセサー

    public int getNum() {
        return num;
    }

    public CharacterModel getDevil() {
        return devil;
    }
    //endregion
}
