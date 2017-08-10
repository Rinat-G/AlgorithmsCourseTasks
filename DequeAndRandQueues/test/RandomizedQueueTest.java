import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by R0cky on 08.08.2017.
 */
public class RandomizedQueueTest {

    RandomizedQueue<String> rq;

    @Before
    public void init() {
        rq = new RandomizedQueue<>();

    }

    @Test
    public void testIsEmpty() {
        assertTrue("Not empty", rq.isEmpty());

        rq.enqueue("123");
        assertFalse("Empty, but not must", rq.isEmpty());

        rq.dequeue();

        assertTrue("Not Empty", rq.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals("Not zero init size ", 0, rq.size());

        Random random = new Random();
        int n = random.nextInt(10);
        for (int i = 0; i < n; i++) {
            rq.enqueue("1");

        }

        assertEquals("Size not right", n, rq.size());

        for (int i = 0; i < n - 1; i++) {
            rq.dequeue();
        }

        assertEquals("Size not right", 1, rq.size());

    }

    @Test
    public void testIterator() {
        String[] testArray = {"10", "20", "30", "40", "50"};


        for (String  arrayItem :
                testArray) {
            rq.enqueue(arrayItem);
        }

        String [] actualArray = new String[testArray.length];
        int i = 0;
        for (String  dequeItem :
                rq) {
            actualArray[i] = dequeItem;
            i++;
        }
        assertEquals(testArray.length, actualArray.length);

    }
}
