import java.util.Collection;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

abstract class ItemList {
    public Node<Item> head;
    public int size;
    Comparator<Item> c;

    public ItemList(Comparator c) {
        this.c = c;
    }

    public boolean isEmpty() {
        if (size == 0) return true;
        return false;
    }

    public Node<Item> getNode(int index) {
        Node<Item> aux = head;
        int i = 0;
        while (i <= index) {
            if (aux.next == null) {
                if (i == index) return aux;
                return null;
            } else {
                aux = aux.next;
                i++;
            }
        }
        return aux;
    }

    public abstract boolean add(Item element);

    public abstract boolean addAll(Collection<? extends Item> c);

    public abstract Item remove(int index);

    public abstract boolean removeAll(Collection<? extends Item> c);

    public Item getItem(int index) {
        for (ListIterator<Item> it = this.listIterator(); it.hasNext(); ) {
            if (it.nextIndex() == index) return it.next();
        }
        return null;
    }

    public int indexOf(Item item) {
        for (ListIterator<Item> it = this.listIterator(); it.hasNext(); ) {
            if (it.next() == item) return it.nextIndex();
        }
        throw new NoSuchElementException();
    }

    public int indexOf(Node<Item> node) {
        Node<Item> aux = head;
        int index = 0;
        while (aux != null) {
            if (aux == node) return index;
            index++;
            aux = aux.next;
        }
        throw new NoSuchElementException();
    }

    public boolean contains(Node<Item> node) {
        Node<Item> aux = head;
        while (aux != null) {
            if (aux == node) return true;
            aux = aux.next;
        }
        return false;
    }

    public boolean contains(Item item) {
        for (ListIterator<Item> it = this.listIterator(); it.hasNext(); ) {
            if (it.next() == item) return true;
        }
        return false;
    }


    public double getTotalPrice() {
        double s = 0;
        for (ListIterator<Item> it = this.listIterator(); it.hasNext(); ) {
            s = s + it.next().getPrice();
        }
        return s;
    }

    public ListIterator<Item> listIterator() {
        return new ItemIterator();
    }

    public ListIterator<Item> listIterator(int index) {
        ListIterator a = new ItemIterator();
        ((ItemIterator) a).setIndex(index);
        return a;
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
            next = null;
            prev = null;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public Node<Item> getNext() {
            return next;
        }

        public void setNext(Node<Item> next) {
            this.next = next;
        }

        public Node<Item> getPrev() {
            return prev;
        }

        public void setPrev(Node<Item> prev) {
            this.prev = prev;
        }

        public Node(Item item, Node<Item> next, Node<Item> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    public class ItemIterator implements ListIterator<Item> {
        private Node<Item> current = head.next;//nodul curent
        private Node<Item> last = null; //ultimul nod accesat de metoda next() sau previous()
        private int index = 0;

        public void setIndex(int index) {
            this.index = index;
        }

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
