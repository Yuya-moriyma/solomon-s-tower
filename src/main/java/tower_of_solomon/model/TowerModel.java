package tower_of_solomon.model;

import java.util.ArrayList;
import java.util.List;

public class TowerModel {

    //region フィールド

    private List<FloorModel> floors;

    //endregion

    //region コンストラクタ

    /**
     * コンストラクタ
     * @param floorCharacters
     */
    public TowerModel(List<CharacterModel> floorCharacters) {
        this.floors = new ArrayList<>();
        int i = 0;
        for(CharacterModel devil : floorCharacters) {
            floors.add(new FloorModel(++i, devil));
        }
    }

    //endregion

    //region メソッド

    /**
     * 次の階層の情報を取得する。
     * @param currentFloor
     * @return
     */
    public FloorModel getNextFloor(int currentFloor) {
        FloorModel floorInfo = null;
        for(FloorModel floor : floors) {
            if(floor.getNum() == currentFloor) {
                floorInfo = floor;
                break;
            }
        }
        return floorInfo;
    }

    //endregion

    //region アクセサー
    //endregion


}
