import org.junit.Test;

public class SomeTest {
    @Test
    public void someTest1(){

        Point[] points = new Point[8];
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int n = k + 1; n < points.length; n++) {
                        count++;

                    }

                }

            }
        }

        System.out.println(count);
    }
}
