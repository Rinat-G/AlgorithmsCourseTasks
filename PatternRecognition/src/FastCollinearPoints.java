import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FastCollinearPoints {
    //    private List<LineSegment> segments = new ArrayList<>();
    private List<LineEdges> linesEdges = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {     // finds all line segments containing 4 or more points
        if (points == null) {
            throw new java.lang.IllegalArgumentException("Argument can't be null");
        }

        for (int i = 0; i < points.length; i++) {

            if (points[i] == null) throw new java.lang.IllegalArgumentException("Point can't be null");

            Point[] pointsInSlopeOrder = new Point[points.length];
            System.arraycopy(points, 0, pointsInSlopeOrder, 0, points.length);
            Arrays.sort(pointsInSlopeOrder, points[i].slopeOrder());

            double slopeToPrevPoint = Double.POSITIVE_INFINITY;
            int sameSlopeCount = 0;

            List<Point> lineSegmentPoints = new ArrayList<>();
            lineSegmentPoints.add(points[i]);

            for (int j = 1; j < pointsInSlopeOrder.length; j++) {
                double slopeToThisPoint = points[i].slopeTo(pointsInSlopeOrder[j]);
                if (slopeToThisPoint == Double.NEGATIVE_INFINITY)
                    throw new java.lang.IllegalArgumentException("Repeated point detected");

                if (slopeToThisPoint == slopeToPrevPoint) {
                    if (sameSlopeCount == 0) {
                        lineSegmentPoints.add(pointsInSlopeOrder[j - 1]);
                        sameSlopeCount++;
                    }
                    lineSegmentPoints.add(pointsInSlopeOrder[j]);
                    sameSlopeCount++;
                } else {
                    lineSegmentPoints.clear();
                    lineSegmentPoints.add(points[i]);
                    slopeToPrevPoint = slopeToThisPoint;
                    sameSlopeCount = 0;
                }

                if (sameSlopeCount >= 3) {
                    if (j + 1 < pointsInSlopeOrder.length &&
                            slopeToThisPoint == points[i].slopeTo(pointsInSlopeOrder[j + 1])) {
                        continue;
                    }
                    addLineSegment(lineSegmentPoints);

                }


            }


        }

    }

    public int numberOfSegments() {        // the number of line segments
        return linesEdges.size();
    }

    public LineSegment[] segments() {                // the line segments
        LineSegment[] segments = new LineSegment[linesEdges.size()];

        for (int i = 0; i < linesEdges.size(); i++) {
            segments[i] = new LineSegment(linesEdges.get(i).first, linesEdges.get(i).last);
        }
        return segments;
    }

    private void addLineSegment(List<Point> lineSegmentPoints) {
        Collections.sort(lineSegmentPoints);
        LineEdges tmp = new LineEdges(lineSegmentPoints.get(0), lineSegmentPoints.get(lineSegmentPoints.size() - 1));
        for (LineEdges lineEdges :
                linesEdges) {
            if (lineEdges.first.equals(tmp.first) && lineEdges.last.equals(tmp.last)) return;
        }
        linesEdges.add(tmp);

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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    private class LineEdges {
        private Point first;
        private Point last;

        LineEdges(Point first, Point last) {
            this.first = first;
            this.last = last;

        }
    }
}