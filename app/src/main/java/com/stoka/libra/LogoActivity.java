package com.stoka.libra;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogoActivity extends AppCompatActivity {
    @BindView(R.id.txtHello)
    TextView hello;
    @BindView(R.id.txtHello2)
    TextView hello2;
    @BindView(R.id.btnLogo)
    Button btnLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        ButterKnife.bind(this);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        AnimatorSet animatorSet = new AnimatorSet();
        hello2.setVisibility(View.INVISIBLE);
        btnLogo.setVisibility(View.INVISIBLE);

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(hello,"translationX",-width,0).setDuration(2000);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(hello2,"translationX",width,0).setDuration(2000);
        final ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(btnLogo,"translationY",height,0).setDuration(2000);

        objectAnimator2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                hello2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                objectAnimator3.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        objectAnimator3.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                btnLogo.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        objectAnimator2.setStartDelay(1000);
        objectAnimator1.start();
        objectAnimator2.start();




    }

    @OnClick(R.id.btnLogo)
    void onLogoClick(){

        Intent intent = new Intent(this,CalibrationActivity.class);
        startActivity(intent);

    }


}
