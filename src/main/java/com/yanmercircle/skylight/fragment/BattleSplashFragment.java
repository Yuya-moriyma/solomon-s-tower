package com.yanmercircle.skylight.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanmercircle.skylight.R;

import entity.TransitionParam;
import util.AnimUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class BattleSplashFragment extends BaseFragment {

    private View view;

    public BattleSplashFragment() {
    }

    public static BattleSplashFragment newInstance(TransitionParam param) {
        BattleSplashFragment fragment = new BattleSplashFragment();
        if (param != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("param", param);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container);
    }

    @Override
    public void onStart() {
        super.onStart();
        TransitionParam param = (TransitionParam) getArguments().getSerializable("param");
        AnimUtil.rotate(view.findViewById(R.id.solomon_circle), true);
        changeText(view, R.id.splash_floor_header, param.getInt("floor") + "階");
        changeText(view, R.id.splash_floor_name, param.getString("name"));
        View[] views = {
                view.findViewById(R.id.splash_floor_header),
                view.findViewById(R.id.splash_floor_name)
        };
        AnimUtil.fadeInAll(views, null);
        view.findViewById(R.id.base)
                .animate()
                .alpha(0f)
                .setDuration(500)
                .setStartDelay(4000)
                .start();
    }

    private View initView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_battle_splash, container, false);
        return view;
    }


}
