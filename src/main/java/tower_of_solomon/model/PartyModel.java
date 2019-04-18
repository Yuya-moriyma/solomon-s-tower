package tower_of_solomon.model;

import java.util.List;

import tower_of_solomon.constant.Character;

public class PartyModel {

    //region フィールド
    /**
     * キャラクターリスト
     */
    private List<CharacterModel> characters;
    //endregion

    //region コンストラクタ
    /**
     * コンストラクタ
     * @param characters
     */
    public PartyModel (List<CharacterModel> characters) {
        this.characters = characters;
    }
    //endregion

    //region アクセサー
    public List<CharacterModel> getCharcter() {
        return this.characters;
    }
    //endregion

    //region メソッド
    /**
     * 戦闘前にステータスの複製を作る
     */
    public void ready() {
        for (CharacterModel chara : this.characters) {
            chara.createBattleStatus();
        }
    }

    /**
     * 戦闘前にステータスの複製を作る
     */
    public CharacterModel getFirstCharacter( ){
        return characters.get(0);
    }

    /**
     * 戦えるキャラクターが居るかどうか判定
     * @return
     */
    public boolean existBattleCharacter(){
        for(CharacterModel character : characters){
            if(character.getBattleStatus(Character.STATUS_HP) > 0){
                return true;
            }
        }
        return false;
    }
    //endregion
}
