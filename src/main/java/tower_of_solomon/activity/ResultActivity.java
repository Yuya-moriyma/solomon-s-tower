package tower_of_solomon.activity;

import android.os.Bundle;

import tower_of_solomon.R;

import tower_of_solomon.entity.TransitionParam;

public class ResultActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    private void init(){
        //描画内容取得
        //描画設定
    }

    @Override
    public TransitionParam setParam() {
        return null;
    }
}
