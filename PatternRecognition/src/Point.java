import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    public Point(int x, int y) {             // constructs the point (x, y)
        assert (x >= 0 && x <= 32767);
        assert (y >= 0 && y <= 32767);
        this.x = x;
        this.y = y;
    }

    public void draw() {                     // draws this point
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {         // draws the line segment from this point to that point
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {               // string representation
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that) {       // compare two points by y-coordinates, breaking ties by x-coordinates
        if (this.y < that.y) return -1;
        if (this.y > that.y) return 1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return 1;
        return 0;
    }

    public double slopeTo(Point that) {      // the slope between this point and that point
        if (this.x == that.x && this.y == that.y) return Double.NEGATIVE_INFINITY;
        if (this.x == that.x) return Double.POSITIVE_INFINITY;
        if (this.y == that.y) return +0.0;

        return ((double) that.y - this.y) / (that.x - this.x);
    }

    public Comparator<Point> slopeOrder() {  // compare two points by slopes they make with this point
        return new SlopeOrder(this);
    }


    private class SlopeOrder implements Comparator<Point> {
        private final Point point;

        SlopeOrder(Point point) {
            this.point = point;
        }

        @Override
        public int compare(Point p1, Point p2) {
            double slopeToP1 = point.slopeTo(p1);
            double slopeToP2 = point.slopeTo(p2);

            if (slopeToP1 == slopeToP2) return 0;
            if (slopeToP1 < slopeToP2) return -1;
            if (slopeToP1 > slopeToP2) return 1;
            return 0;
        }
    }


    public static void main(String[] args) {
        Point point = new Point(2, 2);
        Point point2 = new Point(1, 3);
        Point point3 = new Point(5, 5266);
        System.out.println(point.slopeTo(point2));
    }
}
