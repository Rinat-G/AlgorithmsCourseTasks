import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private int count = 0;
    private List<LineSegment> segments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {    // finds all line segments containing 4 points
        if (points == null) {
            throw new java.lang.IllegalArgumentException("Argument can't be null");
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new java.lang.IllegalArgumentException("Point can't be null");
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    if (!isCollinear(points[i], points[j], points[k])) continue;
                    for (int n = k + 1; n < points.length; n++) {
                        if (isCollinear(points[i], points[j], points[k], points[n])) {
                            count++;
                            segments.add(getSegment(points[i], points[j], points[k], points[n]));


                        }

                    }

                }

            }
        }

    }

    public int numberOfSegments() {        // the number of line segments
        return count;
    }

    public LineSegment[] segments() {                // the line segments
        LineSegment[] a = new LineSegment[segments.size()];
        segments.toArray(a);
        return a;
    }

    private boolean isCollinear(Point p1, Point p2, Point p3) {
        double slopeP1toP2 = checkedSlopeTo(p1, p2);
        double slopeP1toP3 = checkedSlopeTo(p1, p3);

        return slopeP1toP2 == slopeP1toP3;

    }

    private boolean isCollinear(Point p1, Point p2, Point p3, Point p4) {
        double slopeP1toP2 = checkedSlopeTo(p1, p2);
        double slopeP1toP3 = checkedSlopeTo(p1, p3);
        double slopeP1toP4 = checkedSlopeTo(p1, p4);

        return (slopeP1toP2 == slopeP1toP3) && (slopeP1toP3 == slopeP1toP4);

    }

    private LineSegment getSegment(Point p1, Point p2, Point p3, Point p4) {
        Point[] segPoints = {p1, p2, p3, p4};
        Arrays.sort(segPoints);
        return new LineSegment(segPoints[0], segPoints[3]);

    }

    private double checkedSlopeTo(Point p1, Point p2) {
        double slopeP1toP2 = p1.slopeTo(p2);
        if (slopeP1toP2 == Double.NEGATIVE_INFINITY) {
            throw new java.lang.IllegalArgumentException("Repeated point detected");
        }
        return slopeP1toP2;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
