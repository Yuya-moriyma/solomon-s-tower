package com.yanmercircle.skylight.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("info", "create:" + getClass().getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("info", "destroy:" + getClass().getName());
    }

    /**
     * テキスト変更
     *
     * @param id
     * @param text
     */
    protected void changeText(View view, int id, String text) {
        TextView textView = view.findViewById(id);
        textView.setText(text);
    }

    /**
     * 画像変更
     *
     * @param id
     * @param imageId
     */
    protected void changeImage(View view, int id, int imageId) {
        ImageView imageView = view.findViewById(id);
        imageView.setImageResource(imageId);
    }
}
