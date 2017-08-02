import java.util.Iterator;

/**
 * Created by R0cky on 28.07.2017.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Node next;
        Node previous;
        private final Item item;

        Node(Item item) {
            this.item = item;
            next = null;
            previous = null;
        }

    }

    public Deque() {                           // construct an empty deque
        first = last = null;
        size = 0;
    }

    public boolean isEmpty() {                 // is the deque empty?
        return (size == 0);
    }

    public int size() {                       // return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) {       // add the item to the front
        if (item == null) throw new IllegalArgumentException();
        Node newNode = new Node(item);
        if (first != null) {
            newNode.next = first;
            first.previous = newNode;
        }
        first = newNode;

    }

    public void addLast(Item item) {          // add the item to the end
        if (item.equals(null)) throw new IllegalArgumentException();

    }

    public Item removeFirst() {              // remove and return the item from the front
    }

    public Item removeLast() {             // remove and return the item from the end
    }

    public Iterator<Item> iterator() {       // return an iterator over items in order from front to end
    }

    public static void main(String[] args) {

    }

}
