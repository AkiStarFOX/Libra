package com.stoka.libra;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class LibraView extends View {
    Paint p;
    Paint pCirlce;
    float x;
    float y;
    float xCenter;
    float yCenter;
    private RectF rect;
    private float angle;
    private static final int START_ANGLE_POINT = 90;

    public LibraView(Context context) {
        super(context);
        init();
    }

    public LibraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LibraView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radius = getWidth()/2-100;
        xCenter=getWidth()/2;
        yCenter=getHeight()/2;
        canvas.drawCircle(xCenter,yCenter,radius,p);
        canvas.drawCircle(xCenter,yCenter,50,p);
        rect.left = xCenter-radius-15;
        rect.top = yCenter-radius-15;
        rect.right=xCenter+radius+15;
        rect.bottom = yCenter+radius+15;
        canvas.drawArc(rect, START_ANGLE_POINT, angle, false, pCirlce);

    }
    public void init(){
        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(15);

        final int strokeWidth = 40;
        pCirlce = new Paint();
        pCirlce.setAntiAlias(true);
        pCirlce.setStyle(Paint.Style.STROKE);
        pCirlce.setStrokeWidth(strokeWidth);
        //Circle color
        pCirlce.setColor(Color.RED);

        //size 200x200 example
        rect = new RectF(xCenter, yCenter, 200 + xCenter, 200 + yCenter);

        //Initial Angle (optional, it can be zero)
        angle = 0;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
    public void setpCirlce(){
        pCirlce.setColor(Color.GREEN);
        invalidate();
    }
}
