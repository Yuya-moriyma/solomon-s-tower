package tower_of_solomon.activity;

import android.os.Bundle;
import android.view.View;

import tower_of_solomon.R;

import tower_of_solomon.entity.TransitionParam;
import tower_of_solomon.service.UserService;
import util.AnimUtil;

public class TitleActivity extends BaseActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        init();
    }

    @Override
    protected void onResume() {
        AnimUtil.rotate(findViewById(R.id.title_icon), true);
        View[] views = {
                findViewById(R.id.title_message),
                findViewById(R.id.button_new_game),
                findViewById(R.id.button_continue)
        };
        AnimUtil.fadeInAll(views, null);
        super.onResume();
    }

    public void init() {

    }

    public void onTransition(View view) {
        if (view.getId() == R.id.button_new_game) {
            UserService user = new UserService();
            user.initDB();
        }
        super.onTransition(view);
    }

    @Override
    public TransitionParam setParam() {
        return null;
    }
}