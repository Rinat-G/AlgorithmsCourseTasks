import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    private static class Node<Item> {
        Node<Item> next;
        Node<Item> previous;
        private final Item item;

        Node(Node<Item> previous, Item item, Node<Item> next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
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
        if (item == null) throw new IllegalArgumentException("Item cannot be null");

        final Node<Item> f = first;
        final Node<Item> newNode = new Node<>(null, item, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.previous = newNode;
        }
        size++;

    }

    public void addLast(Item item) {          // add the item to the end
        if (item == null) throw new IllegalArgumentException("Item cannot be null");

        final Node<Item> l = last;
        final Node<Item> newNode = new Node<>(l, item, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public Item removeFirst() {              // remove and return the item from the front
        final Node<Item> f = first;
        if (f == null) throw new java.util.NoSuchElementException("Cant remove element from empty deque");

        if (f.next == null) {
            first = null;
            last = null;
        } else {
            first = f.next;
            first.previous = null;
        }
        size--;
        return f.item;
    }

    public Item removeLast() {             // remove and return the item from the end
        final Node<Item> l = last;
        if (l == null) throw new java.util.NoSuchElementException("Cant remove element from empty deque");

        if (l.previous == null) {
            first = null;
            last = null;
        } else {
            last = l.previous;
            last.next = null;
        }
        size--;
        return l.item;
    }

    public Iterator<Item> iterator() {       // return an iterator over items in order from front to end
        return new DeqItr(first);
    }

    private class DeqItr implements Iterator<Item> {
        Node<Item> first;
        //        Node<Item> last;
        Node<Item> next;

        DeqItr(Node<Item> first) {
            this.first = first;
//            this.last = last;
            this.next = first;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException("No more elements");
            Node<Item> n = next;
            next = n.next;
            return n.item;
        }
    }

    public static void main(String[] args) {

    }

}
