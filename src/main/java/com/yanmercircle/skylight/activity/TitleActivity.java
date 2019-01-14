package com.yanmercircle.skylight.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.yanmercircle.skylight.R;

import util.AnimUtil;

public class TitleActivity extends BaseActivity {

    //region メソッド
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        this.init();
    }

    public void init() {
        ImageView titleImage = findViewById(R.id.title_icon);
        AnimUtil.rotate(titleImage, true);
    }

    public void onTransition(View view){
        super.onTransition(view);
    }
    //endregion
}