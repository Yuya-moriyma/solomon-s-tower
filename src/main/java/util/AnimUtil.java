package util;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import java.util.List;

import Listener.CallableListener;
import constant.Battle;

public class AnimUtil {

    /**
     * 回転
     *
     * @param image
     * @param isInfinite
     */
    public static void rotate(View image, boolean isInfinite) {
        RotateAnimation anim = new RotateAnimation(0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(50000);
        anim.setRepeatCount(1);
        anim.setInterpolator(new LinearInterpolator());
        if (isInfinite) {
            anim.setRepeatCount(Animation.INFINITE);
        }
        anim.setFillAfter(true);
        image.startAnimation(anim);
        //アニメーションの開始
    }

    /**
     * フェードアウト
     *
     * @param view
     * @param onCompleted
     * @param delay
     */
    public static void fadeOut(View view, Runnable onCompleted, int delay) {
        view.animate()
                .alpha(0f)
                .setDuration(500)
                .setStartDelay(delay)
                .withEndAction(onCompleted)
                .start();
    }

    /**
     * 連続フェードイン
     *
     * @param views
     */
    public static void fadeInAll(View[] views, Runnable onCompleted) {
        long delay = 0;
        for (View view : views) {
            view.animate()
                    .alpha(1f)
                    .setStartDelay(delay);
            if (view == views[views.length - 1] && onCompleted != null) {
                view.animate().withEndAction(onCompleted);
            }
            view.animate().start();
            delay += 150;
        }
    }

    public static Animator createFadeIn(View view, Runnable callforward) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).setDuration(750);
        if(callforward != null){
            objectAnimator.addListener(new CallableListener(callforward, null));
        }
        return objectAnimator;
    }

    public static Animator createFadeOut(View view, Runnable callforward) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f).setDuration(750);
        if(callforward != null){
            objectAnimator.addListener(new CallableListener(callforward, null));
        }
        return objectAnimator;
    }

    public static Animator createHidden(View view, Runnable callback) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        if(callback != null){
            objectAnimator.addListener(new CallableListener(callback, null));
        }
        return objectAnimator;
    }

    public static Animator createShow(View view, Runnable callback) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        if(callback != null){
            objectAnimator.addListener(new CallableListener(callback, null));
        }
        return objectAnimator;
    }

    public static Animator createColorEffect(View view, int color, Runnable callback) {
        if(color == Battle.EFFECT_TYPE.NONE.getColor()){
            return null;
        }
        final View _view = view;
        int colorFrom = ((ColorDrawable) _view.getBackground()).getColor();
        ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, color);
        colorAnimator.setDuration(375);
        colorAnimator.setRepeatCount(1);
        colorAnimator.setRepeatMode(ValueAnimator.REVERSE);
        colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                _view.setBackgroundColor((int) animator.getAnimatedValue());
            }
        });
        if(callback != null){
            colorAnimator.addListener(new CallableListener(null, callback));
        }
        return colorAnimator;
    }

    public static Animator createDamageFlash(View view, Runnable callback) {
        ObjectAnimator flashAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        flashAnimator.setDuration(50);
        flashAnimator.setRepeatMode(ValueAnimator.REVERSE);
        flashAnimator.setRepeatCount(7);
        flashAnimator.addListener(new CallableListener(null, callback));
        return flashAnimator;
    }

    public static void permutationExecute(List<Animator> animators) {
        try {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(animators);
            animatorSet.start();
        } catch (Exception e){
            Exception _e = e;
        }
    }

    public void getEffectColor(){

    }
}
