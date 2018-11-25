package com.stoka.libra;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import java.math.BigDecimal;

public class AccelView extends View {
    Context context;
    Paint p,pLeftTop,pLeftBottom, pRightTop, pRightBottom;
    RectF rectLeftTop,rectLeftBottom,rectRightTop,rectRightBottom;
    float x = 100;
    float y = 100;
    int side = 100;

    float width;
    float height;




    public AccelView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public AccelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public AccelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        canvas.drawRect(x, y, x + side, y + side, p);
        canvas.drawRect(rectLeftTop,pLeftTop);
        canvas.drawRect(rectLeftBottom,pLeftBottom);
        canvas.drawRect(rectRightTop,pRightTop);
        canvas.drawRect(rectRightBottom,pRightBottom);
    }

    public void init(){

        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                width = getWidth();
                height = getHeight();
                float widthTen = width/10;
                float heightTen = height/10;

                rectLeftTop = new RectF(widthTen,heightTen,widthTen*2,heightTen*2);
                rectLeftBottom = new RectF(widthTen,height-heightTen,widthTen*2,height-heightTen*2);
                rectRightTop = new RectF(width-widthTen,heightTen,width-widthTen*2,heightTen*2);
                rectRightBottom = new RectF(width-widthTen,height-heightTen,width-widthTen*2,height-heightTen*2);

            }
        });
        p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.RED);

        pLeftTop = createPaint();

        pLeftBottom = createPaint();

        pRightTop = createPaint();

        pRightBottom = createPaint();



        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        final Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(new SensorEventListener() {
                                           @Override
                                           public void onSensorChanged(SensorEvent sensorEvent) {

                                               x += round(sensorEvent.values[0],5)*5;
                                               y += round(sensorEvent.values[1],5)*5;

                                               if (x+side>width) x = width-side;
                                               if (x<=0) x = 0;
                                               if (y+side>height) y=height-side;
                                               if (y<=0) y =0;


                                               invalidate();
                                           }

                                           @Override
                                           public void onAccuracyChanged(Sensor sensor, int i) {

                                           }
                                       },
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public static float round(float d, int decimalPlace) {
        if (d>=-1 && d<=1){
            return 0;
        }
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_DOWN);
        return bd.floatValue();
    }

    private Paint createPaint(){
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.RED);
        return p;
    }

}
