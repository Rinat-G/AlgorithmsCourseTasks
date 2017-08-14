import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {           // constructs the point (x, y)
        this.x = x;
        this.y = y;
    }

    public void draw() {                   // draws this point
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {       // draws the line segment from this point to that point
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {             // string representation
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that) {     // compare two points by y-coordinates, breaking ties by x-coordinates
        if (this.y < that.y) return -1;
        if (this.y > that.y) return 1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return 1;
        return 0;
    }

    public double slopeTo(Point that) {    // the slope between this point and that point
        return 0.0;
    }

    public Comparator<Point> slopeOrder() {// compare two points by slopes they make with this point
        return null;
    }
}
