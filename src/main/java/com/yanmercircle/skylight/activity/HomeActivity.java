package com.yanmercircle.skylight.activity;

import android.os.Bundle;
import android.view.View;
import com.yanmercircle.skylight.R;

import service.UserService;
import model.*;

public class HomeActivity extends BaseActivity {

    //region メソッド
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.init();
    }

    //region
    /**
     * ゲーム初期化処理
     */
    private void init() {
        UserService user = new UserService();
        ApplicationModel app = ApplicationModel.getInstance();
        app.setUser(user.getUser());
    }

    public void onTransition(View view){
        super.onTransition(view);
    }
    //endregion
}