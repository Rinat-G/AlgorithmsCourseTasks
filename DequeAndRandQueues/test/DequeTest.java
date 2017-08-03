import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by R0cky on 03.08.2017.
 */
public class DequeTest {
    Deque<Integer> deque;

    @Before
    public void init() {
        deque = new Deque<>();

    }


    @Test
    public void testIsEmpty() {
        assertTrue("Not empty", deque.isEmpty());

        deque.addFirst(123);
        assertFalse("Empty, but not must", deque.isEmpty());

        deque.removeLast();

        assertTrue("Not Empty", deque.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals("Not zero init size ", 0, deque.size());

        Random random = new Random();
        int n = random.nextInt(10);
        for (int i = 0; i < n; i++) {
            deque.addFirst(1);

        }

        assertEquals("Size not right", n, deque.size());

        for (int i = 0; i < n - 1; i++) {
            deque.removeLast();
        }

        assertEquals("Size not right", 1, deque.size());

    }

    @Test
    public void testAddFirstRemoveLast() {
        Integer[] testArray = {10, 20, 30, 40, 50};


        for (Integer arrayItem :
                testArray) {
            deque.addFirst(arrayItem);
        }

        Integer[] actualResultArray = new Integer[testArray.length];
        for (int i = 0; i < testArray.length; i++) {
            actualResultArray[i] = deque.removeLast();
        }

        assertEquals(testArray, actualResultArray);

    }

    @Test
    public void testIterator() {
        Integer[] testArray = {10, 20, 30, 40, 50};


        for (Integer arrayItem :
                testArray) {
            deque.addLast(arrayItem);
        }

        Integer[] actualArray = new Integer[testArray.length];
        int i = 0;
        for (Integer dequeItem :
                deque) {
            actualArray[i] = dequeItem;
            i++;
        }
        assertEquals(testArray, actualArray);

    }


}
