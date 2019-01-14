package util;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yanmercircle.skylight.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import entity.AsyncSleep;
import model.ApplicationModel;

public class AnimUtil {

    /**
     * 永続回転
     *
     * @param image
     */
    public static void rotate(ImageView image, boolean isInfinite) {
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
     */
    public static void fadeOut(View view, Runnable onCompleted) {
        view.animate()
                .alpha(0f)
                .setDuration(500)
                .withEndAction(onCompleted)
                .start();
    }

    /**
     * フェードアウト
     *
     * @param view
     */
    public static Animator fadeOut(View view) {
        ObjectAnimator fadeOutAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        fadeOutAnimator.setDuration(500);
        return fadeOutAnimator;
    }

    /**
     * フェードイン
     *
     * @param view
     */
    public static List<Animator> fadeIn(View view) {
        ArrayList<Animator> list = new ArrayList();
        ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        fadeInAnimator.setDuration(500);
        list.add(fadeInAnimator);
        ObjectAnimator fadeOutAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 1f);
        fadeOutAnimator.setStartDelay(1000);
        fadeOutAnimator.setDuration(500);
        list.add(fadeOutAnimator);
        return list;
    }

    /**
     * フェードイン
     *
     * @param view
     */
    public static void fadeIn(View view, Runnable onCompleted) {
        view.animate()
                .alpha(1f)
                .setDuration(500)
                .withEndAction(onCompleted)
                .start();
    }

    public static List<Animator> popup(View view) {
        ArrayList<Animator> list = new ArrayList();
        ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        fadeInAnimator.setDuration(500);
        list.add(fadeInAnimator);
        ObjectAnimator fadeOutAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        fadeOutAnimator.setStartDelay(1000);
        fadeOutAnimator.setDuration(500);
        list.add(fadeOutAnimator);
        return list;
    }

    /**
     * トースト
     */
    public static void toast(View view) {
        AlphaAnimation anim = new AlphaAnimation(1, 0);
        anim.setDuration(500);
        anim.setFillAfter(true);
        anim.setFillEnabled(true);
        view.startAnimation(anim);

        anim = new AlphaAnimation(0, 1);
        anim.setDuration(500);
        anim.setFillAfter(true);
        anim.setFillEnabled(true);
        anim.setStartTime(3000);
        view.startAnimation(anim);
    }

    public static void testAnim(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(view, "Text", new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                return (fraction < 0.5) ? startValue : endValue;
            }
        }, "-", "--", "---");
        objectAnimator.setRepeatCount(1);
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }

    /**
     * スナックバー表示
     *
     * @param targetArea
     */
    public static void ShowSnackBar(View targetArea, String text) {
        Snackbar snackbar = Snackbar.make(targetArea, text, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(Color.RED);
        TextView textView = snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        textView.setBackgroundColor(Color.RED);
        textView.setTextColor(Color.BLACK);
        snackbar.show();
    }

}
