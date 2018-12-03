package com.stoka.libra;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.badoualy.stepperindicator.StepperIndicator;
import com.stoka.libra.fragments.CalibrateFragment;
import com.stoka.libra.fragments.ListFragment;
import com.stoka.libra.fragments.PutOnTableFragment;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalibrationActivity extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    StepperIndicator indicator;
    @BindView(R.id.btnNext)
    Button btnNext;
    CalibrateFragment calibrateFragment;
    PutOnTableFragment putOnTableFragment;
    ListFragment listFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration);
        ButterKnife.bind(this);

        btnNext.setVisibility(View.INVISIBLE);
        calibrateFragment = new CalibrateFragment();

        putOnTableFragment = new PutOnTableFragment();
        listFragment = new ListFragment();

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), calibrateFragment, putOnTableFragment, listFragment));
        indicator.setViewPager(viewPager);
// or keep last page as "end page"
        indicator.setViewPager(viewPager, viewPager.getAdapter().getCount() - 1);

//
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                return true;
//            }
//        });

        calibrateFragment.setOnViewCreateDoneListener(new CalibrateFragment.onViewCreateDoneListener() {
            @Override
            public void onCreateComplete() {
                btnNext.setVisibility(View.VISIBLE);
               btnNext.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       switch (viewPager.getCurrentItem()){
                           case 0:
                               viewPager.setCurrentItem(1);
                               break;
                           case 1:
                               viewPager.setCurrentItem(2);
                               btnNext.setVisibility(View.INVISIBLE);
                               break;
                       }

                   }
               });
            }
        });
        listFragment.setOnRVItemClickListener(new ListFragment.onRVItemClickListener() {
            @Override
            public void onItemClick(String s) {
                Intent intent = new Intent(CalibrationActivity.this, LibraActivity.class);
                intent.putExtra("name", s);
                startActivity(intent);
            }
        });

    }


}
