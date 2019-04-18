package Listener;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

public class CallableListener extends AnimatorListenerAdapter {
    private Runnable callforward;
    private Runnable callback;

    public CallableListener(Runnable callforward, Runnable callback){
        this.callforward = callforward;
        this.callback = callback;
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if(this.callback != null){
            this.callback.run();
        }
        super.onAnimationEnd(animation);
    }

    @Override
    public void onAnimationStart(Animator animation) {
        if(this.callforward != null){
            this.callforward.run();
        }
        super.onAnimationStart(animation);
    }
}
