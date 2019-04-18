package tower_of_solomon.constant;

import com.yanmercircle.tower_of_solomon.R;

import java.util.ArrayList;
import java.util.List;

public class Route {

    public static final List<Integer> TRANSITION_ID_TITLE = new ArrayList<Integer>() {
        {
            add(R.id.button_move_title);
        }
    };

    public static final List<Integer> TRANSITION_ID_HOME = new ArrayList<Integer>() {
        {
            add(R.id.button_new_game);
            add(R.id.button_continue);
        }
    };


    public static final List<Integer> TRANSITION_ID_BATTLE = new ArrayList<Integer>() {
        {
            add(R.id.button_move_battle);
        }
    };

    public static final List<Integer> TRANSITION_ID_BATTLE_READY = new ArrayList<Integer>() {
        {
            add(R.id.button_move_battle_ready);
        }
    };

    public static final List<Integer> TRANSITION_ID_RESULT = new ArrayList<Integer>() {
        {
            add(Result.TRANSITION_ID_RESULT);
        }
    };
}
