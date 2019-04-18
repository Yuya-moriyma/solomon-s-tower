package tower_of_solomon.Listener.Thread;

import com.yanmercircle.tower_of_solomon.R;

public class CallbackableThread {
    private CallbackableThread mainThread;
    private CallbackableThread callback;

    public CallbackableThread(CallbackableThread mainThread, CallbackableThread callback){
        this.mainThread = mainThread;
        this.callback = callback;
    }

    public void run(){
        new Thread(){
            @Override
            public void run() {
                mainThread.run();
                callback.run();
            }
        }.start();
    }
}
