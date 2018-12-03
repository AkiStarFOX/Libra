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
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import java.math.BigDecimal;

public class AccelView extends View {
    onCalibrateCompleteListener listener;

    public interface onCalibrateCompleteListener{
        void onCalibrateComplete();

    }

    public void setOnCalibrateListener(onCalibrateCompleteListener onCalibrateListener){
        listener = onCalibrateListener;
    }

    Context context;
    Paint p, pLeftTop, pLeftBottom, pRightTop, pRightBottom;
    RectF rectLeftTop, rectLeftBottom, rectRightTop, rectRightBottom,rectCursor;
    float x = 100;
    float y = 100;
    int side = 20;

    float width;
    float height;
    boolean isFirst=false,isSecond=false,isThird=false,isFour=false;



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


        rectCursor = new RectF(x,y,x+side,y+side);
        canvas.drawRect(rectCursor, p);
        canvas.drawRect(rectLeftTop, pLeftTop);
        canvas.drawRect(rectLeftBottom, pLeftBottom);
        canvas.drawRect(rectRightTop, pRightTop);
        canvas.drawRect(rectRightBottom, pRightBottom);

        if(rectLeftBottom.contains(rectCursor)){
            pLeftBottom.setColor(Color.GREEN);
            isFirst=true;
        }else if(rectLeftTop.contains(rectCursor)){
            pLeftTop.setColor(Color.GREEN);
            isSecond=true;
        }else if(rectRightBottom.contains(rectCursor)){
            pRightBottom.setColor(Color.GREEN);
            isThird=true;
        }else if(rectRightTop.contains(rectCursor)){
            pRightTop.setColor(Color.GREEN);
            isFour=true;
        }

        if (isFirst && isSecond && isThird && isFour){
            listener.onCalibrateComplete();
        }

    }

    public void init() {

        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                width = getWidth();
                height = getHeight();
                float widthTen = width / 10;
                float heightTen = height / 10;


                rectLeftTop = new RectF(widthTen, heightTen, widthTen * 2, heightTen * 2);
                rectLeftBottom = new RectF(widthTen, height - heightTen * 2, widthTen * 2,height - heightTen );
                rectRightTop = new RectF(width - widthTen * 2, heightTen,width - widthTen , heightTen * 2);
                rectRightBottom = new RectF(width - widthTen * 2, height - heightTen * 2,width - widthTen , height - heightTen);

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

                                               x -= round(sensorEvent.values[0], 5) * 5;
                                               y += round(sensorEvent.values[1], 5) * 5;

                                               if (x + side > width) x = width - side;
                                               if (x <= 0) x = 0;
                                               if (y + side > height) y = height - side;
                                               if (y <= 0) y = 0;


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
        if (d >= -1 && d <= 1) {
            return 0;
        }
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_DOWN);
        return bd.floatValue();
    }

    private Paint createPaint() {
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.RED);
        return p;
    }

}
