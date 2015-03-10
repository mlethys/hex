package pl.mlethys.poc.hexmap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

/**
 * Created by mlethys on 2015-02-05.
 */
public class HexCanvas extends View{

    public static final double OFFSET = 0.7;
    public static final int MARGIN = 10;
    private int radius;

    private boolean isScaling = false;

    private Paint paint = new Paint();
    private Path drawPath = new Path();
    private boolean radiusChanged = false;
    private ScaleGestureDetector scaleGestueDetector;

    private float downX = 0;
    private float downY = 0;
    private int moveX = 0;
    private int moveY = 0;
    private float upX = 0;
    private float upY = 0;
    private float dX = 0;
    private float dY = 0;
    private boolean mapTouched;
/*  Use this map to generate Great Britain.
    private int[][] map = {
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
    };*/

private int[][] map = { //comment this array to use proper map instead of this created only for testing.
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,1,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,1,1,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0},
        {0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,0,0,0,0,0},
        {0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,0,0,0,0},
        {0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
        {0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
        {0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
        {0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
        {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
        {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
        {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
        {0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
        {0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
        {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
        {0,0,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
        {0,0,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
        {0,0,0,0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
        {0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
        {0,0,0,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
        {0,0,0,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
        {0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0},
        {0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0}
};

    private Hexagon[][] hexMap;




    public HexCanvas(Context context) {
        super(context);
        scaleGestueDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    public HexCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        scaleGestueDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    public HexCanvas(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        scaleGestueDetector = new ScaleGestureDetector(context, new ScaleListener());

    }

    public void setRadius(int radius) {
        this.radius = radius;
        radiusChanged = true;
    }

    public Hexagon[][] getHexMap() {
        return hexMap;
    }

    private void populateHexMap() {
        System.out.println("populate hex map");
        hexMap = new Hexagon[map.length][map[0].length];
        for (int j = 0; j < map[0].length; j++) {
            hexMap[0][j] = new Hexagon(new Point(j * (radius * 2) + radius, radius), radius);
            if (map[0][j] == 1) {
                hexMap[0][j].setToDraw(true);
            }
        }
        int r = hexMap[0][0].getRadius();
        for (int i = 1; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if((i % 2) == 0) {
                    hexMap[i][j] = new Hexagon(new Point(hexMap[i - 1][j].getCenterPoint().x - r, (int) (hexMap[i - 1][j].getCenterPoint().y + radius + radius * OFFSET)), radius);
                } else {
                    hexMap[i][j] = new Hexagon(new Point(hexMap[i - 1][j].getCenterPoint().x + r, (int) (hexMap[i - 1][j].getCenterPoint().y + radius + radius * OFFSET)), radius);
                }
                if (map[i][j] == 1) {
                    hexMap[i][j].setToDraw(true);
                }
            }
        }
        radiusChanged = false;
    }

    private void changeRadius() {
        System.out.println("change radius");
        for (int j = 0; j < map[0].length; j++) {
            hexMap[0][j].setCenterPoint(new Point((j * (radius * 2) + radius), radius));

            hexMap[0][j].setRadius(radius);
        }
        int r = hexMap[0][0].getRadius();
        for (int i = 1; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if((i % 2) == 0) {
                    hexMap[i][j].setCenterPoint(new Point(hexMap[i - 1][j].getCenterPoint().x - r, (int) (hexMap[i - 1][j].getCenterPoint().y + radius + radius * OFFSET)));
                    hexMap[i][j].setRadius(radius);
                } else {
                    hexMap[i][j].setCenterPoint(new Point(hexMap[i - 1][j].getCenterPoint().x + r, (int) (hexMap[i - 1][j].getCenterPoint().y + radius + radius * OFFSET)));
                    hexMap[i][j].setRadius(radius);
                }
            }
        }
        radiusChanged = false;
    }

    @Override
    public void onDraw(Canvas canvas) {
        System.out.println("On draw");
        if(hexMap == null || hexMap.length <= 0) {
            populateHexMap();
        }
        if(radiusChanged) {
            changeRadius();
        }
        for(int i = 0; i < hexMap.length; i++) {
            for(int j = 0; j < hexMap[i].length; j++) {
                if(hexMap[i][j] != null && hexMap[i][j].isToDraw()) {
                    paint.setColor(hexMap[i][j].getColor());
                    drawPath.reset();
                    drawPath.moveTo(hexMap[i][j].getPoint(0).x, hexMap[i][j].getPoint(0).y);
                    drawPath.lineTo(hexMap[i][j].getPoint(1).x, hexMap[i][j].getPoint(1).y);
                    drawPath.lineTo(hexMap[i][j].getPoint(2).x, hexMap[i][j].getPoint(2).y);
                    drawPath.lineTo(hexMap[i][j].getPoint(3).x, hexMap[i][j].getPoint(3).y);
                    drawPath.lineTo(hexMap[i][j].getPoint(4).x, hexMap[i][j].getPoint(4).y);
                    drawPath.lineTo(hexMap[i][j].getPoint(5).x, hexMap[i][j].getPoint(5).y);
                    drawPath.lineTo(hexMap[i][j].getPoint(0).x, hexMap[i][j].getPoint(0).y);
                    canvas.drawPath(drawPath, paint);
                }
            }

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int mActivePointerId = -1;
        scaleGestueDetector.onTouchEvent(ev);

        final int action = MotionEventCompat.getActionMasked(ev);

        switch(action) {
            case MotionEvent.ACTION_DOWN:
                mapTouched = isMapTouched(ev);
                int pointerIndex = MotionEventCompat.getActionIndex(ev);
                downX = MotionEventCompat.getX(ev, pointerIndex);
                downY = MotionEventCompat.getY(ev, pointerIndex);
                moveX = (int)downX;
                moveY = (int)downY;
                break;
            case MotionEvent.ACTION_MOVE:
                if(!isScaling) {
                    if(mapTouched) {
                        moveMap((int) (moveX - ev.getX()), (int) (moveY - ev.getY()));
                        moveX = (int) ev.getX();
                        moveY = (int) ev.getY();
                        HexCanvas.this.invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                pointerIndex = MotionEventCompat.getActionIndex(ev);
                upX = MotionEventCompat.getX(ev, pointerIndex);
                upY = MotionEventCompat.getY(ev, pointerIndex);
                if(((int) upX <= (int)downX + MARGIN && (int) upX >= (int)downX - MARGIN) &&
                    ((int) upY <= (int)downY + MARGIN && (int) upY >= (int)downY - MARGIN)) {
                    changeColorOnTouchEvent(ev);
                    HexCanvas.this.invalidate();
                }
                isScaling = false;
                break;
        }
        return true;
    }

    private boolean isMapTouched(MotionEvent ev) {
        for(int i = 0; i < hexMap.length; i++) {
            for (int j = 0; j < hexMap[i].length; j++) {
                if (hexMap[i][j] != null && hexMap[i][j].isToDraw()) {
                    if ((ev.getX() >= hexMap[i][j].getCenterPoint().x - hexMap[i][j].getRadius())
                            && (ev.getX() <= hexMap[i][j].getCenterPoint().x + hexMap[i][j].getRadius())
                            && ((ev.getY() >= hexMap[i][j].getCenterPoint().y - hexMap[i][j].getRadius())
                            && (ev.getY() <= hexMap[i][j].getCenterPoint().y + hexMap[i][j].getRadius()))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void changeColorOnTouchEvent(MotionEvent ev) {
        for(int i = 0; i < hexMap.length; i++) {
            for (int j = 0; j < hexMap[i].length; j++) {
                if(hexMap[i][j] != null && hexMap[i][j].isToDraw()) {
                    if((ev.getX() >= hexMap[i][j].getCenterPoint().x - hexMap[i][j].getRadius())
                            && (ev.getX() <= hexMap[i][j].getCenterPoint().x + hexMap[i][j].getRadius())
                            && ((ev.getY() >= hexMap[i][j].getCenterPoint().y - hexMap[i][j].getRadius())
                            && (ev.getY() <= hexMap[i][j].getCenterPoint().y + hexMap[i][j].getRadius()))) {
                        hexMap[i][j].setColor(getResources().getColor(R.color.black));
                        return;
                    }
                }
            }
        }
    }

    private void moveMap(int shiftX, int shiftY) {
        for(int i = 0; i < hexMap.length; i++) {
            for (int j = 0; j < hexMap[i].length; j++) {
                if(hexMap[i][j] != null && hexMap[i][j].isToDraw()) {
                    hexMap[i][j].setCenterPoint(new Point(hexMap[i][j].getCenterPoint().x - shiftX, hexMap[i][j].getCenterPoint().y - shiftY));
                }
            }
        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private float scale = 1f;
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if((int)(MainActivity.DEFAULT_RADIUS * scale) <= 1500 &&
                            (int)(MainActivity.DEFAULT_RADIUS * scale) >= MainActivity.DEFAULT_RADIUS) {
                        HexCanvas.this.setRadius((int) (MainActivity.DEFAULT_RADIUS * scale));
                    }
                }
            }).run();
            HexCanvas.this.invalidate();
            System.out.println("on scale");
            isScaling = true;
            return true;
        }
    }
}
