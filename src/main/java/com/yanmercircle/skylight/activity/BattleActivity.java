package com.yanmercircle.skylight.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yanmercircle.skylight.R;

import constant.Battle;
import constant.Character;
import constant.Skill;
import entity.ActionResult;
import entity.ButtonItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import service.BattleService;
import service.CharacterService;
import service.UserService;
import util.AnimUtil;
import util.StringUtil;

public class BattleActivity extends BaseActivity {

    //region メソッド
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        this.init();
    }

    /**
     * 初期化処理
     */
    private void init() {
        //データ取得
        Intent intent = getIntent();
        String id = intent.getStringExtra("0");
        //バトル準備
        BattleService battle = new BattleService();
        battle.init(Integer.parseInt(id));
        //自キャラクター表示設定
        setPlayerView();
        //敵キャラクター表示設定
        setEnemyView();
        //アイテム設定
        UserService user = new UserService();
        ArrayList itemList = user.getItemList();
        setSpinner(R.id.spinner_item_list, itemList, new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ButtonItem item = (ButtonItem) parent.getSelectedItem();
                BattleService battle = new BattleService();
                battle.setUseItem(item.index);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                BattleService battle = new BattleService();
                battle.setUseItem(Battle.SELECTED_ITEM_INDEX_DEFAULT);
            }
        });
        readyBattle();
    }

    /**
     * 戦闘開始準備
     */
    public void readyBattle() {
        BattleService battle = new BattleService();
        ActionResult result = battle.activateSkill(true, Skill.TIMING.BattleStart);
        setPlayerView(result);
        setEnemyView();
        result = battle.activateSkill(false, Skill.TIMING.BattleStart);
        setEnemyView(result);
        setPlayerView();
    }

    /**
     * 自キャラクター表示設定(追加情報あり)
     */
    private void setPlayerView(ActionResult result) {
        setPlayerView();
        if (result == null) {
            return;
        }
    }

    /**
     * 敵キャラクター表示設定(追加情報あり)
     */
    private void setEnemyView(ActionResult result) {
        setEnemyView();
        if (result == null) {
            return;
        }
    }


    /**
     * 自キャラクター表示設定
     */
    private void setPlayerView() {
        CharacterService character = new CharacterService();
        HashMap<String, String> statusViewData = character.getFirstCharacterStatus();
        int iconId = character.getIconId(Integer.parseInt(statusViewData.get(Character.STATUS_ID)));
        ProgressBar hp = findViewById(R.id.bar_player_hp);
        hp.setMax(Integer.valueOf(statusViewData.get(Character.STATUS_HP_MAX)));
        hp.setProgress(Integer.valueOf(statusViewData.get(Character.STATUS_HP)));
        ImageView imageView = findViewById(R.id.player_icon);
        imageView.setImageResource(iconId);
    }

    /**
     * 敵キャラクター表示設定
     */
    private void setEnemyView() {
        CharacterService character = new CharacterService();
        HashMap<String, String> statusViewData = character.getEnemyCharacterStatus();
        int imageId = character.getImageId(
                Integer.parseInt(statusViewData.get(Character.STATUS_ID)));
        ImageView enemyImage = findViewById(R.id.enemy_image);
        enemyImage.setImageResource(imageId);
    }

    /**
     * 攻撃アクション
     *
     * @param view
     */
    public void attackAction(View view) {
        BattleService battle = new BattleService();
        ArrayList<ActionResult> list = new ArrayList();
        //ターン開始
        list.add(battle.activateItem());
        list.add(battle.activateSkill(true, Skill.TIMING.TurnStart));
        list.add(battle.activateSkill(false, Skill.TIMING.TurnStart));
        //プレイヤー攻撃
        list.add(battle.activateSkill(true, Skill.TIMING.Attack));
        list.add(battle.activateSkill(false, Skill.TIMING.Defence));
        list.add(battle.attack(true));
        //敵攻撃
        list.add(battle.activateSkill(true, Skill.TIMING.Defence));
        list.add(battle.attack(false));
        list.add(battle.activateSkill(false, Skill.TIMING.Attack));
        list.removeAll(Collections.singleton(null));
        startAnimationSequential(list);
    }

    /**
     * アクション結果描画
     *
     * @param list
     */
    private void startAnimationSequential(ArrayList<ActionResult> list) {
        FrameLayout enemyImageArea = findViewById(R.id.area_enemy_image);
        ImageView enemyDetailButton = findViewById(R.id.button_enemy_detail);
        ArrayList<Animator> animList = new ArrayList();
        animList.add(AnimUtil.fadeOut(enemyDetailButton));
        for (ActionResult result : list) {
            if(StringUtil.isNullOrEmpty(result.skillName)){
                readySkillAnim(result, (FrameLayout)findViewById(R.id.area_skill_info), animList);
            }
            if(StringUtil.isNullOrEmpty(result.battleInfo)){
                readyBattleInfoAnim(result, (FrameLayout)findViewById(R.id.area_battle_info), animList);
            }
        }
        AnimatorSet animSequence = new AnimatorSet();
        animSequence.playSequentially(animList);
        animSequence.start();
    }

    /**
     * スキルアニメーション設定
     * @param result
     * @param skillInfoArea
     * @param animList
     * @return
     */
    public void readySkillAnim(ActionResult result, FrameLayout skillInfoArea, ArrayList<Animator> animList){
        TextView view = new TextView(this);
        if(result.isPlayer){
            view.setBackground(getDrawable(R.drawable.frame_skill_info_player));
        }else {
            view.setBackground(getDrawable(R.drawable.frame_skill_info_enemy));
        }
        view.setText(result.skillName);
        view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        view.setLayoutParams(lp);
        view.setAlpha(0);
        skillInfoArea.addView(view);
        animList.addAll(AnimUtil.popup(view));
    }

    public void readyBattleInfoAnim(ActionResult result, FrameLayout battleInfoArea, ArrayList<Animator> animList) {
        TextView view = new TextView(this);
        view.setBackground(getDrawable(R.drawable.frame_transparent));
        view.setText(result.battleInfo);
        view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        view.setLayoutParams(lp);
        view.setAlpha(0);
        battleInfoArea.addView(view);
        animList.addAll(AnimUtil.popup(view));
    }

        //endregion
}
