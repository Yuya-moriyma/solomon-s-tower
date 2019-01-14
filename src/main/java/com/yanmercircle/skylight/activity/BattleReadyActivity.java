package com.yanmercircle.skylight.activity;

import android.os.Bundle;
import android.view.View;

import com.yanmercircle.skylight.R;

import constant.Battle;
import constant.BattleReady;
import constant.Character;
import model.ApplicationModel;
import model.CharacterModel;
import model.FloorModel;
import model.UserModel;
import service.*;

public class BattleReadyActivity extends BaseActivity {

    //region 初期化処理
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_ready);
        this.indexAction();
    }
    //endregion

    //region 画面初期化処理

    /**
     * 画面初期化処理
     */
    private void indexAction() {
        UserService user = new UserService();
        int currentFloor = user.getCurrentFloor();
        int currentPoint = user.getCurrentPoint();
        FloorModel nextFloor = user.getTower().getNextFloor(currentFloor);
        int nextEnemyId = nextFloor.getDevil().getStatus(Character.STATUS_ID);
        String enemyName = Character.UNKNOWN_ENEMY_NAME;
        if (user.rememberEnemy(nextEnemyId)) {
            CharacterService character = new CharacterService();
            enemyName = character.getCharacterName(nextEnemyId);
        }
        changeText(R.id.current_floor, String.format(BattleReady.CURRENT_FLOOR_TEXT, currentFloor));
        changeText(R.id.current_point, String.format(BattleReady.CURRENT_POINT_TEXT, currentPoint));
        changeText(R.id.next_enemy_info, String.format(BattleReady.NEXT_ENEMY_TEXT, enemyName));
    }
    //endregion

    //region 画面遷移

    @Override
    public void onTransition(View view) {
        UserModel user = ApplicationModel.getUser();
        int currentFloor = user.getCurrentFloor();
        FloorModel nextFloor = user.getTower().getNextFloor(currentFloor);
        super.values.add(String.valueOf(nextFloor.getDevil().getStatus(Character.STATUS_ID)));
        super.onTransition(view);
    }
    //endregion
}
