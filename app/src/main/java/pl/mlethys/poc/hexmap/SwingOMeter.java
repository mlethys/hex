package pl.mlethys.poc.hexmap;

import android.graphics.Point;

/**
 * Created by mlethys on 2015-03-08.
 */
public class SwingOMeter {

    public static final int SWING_CIRCLES_QUANTITY = 6;

    private int radius;
    private SwingCircle[] swingCircles;
    private Point startingPoint;


    public SwingOMeter(int radius, Point startingPoint) {
        this.radius = radius;
        this.startingPoint = startingPoint;
        swingCircles = new SwingCircle[SWING_CIRCLES_QUANTITY];
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Point getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Point startingPoint) {
        this.startingPoint = startingPoint;
        System.out.println("Y: " + this.startingPoint.y);
    }

    private class SwingCircle {
        private int radius;
        private Point startingPoint;

        public SwingCircle(int radius) {
            this.startingPoint = startingPoint;
        }
    }
}
