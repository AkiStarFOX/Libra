package com.stoka.libra;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LibraActivity extends AppCompatActivity {
    @BindView(R.id.txtLibra)
    TextView txtLibra;
    @BindView(R.id.btnLibra)
    Button btnLibra;
    @BindView(R.id.libraView)
    LibraView libraView;

    Thread thread;
    boolean isFinger;
    Handler handler;
    CircleAngleAnimation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libra);
        ButterKnife.bind(this);

        animation = new CircleAngleAnimation(libraView, 360);
        animation.setDuration(5000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                libraView.setpCirlce();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



        libraView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        isFinger=true;
                        setTextForFinger();

                        libraView.startAnimation(animation);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("TAG","UPPP");
                        isFinger=false;// отпускание
                        animation.cancel();
                        libraView.setAngle(0);

                    case MotionEvent.ACTION_CANCEL:

                        break;
                }

                return true;
            }
        });
    }
    private void setTextForFinger(){

        handler=new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                if(isFinger){
                    setLibraText();
                    handler.postDelayed(this,100); // set time here to refresh textView
                }

            }
        });
    }
    private void setLibraText(){
        float minX = 270.0f;
        float maxX = 350.0f;

        Random rand = new Random();

        float finalX = rand.nextFloat() * (maxX - minX) + minX;

        txtLibra.setText(finalX+"g");
    }
}
