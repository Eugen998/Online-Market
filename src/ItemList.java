import java.util.*;

public abstract class ItemList {
    public Node<Item> head;
    public int size;
    Comparator<Item> c;

    public ItemList() {
        head = new Node();
        size = 0;
        c = new SCComparator();
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

    public boolean add(Item element) {
        Node<Item> aux = new Node(element);
        if (isEmpty()) {        //daca lista este goala
            head = aux;
            size++;
            return true;
        } else if (size == 1) {

            if (c.compare(element, head.item) <= 0) {
                aux.next = head;
                head.prev = aux;
                head = aux;
            } else {
                head.next = aux;
                aux.prev = head;
            }
            size++;
            return true;
        } else {
            Node<Item> aux1 = head;
            Node<Item> aux2 = head.next;
            while (aux2 != null) {
                if (c.compare(element, aux1.item) > 0 && c.compare(element, aux2.item) < 0) {
                    aux1.next = aux;
                    aux2.prev = aux;
                    aux.prev = aux1;
                    aux.next = aux2;
                    size++;
                    return true;
                } else {
                    aux1 = aux2;
                    aux2 = aux2.next;
                }
            }
            aux1.next = aux;
            aux.prev = aux1;
            size++;
            return true;
        }
    }

    public abstract boolean addAll(Collection<? extends Item> c);

    public Item remove(int index) {
        if (isEmpty()) return null;
        else if (size == 1) {
            if (index == 1) {
                Item aux = head.item;
                head = null;
                size--;
                return aux;
            } else throw new NoSuchElementException();
        } else {
            for (ListIterator<Item> it = this.listIterator(); it.hasNext(); ) {
                if (it.nextIndex() == index) {
                    Node<Item> aux = ((ItemIterator) it).current;
                    if (aux.prev == null) {
                        head = head.next;
                        head.prev = null;
                    } else if (aux.next == null) {
                        aux.prev.next = null;
                    } else {
                        aux.next.prev = aux.prev;
                        aux.prev.next = aux.next;
                    }
                    size--;
                    return aux.item;
                } else {
                    it.next();
                }
            }
        }
        return null;
    }

    public abstract boolean removeAll(Collection<? extends Item> c);

    public Item getItem(int index) {
        for (ItemIterator it = this.listIterator(); it.hasNext(); ) {
            if (it.nextIndex() == index) return it.next();
        }
        return null;
    }

    public int indexOf(Item item) {
        for (ItemIterator it = this.listIterator(); it.hasNext(); ) {
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

    public ItemIterator listIterator() {
        return new ItemIterator();
    }

    public ItemIterator listIterator(int index) {
        ItemIterator a = new ItemIterator();
        a.setIndex(index);
        return a;
    }

    public static class Node<Item> {
        public Item item;
        public Node<Item> next;
        public Node<Item> prev;

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

        public Node(Item item, Node<Item> next, Node<Item> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        public void setNext(Node<Item> next) {
            this.next = next;
        }

        public void setPrev(Node<Item> prev) {
            this.prev = prev;
        }

    }

    public class ItemIterator implements ListIterator<Item> {
        public Node<Item> current = head;   //nodul curent
        public int index = 0;

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
            Item aux = current.item;
            current = current.next;
            index++;
            return aux;
        }

        @Override
        public Item previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            Item aux = current.item;
            current = current.prev;
            index--;
            return aux;
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
