package com.yanmercircle.skylight.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.yanmercircle.skylight.R;

import java.util.ArrayList;

import constant.Route;
import entity.ButtonItem;
import entity.ListAdapter;
import util.AnimUtil;

public class BaseActivity extends AppCompatActivity {

    protected ArrayList<String> values;
    protected Intent intent;

    /**
     * コンストラクタ
     *
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        this.values = new ArrayList<>();
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
        //バトル準備画面遷移
        if (Route.TRANSITION_ID_BATTLE_READY.contains(nextViewId)) {
            intent = new Intent(this, BattleReadyActivity.class);
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
        int index = 0;
        for (String value : values) {
            intent.putExtra(String.valueOf(index), value);
            index++;
        }
        this.startActivity(intent);
    }

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
     * スピナー要素セット
     */
    protected void setSpinner(int id, ArrayList<ButtonItem> list, AdapterView.OnItemSelectedListener listener) {
        ListAdapter adapter = new ListAdapter(this, list);
        Spinner spinner = findViewById(id);
        spinner.setOnItemSelectedListener(listener);
        spinner.setAdapter(adapter);
    }
}
