import edu.princeton.cs.algs4.Knuth;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    Item[] rq;


    public RandomizedQueue() {
        size = 0;
        rq = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException("Item must be not null");
        if (size == rq.length) resize(2 * rq.length);
        rq[size] = item;
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int randIndex = StdRandom.uniform(size);
        Item item = rq[randIndex];
        Item last = rq[size - 1];
        rq[randIndex] = last;
        rq[size - 1] = null;
        size--;
        if (size > 0 && size == rq.length / 4) resize(rq.length / 2);
        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int randIndex = StdRandom.uniform(size);
        return rq[randIndex];
    }

    public Iterator<Item> iterator() {
        return new RandQueueItr();
    }

    private void resize(int capacity) {
        assert capacity >= size;
        Item[] temp = (Item[]) new Object[capacity];

//        for (int i = 0; i < size; i++) {
//            temp[i] = rq[i];
//        }
        System.arraycopy(rq, 0, temp, 0, size);
        rq = temp;
//        first = 0;
//        last  = n;
    }

    private class RandQueueItr implements Iterator<Item> {
        private Item[] iterRQ;
        private int i;

        RandQueueItr() {
            iterRQ = (Item[]) new Object[size];
            System.arraycopy(rq, 0, iterRQ, 0, size);
            Knuth.shuffle(iterRQ);
            i = 0;
        }

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = iterRQ[i];
            i++;
            return item;
        }
    }

    public static void main(String[] args) {
    }
}
