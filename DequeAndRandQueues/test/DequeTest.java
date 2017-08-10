import edu.princeton.cs.algs4.Knuth;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Before;
import org.junit.Test;


import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


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

    @Test
    public void testIteratorIndepend() {
        Integer[] testArray = {10, 20, 30, 40, 50};
        for (Integer arrayItem :
                testArray) {
            deque.addLast(arrayItem);
        }


        for (Integer dequeItemOut :
                deque) {
            for (Integer dequeItemIn :
                    deque) {
                System.out.print(dequeItemIn);

            }
            System.out.println();
            System.out.println(dequeItemOut);
        }


    }

    @Test
    public void uRandTest() {
        for (int i = 0; i < 100; i++) {
            System.out.println(StdRandom.uniform(10));
        }
    }

    @Test

    public void prostoTest() {
        Integer[] src = {2, 5, 7, 8, 3, 0};
        Integer[] dst = new Integer[src.length];
        System.arraycopy(src, 0, dst, 0, src.length);
        for (Integer intgr :
                src) {
            System.out.println(intgr);
        }

        System.out.println();
        Knuth.shuffle(dst);

        for (Integer intgr :
                src) {
            System.out.println(intgr);
        }
        System.out.println();
        for (Integer intgr :
                dst) {
            System.out.println(intgr);
        }

    }


}
