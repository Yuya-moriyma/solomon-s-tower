package tower_of_solomon.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tower_of_solomon.R;

import tower_of_solomon.constant.Character;
import tower_of_solomon.entity.TransitionParam;


public class BattleReadyFragment extends BaseFragment {

    public BattleReadyFragment() {
    }

    public static BattleReadyFragment newInstance(TransitionParam param) {
        BattleReadyFragment fragment = new BattleReadyFragment();
        if (param != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("param", param);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TransitionParam param = (TransitionParam) getArguments().getSerializable("param");
        return initView(inflater, container, param);
    }

    private View initView(LayoutInflater inflater, ViewGroup container, TransitionParam param) {
        View view = inflater.inflate(R.layout.fragment_battle_ready, container, false);
        changeText(view, R.id.header, String.format("%d階の悪魔", param.getInt("nextFloor")));
        changeText(view, R.id.name, param.getString("name"));
        changeText(view, R.id.skill_detail, param.getString("skill_detail"));
        changeImage(view, R.id.type_image, Character.TYPE_IMAGE_LIST.get(param.getInt("type")));
        changeImage(view, R.id.battle_ready_image, Character.IMAGE_ID_LIST.get(param.getInt("id")));
        return view;
    }
}
