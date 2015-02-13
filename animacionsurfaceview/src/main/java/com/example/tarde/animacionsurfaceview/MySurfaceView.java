package com.example.tarde.animacionsurfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;

/**
 * Created by tarde on 13/02/2015.
 */
public class MySurfaceView extends SurfaceView {
    private Paint pincel;

    public MySurfaceView(Context context) {
        this(context, null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        pincel = new Paint();
        pincel.setColor(Color.CYAN);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Canvas canvas = null;
                try {
                    canvas = getHolder().lockCanvas();
                    synchronized (getHolder()) {
                        canvas.drawColor(Color.WHITE);
                        canvas.drawCircle(x, y, 100, pincel);
                    }
                } finally {
                    if (canvas != null) {
                        getHolder().unlockCanvasAndPost(canvas);
                    }
                }
            }
        }).start();

        return super.onTouchEvent(event);
    }
}
