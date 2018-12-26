import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

abstract class ItemList<Item> implements Iterable<Item> {
    public Node after;
    public Node before;
    public int size;

    //    public ItemList(){
//        before = new Node();
//        after = new Node();
//        before.next = after;
//        after.prev = before;
//    }
    public boolean isEmpty() {
        if (size == 0) return true;
        return false;
    }

    public class Node {
        Item item;
        Node next;
        Node prev;
    }

    public class ItemIterator implements ListIterator<Item> {
        private Node current = before.next;
        private Node last = null;
        private int index = 0;

        public boolean hasNext() {
            return index < size;
        }

        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            last = current;
            Item aux = current.item;
            current = current.next;
            index++;
            return aux;
        }

        @Override
        public Item previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            current = current.prev;
            index--;
            last = current;
            return current.item;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            if (last != null) {
                Node aux1 = last.prev;
                Node aux2 = last.next;
                aux1.next = aux2;
                aux2.prev = aux1;
                size--;
                if (current == last)
                    current = aux2;
                else
                    index--;
                last = null;

            }
        }

        @Override
        public void set(Item item) {
            if (last != null) {
                last.item = item;
            }
        }

        @Override
        public void add(Item item) {

        }
    }

}
