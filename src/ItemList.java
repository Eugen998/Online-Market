import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

abstract class ItemList {
    public Node after;
    public Node before;
    public int size;
    Comparator c;

    public ItemList(Comparator c) {
        this.c = c;
    }

    public boolean isEmpty() {
        if (size == 0) return true;
        return false;
    }

    public Node<Item> getNode(int index) {
        Node<Item> aux = before;
        int i = 0;
        while (aux != after) {
            i++;
            if (i == index) return aux;
            aux = aux.next;
        }
        return null;
    }

    public abstract boolean add(Item element);

    public Item getItem(int index) {
        for (ListIterator<Item> it = listIterator(); it.hasNext(); ) {
            if (it.nextIndex() == index) return it.next();
        }
        return null;
    }

    public ListIterator<Item> listIterator() {
        return new ItemIterator();
    }

    public static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;

        public Node() {
            item = null;
            next = null;
            prev = null;
        }

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> next, Node<Item> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    public class ItemIterator implements ListIterator<Item> {
        private Node<Item> current = before.next;//nodul curent
        private Node<Item> last = null; //ultimul nod accesat de metoda next() sau previous()
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
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(Item item) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(Item item) {
            throw new UnsupportedOperationException();
        }
    }

}
