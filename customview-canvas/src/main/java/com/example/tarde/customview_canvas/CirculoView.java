package com.example.tarde.customview_canvas;

import android.app.Notification;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by tarde on 13/02/2015.
 */
public class CirculoView extends View {
    public CirculoView(Context context) {
        this(context, null);
    }

    public CirculoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirculoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint pincel;

    private void init() {
        pincel = new Paint();
        pincel.setColor(Color.MAGENTA);
        pincel.setStrokeWidth(1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(300,300,100,pincel);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        invalidate();
        return super.onTouchEvent(event);
    }
}
