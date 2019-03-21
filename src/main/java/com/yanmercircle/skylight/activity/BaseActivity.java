package com.yanmercircle.skylight.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.yanmercircle.skylight.R;
import com.yanmercircle.skylight.fragment.BaseFragment;

import java.util.ArrayList;

import constant.Route;
import entity.ButtonItem;
import entity.ListAdapter;
import entity.TransitionParam;
import util.AnimUtil;

public abstract class BaseActivity extends AppCompatActivity {

    protected Intent intent;

    /**
     * コンストラクタ
     *
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 画面遷移ボタン押下イベント
     *
     * @param view
     */
    protected void onTransition(View view) {
        createIntent(view.getId());
        AnimUtil.fadeOut(findViewById(R.id.base), new Runnable() {
            @Override
            public void run() {
                startActivity();
            }
        });
    }

    /**
     * 画面遷移イベント
     *
     * @param id
     */
    protected void onTransition(int id) {
        createIntent(id);
        AnimUtil.fadeOut(findViewById(R.id.base), new Runnable() {
            @Override
            public void run() {
                startActivity();
            }
        });
    }

    /**
     * 画面遷移
     *
     * @param nextViewId
     */
    protected void createIntent(int nextViewId) {
        //ホーム画面遷移
        if (Route.TRANSITION_ID_TITLE.contains(nextViewId)) {
            intent = new Intent(this, TitleActivity.class);
            return;
        }
        if (Route.TRANSITION_ID_HOME.contains(nextViewId)) {
            intent = new Intent(this, HomeActivity.class);
            return;
        }
        //バトル画面遷移
        if (Route.TRANSITION_ID_BATTLE.contains(nextViewId)) {
            intent = new Intent(this, BattleActivity.class);
            return;
        }
        //バトル結果画面遷移
        if (Route.TRANSITION_ID_RESULT.contains(nextViewId)) {
            intent = new Intent(this, ResultActivity.class);
            return;
        }
        if (intent == null) {
            return;
        }
    }

    /**
     * アクティビティ開始
     */
    private void startActivity() {
        TransitionParam param = setParam();
        if (param != null) {
            intent.putExtra("param", param);
        }
        this.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    /**
     * フラグメント表示
     *
     * @param fragment
     */
    public void showFragment(int id, BaseFragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(id, fragment, tag)
                .commit();
    }

    /**
     * フラグメントを閉じる
     */
    public void closeFragment(String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(getSupportFragmentManager().findFragmentByTag(tag))
                .commit();
    }

    public abstract TransitionParam setParam();

    /**
     * テキスト変更
     *
     * @param id
     * @param text
     */
    protected void changeText(int id, String text) {
        TextView view = findViewById(id);
        view.setText(text);
    }

    /**
     * 画像変更
     *
     * @param id
     * @param imageId
     */
    protected void changeImage(int id, int imageId) {
        ImageView view = findViewById(id);
        view.setImageResource(imageId);
    }

    /**
     * スピナー要素セット
     */
    protected void setSpinner(int id, ArrayList<ButtonItem> list, AdapterView.OnItemSelectedListener listener) {
        ListAdapter adapter = new ListAdapter(this, list);
        Spinner spinner = findViewById(id);
        spinner.setOnItemSelectedListener(listener);
        spinner.setAdapter(adapter);
    }
}
