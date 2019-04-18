package tower_of_solomon.activity;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import tower_of_solomon.R;
import tower_of_solomon.fragment.BattleSplashFragment;

import java.util.ArrayList;
import java.util.HashMap;

import tower_of_solomon.constant.Battle;
import tower_of_solomon.entity.ActionResult;
import tower_of_solomon.entity.CharacterEntity;
import tower_of_solomon.entity.TransitionParam;
import tower_of_solomon.service.BattleService;
import tower_of_solomon.service.CharacterService;
import tower_of_solomon.service.UserService;
import util.AnimUtil;

import static tower_of_solomon.constant.Character.*;

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
        int nextFloor = userService.getNextFloor();
        TransitionParam param = new TransitionParam();
        param.put("floor", nextFloor).put("name", enemy.getString("name"));
        showFragment(R.id.dialog, BattleSplashFragment.newInstance(param), "");
    }

    @Override
    public TransitionParam setParam() {
        return null;
    }
    //endregion

    public void onAttack(View view) {
        BattleService battle = new BattleService();
        HashMap resultList = battle.actionTurn();
        //選択中のアイテム取得
        //アイテム効果発動
        //味方攻撃時スキル発動
        //敵防御時スキル発動
        //攻撃処理
        //アニメーション
        animateAttack(resultList);
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

    private void animateAttack(HashMap<String, ActionResult> list) {
        ActionResult playerAttackSkill = list.get("playerAttackSkill");
        ActionResult enemyAttackSkill = list.get("enemyAttackSkill");
        ActionResult playerAttack = list.get("playerAttack");
        ActionResult enemyAttack = list.get("enemyAttack");
        ArrayList<Animator> animatorList = new ArrayList<>();
        //味方攻撃時スキル
        animatorList.addAll(createAttackSkillAnimator(playerAttackSkill, true));
        //終了判定
        if (playerAttackSkill.finishBattle) {
            //TODO:バトル終了アニメーション
            animatorList.add(AnimUtil.createDamageFlash(findViewById(R.id.enemy_image), new Runnable() {
                @Override
                public void run() {
                    setCharacterView(false);
                }
            }));
            AnimUtil.permutationExecute(animatorList);
            return;
        }
        //味方攻撃
        animatorList.addAll(createAttackAnimator(playerAttack, true));
        if (playerAttack.finishBattle) {
            //TODO:バトル終了アニメーション
            AnimUtil.permutationExecute(animatorList);
            return;
        }
        //敵攻撃時スキル
        animatorList.addAll(createAttackSkillAnimator(enemyAttackSkill, false));
        //終了判定
        if (enemyAttackSkill.finishBattle) {
            //TODO:バトル終了アニメーション
            AnimUtil.permutationExecute(animatorList);
            return;
        }
        //敵攻撃
        animatorList.addAll(createAttackAnimator(enemyAttack, false));
        if (enemyAttack.finishBattle) {
            //TODO:バトル終了アニメーション
            AnimUtil.permutationExecute(animatorList);
            return;
        }
        AnimUtil.permutationExecute(animatorList);
    }

    private ArrayList<Animator> createAttackAnimator(ActionResult result, boolean playable) {
        ArrayList<Animator> animatorList = new ArrayList<>();
        final ActionResult _result = result;
        final boolean _playable = playable;
        animatorList.add(
                AnimUtil.createFadeIn(findViewById(R.id.area_skill_name), new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = findViewById(R.id.text_skill_name);
                        textView.setText("攻撃");
                    }
                })
        );
        if (_result.effectType != Battle.EFFECT_TYPE.NONE) {
            animatorList.add(
                    AnimUtil.createColorEffect(
                            findViewById(R.id.effect),
                            _result.effectType.getColor(),
                            null
                    )
            );
        }
        animatorList.add(
                AnimUtil.createDamageFlash(findViewById(_playable ? R.id.enemy_image : R.id.player_icon), new Runnable() {
                    @Override
                    public void run() {
                        setCharacterView(!_playable);
                    }
                })
        );
        return animatorList;
    }

    private ArrayList<Animator> createAttackSkillAnimator(ActionResult result, boolean playable) {
        ArrayList<Animator> animatorList = new ArrayList<>();
        final ActionResult _result = result;
        final boolean _playable = playable;
        animatorList.add(
                AnimUtil.createFadeIn(findViewById(R.id.area_skill_name), new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = findViewById(R.id.text_skill_name);
                        textView.setText(_result.skillName);
                    }
                })
        );
        if (_result.effectType != Battle.EFFECT_TYPE.NONE) {
            animatorList.add(
                    AnimUtil.createColorEffect(
                            findViewById(R.id.effect),
                            _result.effectType.getColor(),
                            null)
            );
        }
        if (!empty(_result.damageValue)) {
            animatorList.add(
                    AnimUtil.createDamageFlash(findViewById(_playable ? R.id.enemy_image : R.id.player_icon), new Runnable() {
                        @Override
                        public void run() {
                            setCharacterView(!_playable);
                        }
                    })
            );
        }
        animatorList.add(
                AnimUtil.createFadeIn(findViewById(R.id.area_battle_info), new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = findViewById(R.id.text_battle_info);
                        textView.setText(_result.battleInfo);
                    }
                })
        );
        return animatorList;
    }

    private void setCharacterView(boolean playable) {
        BattleService battle = new BattleService();
        if (playable) {
            ProgressBar playerHp = findViewById(R.id.bar_player_hp);
            CharacterEntity player = battle.getStatus(true);
            playerHp.setProgress(player.getInt("hp"));
            return;
        }
        ProgressBar enemyHp = findViewById(R.id.bar_enemy_hp);
        CharacterEntity enemy = battle.getStatus(false);
        enemyHp.setProgress(enemy.getInt("hp"));
    }

    private void finishBattle() {

    }
}
