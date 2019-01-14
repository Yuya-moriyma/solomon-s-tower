package model;

import android.app.Application;

public class ApplicationModel extends Application {

    //region フィールド
    private static UserModel userModel;
    private static BattleModel battleModel;
    private static ApplicationModel instance = null;
    //endregion

    //region メソッド
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static ApplicationModel getInstance() {
        return instance;
    }
    //endregion

    //region アクセサー
    public static void setUser(UserModel _userModel) {
        userModel = _userModel;

    }
    public static UserModel getUser() {
        return userModel;
    }

    public static void setBattle(BattleModel _battleModel) {
        battleModel = _battleModel;
    }

    public static BattleModel getBattle() {
        return battleModel;
    }
    //endregion
}
