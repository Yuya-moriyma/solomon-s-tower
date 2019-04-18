package Listener.Thread;

import com.yanmercircle.skylight.R;

import util.AnimUtil;

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
