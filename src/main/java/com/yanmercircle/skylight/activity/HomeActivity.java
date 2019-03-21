package com.yanmercircle.skylight.activity;

import android.os.Bundle;
import android.view.View;

import com.yanmercircle.skylight.R;
import com.yanmercircle.skylight.fragment.BaseFragment;
import com.yanmercircle.skylight.fragment.BattleReadyFragment;

import entity.CharacterEntity;
import entity.TransitionParam;
import service.CharacterService;
import service.UserService;
import util.AnimUtil;

public class HomeActivity extends BaseActivity {

    private final String BATTLE_READY_FRAGMENT_TAG = "battleReady";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    public void showReadyDialog(View view) {
        UserService userService = new UserService();
        int nextEnemyId = userService.getNextEnemyId();
        int nextFloor = userService.getNextFloor();
        CharacterService characterService = new CharacterService();
        CharacterEntity character = characterService.findById(nextEnemyId);
        TransitionParam param = new TransitionParam();
        param.put("nextFloor", nextFloor)
                .put("id", nextEnemyId)
                .put("name", character.getString("name"))
                .put("type", character.getInt("type"))
                .put("skill_detail", character.getString("skill_detail"));
        BaseFragment fragment = BattleReadyFragment.newInstance(param);
        showFragment(R.id.dialog, fragment, BATTLE_READY_FRAGMENT_TAG);
    }

    public void closeDialog(View view) {
        closeFragment(BATTLE_READY_FRAGMENT_TAG);
    }

    public void onTransition(View view) {
        if(view.getId() == R.id.button_move_battle) {

        }
        super.onTransition(view);
    }

    @Override
    public TransitionParam setParam() {
        return null;
    }

    private void init() {
        View[] views = {
                findViewById(R.id.home_title_message),
                findViewById(R.id.button_move_battle_ready),
                findViewById(R.id.button_move_edit),
                findViewById(R.id.button_move_shop),
                findViewById(R.id.button_move_title)
        };
        AnimUtil.fadeInAll(views, null);
    }
}
