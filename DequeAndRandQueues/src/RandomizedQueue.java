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
        if(item == null) throw new java.lang.IllegalArgumentException("Item must be not null");
        if (size == rq.length) resize(2*rq.length);
        rq[size] = item;
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int randIndex = StdRandom.uniform(size);

        if (size > 0 && size == rq.length/4) resize(rq.length/2);
        return null;
    }

    public Item sample() {
        return null;
    }

    public Iterator<Item> iterator() {
        return null;
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

    public static void main(String[] args) {
    }
}
