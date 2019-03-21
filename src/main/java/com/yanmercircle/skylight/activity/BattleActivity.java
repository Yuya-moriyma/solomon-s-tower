package com.yanmercircle.skylight.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.yanmercircle.skylight.R;
import com.yanmercircle.skylight.fragment.BaseFragment;
import com.yanmercircle.skylight.fragment.BattleReadyFragment;

import java.util.ArrayList;
import java.util.List;

import constant.Battle;
import entity.ActionResult;
import entity.CharacterEntity;
import entity.TransitionParam;
import service.BattleService;
import service.CharacterService;
import service.UserService;

import static constant.Character.*;

public class BattleActivity extends BaseActivity {

    //region メソッド
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserService userService = new UserService();
        int userCharacterId = userService.getFirstCharacter();
        int enemyId = userService.getNextEnemyId();
        CharacterService characterService = new CharacterService();
//        CharacterEntity character = characterService.findById(userCharacterId);
//        CharacterEntity enemy = characterService.findById(enemyId);
        CharacterEntity player = characterService.findById(72);
        CharacterEntity enemy = characterService.findById(71);
        BattleService battle = new BattleService();
        battle.init(player, enemy);
        initView();
        TransitionParam param = new TransitionParam();
        param.put("floor", nextFloor).put("name", enemy.getString("name"));
        BaseFragment fragment = BattleReadyFragment.newInstance(param);
        showFragment(R.id.dialog, fragment, BATTLE_READY_FRAGMENT_TAG);
    }

    @Override
    public TransitionParam setParam() {
        return null;
    }
    //endregion

    public void onAttack(View view) {
        BattleService battle = new BattleService();
        ArrayList<ActionResult> result = battle.actionTurn();
        setCharacterView();
        //選択中のアイテム取得
        //アイテム効果発動
        //味方攻撃時スキル発動
        //敵防御時スキル発動
        //攻撃処理
        //アニメーション
        ImageView imageView = findViewById(R.id.enemy_image);
        AlphaAnimation anim = new AlphaAnimation( 1, 0 );
        anim.setDuration(50);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(6);
        imageView.startAnimation(anim);
    }

    public void onChange(View view) {

    }

    private void initView() {
        BattleService battle = new BattleService();
        CharacterEntity player = battle.getStatus(true);
        CharacterEntity enemy = battle.getStatus(false);
        ProgressBar playerHp = findViewById(R.id.bar_enemy_hp);
        playerHp.setMax(player.getInt("hp"));
        ProgressBar enemyHp = findViewById(R.id.bar_player_hp);
        enemyHp.setMax(enemy.getInt("hp"));
        changeImage(R.id.enemy_image, IMAGE_ID_LIST.get(enemy.getInt("id")));
        changeImage(R.id.player_icon, ICON_ID_LIST.get(player.getInt("id")));
    }

    private void setCharacterView() {
        BattleService battle = new BattleService();
        CharacterEntity player = battle.getStatus(true);
        CharacterEntity enemy = battle.getStatus(false);
        ProgressBar playerHp = findViewById(R.id.bar_enemy_hp);
        playerHp.setProgress(player.getInt("hp"));
        ProgressBar enemyHp = findViewById(R.id.bar_player_hp);
        enemyHp.setProgress(enemy.getInt("hp"));
    }
}
