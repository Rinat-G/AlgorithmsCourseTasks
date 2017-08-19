import org.junit.Test;

import java.util.Comparator;

public class PointTest {

    @Test
    public void testComparator(){
        Point point = new Point(2,2);
        Point point2 = new Point (2,2);
        Point point3 = new Point (2,2);
        Comparator<Point> comparator = point.slopeOrder();
        System.out.println((comparator.compare(point2, point3)));
    }
}
