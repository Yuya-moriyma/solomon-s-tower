package util;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;


public class LogUtil {
    /**
     * ログ出力
     * @param message
     */
    public static void out (String message) {
        if(message == null || message == "") {
            return;
        }
        Log.i(tower_of_solomon.constant.Log.APP_ERROR_TAG, message);
    }

    /**
     * スタックトレース出力
     * @param e
     */
    public static void printStackTrace (RuntimeException e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String stackTrace = sw.toString();
        if(stackTrace == null || stackTrace == "") {
            return;
        }
        Log.i(tower_of_solomon.constant.Log.APP_ERROR_TAG, stackTrace);
    }

    public static void showErrorToast(RuntimeException e) {

    }
}
