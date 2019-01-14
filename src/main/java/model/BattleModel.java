package model;

public class BattleModel {

    //region フィールド
    private PartyModel userParty;
    private CharacterModel enemy;
    private CharacterModel currentUserCharacter;
    private ItemModel useItem;
    //endregion

    //region コンストラクタ
    public BattleModel(PartyModel userParty, CharacterModel enemy) {
        this.setUserParty(userParty);
        this.setEnemy(enemy);
    }
    //endregion

    //region メソッド
    public void ready() {
        userParty.ready();
        this.enemy.createBattleStatus();
        this.currentUserCharacter = userParty.getFirstCharacter();
    }
    //endregion

    //region アクセサー
    public PartyModel getUserParty() {
        return userParty;
    }

    public void setUserParty(PartyModel userParty) {
        this.userParty = userParty;
    }

    public CharacterModel getEnemy() {
        return enemy;
    }

    public void setEnemy(CharacterModel enemy) {
        this.enemy = enemy;
    }

    public CharacterModel getCurrentUserCharacter() {
        return currentUserCharacter;
    }

    public void setUseItem(ItemModel useItem) {
        this.useItem = useItem;
    }
    //endregion
}
