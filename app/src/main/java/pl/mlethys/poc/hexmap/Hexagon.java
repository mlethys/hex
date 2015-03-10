package pl.mlethys.poc.hexmap;

import android.graphics.Color;
import android.graphics.Point;
import android.view.View;
import android.widget.Toast;

/**
 * Created by mlethys on 2015-02-06.
 */
public class Hexagon {

    public static final int SIDES = 6;
    private int radius;

    private Point[] points;
    private Point centerPoint;
    private double angle;
    private double height;
    private boolean toDraw;
    private int color;


    public Hexagon(Point centerPoint, int radius) {
        this.centerPoint = centerPoint;
        this.radius = radius;
        toDraw = false;
        color = R.color.superDarkGray;
        points = new Point[SIDES];
        setPoints();
        height = Math.sqrt(3) * (this.radius / 2);
    }

    private double findAngle(double fraction) {
        return fraction * Math.PI * 2 + Math.toRadians((90 + 180) % 360);
    }

    private void setPoints() {
        for(int i = 0; i < SIDES; i++) {
            angle = findAngle((double) i / SIDES);
            int x = (int) (centerPoint.x + Math.cos(angle) * radius);
            int y = (int) (centerPoint.y + Math.sin(angle) * radius);
            points[i] = new Point(x, y);
        }
    }

    public Point getPoint(int index) {
        return points[index];
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
          setPoints();
    }

    public boolean isToDraw() {
        return toDraw;
    }

    public void setToDraw(boolean toDraw) {
        this.toDraw = toDraw;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setCenterPoint(Point centerPoint) {
        this.centerPoint = centerPoint;
        setPoints();
    }

}

