package pl.mlethys.poc.hexmap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

/**
 * Created by mlethys on 2015-03-08.
 */
public class SwingOMeterCanvas extends View {

    public static final int DEFAULT_RADIUS = 120;
    public static final int MAX_RADIUS = 220;

    private Context context;
    private SwingOMeter swingOMeter;
    private HexCanvas hexCanvas;

    private int screenWidth;
    private int screenHeight;

    private boolean swingOMeterTouched;

    private int moveY;
    private int minY, maxY;

    public SwingOMeterCanvas(Context context) {
        super(context);
        this.context = context;
        createSwingOMeter();
        minY = (int)(context.getResources().getDisplayMetrics().heightPixels - (context.getResources().getDisplayMetrics().heightPixels / 2.5));
        maxY = (int)(context.getResources().getDisplayMetrics().heightPixels - (context.getResources().getDisplayMetrics().heightPixels / 1.2));
    }

    public SwingOMeterCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        createSwingOMeter();
        minY = (int)(context.getResources().getDisplayMetrics().heightPixels - (context.getResources().getDisplayMetrics().heightPixels / 2.5));
        maxY = (int)(context.getResources().getDisplayMetrics().heightPixels - (context.getResources().getDisplayMetrics().heightPixels / 1.2));
    }

    public SwingOMeterCanvas(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        createSwingOMeter();
        minY = (int)(context.getResources().getDisplayMetrics().heightPixels - (context.getResources().getDisplayMetrics().heightPixels / 2.5));
        maxY = (int)(context.getResources().getDisplayMetrics().heightPixels - (context.getResources().getDisplayMetrics().heightPixels / 1.2));
    }
    public void setHexCanvas(HexCanvas hexCanvas) {
        this.hexCanvas = hexCanvas;
    }

    private void createSwingOMeter() {
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        minY = (int)(context.getResources().getDisplayMetrics().heightPixels - (context.getResources().getDisplayMetrics().heightPixels / 2.5));
        maxY = (int)(context.getResources().getDisplayMetrics().heightPixels - (context.getResources().getDisplayMetrics().heightPixels / 1.2));
        System.out.println("min: " + minY);
        System.out.println("max: " + maxY);
        swingOMeter = new SwingOMeter(DEFAULT_RADIUS, new Point(screenWidth / 2, minY));
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(swingOMeter.getStartingPoint().x,
                            swingOMeter.getStartingPoint().y,
                            swingOMeter.getRadius(),
                            paint);
    }

    private boolean touchedCircle(MotionEvent ev, Point circlePoint, int radius) {
        if((ev.getX() <= circlePoint.x - radius && ev.getX() >= circlePoint.x + radius) &&
            (ev.getY() <= circlePoint.y - radius && ev.getY() >= circlePoint.y + radius)) {
            return true;
        }
        return false;
    }

    private void moveSwingOMeter(int y) {
        if(swingOMeter.getRadius() <= MAX_RADIUS) {
            swingOMeter.setRadius(swingOMeter.getRadius() + 10);
        }
        if(swingOMeter.getRadius() >= DEFAULT_RADIUS) {
            swingOMeter.setRadius(swingOMeter.getRadius() - 10);
        }
        swingOMeter.setStartingPoint(
                new Point(swingOMeter.getStartingPoint().x, y));
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        hexCanvas.dispatchTouchEvent(ev);
        switch(action) {
            case MotionEvent.ACTION_UP:
                swingOMeterTouched = touchedCircle(ev, swingOMeter.getStartingPoint(), swingOMeter.getRadius());
                if(swingOMeterTouched) {
                    System.out.println("Circle was touched");
                    moveY = (int) ev.getY();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(swingOMeterTouched) {
                    if(swingOMeter.getStartingPoint().y >= maxY || swingOMeter.getStartingPoint().y <= minY) {
                        System.out.println("swingometer can move");
                        System.out.println((int) (moveY - ev.getY()));
                        moveSwingOMeter((int)ev.getY());
                        moveY = (int) ev.getY();
                        SwingOMeterCanvas.this.invalidate();
                    }
                }
        }
        return true;
    }
}
