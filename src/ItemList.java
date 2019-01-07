import java.util.*;

public abstract class ItemList {
    public Node<Item> head;
    public int size;
    Comparator<Item> c;

    public ItemList(Comparator c) {
        head = new Node();
        size = 0;
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

    public boolean add(Item element) {
        if (isEmpty()) {  //daca lista este goala
            head = new Node(element);
            size++;
            return true;
        }
        if (c.compare(element, head.getItem()) < 0) {    //daca trebuie adaugat inaintea primului element
            Node<Item> ins = new Node(element);
            Node<Item> aux = head;
            head = ins;
            head.setNext(aux);
            aux.setPrev(head);
            size++;
            return true;
        }
        Node<Item> aux1 = head;
        Node<Item> aux2 = head.getNext();
        if (aux2 == null) {
            if (c.compare(element, aux1.getItem()) > 0) {
                Node<Item> ins = new Node(element);
                head.setNext(ins);
                ins.setPrev(head);
                size++;
                return true;
            }
        }
        while (aux2 != null) {
            if (c.compare(element, aux1.getItem()) > 0 && c.compare(element, aux2.getItem()) < 0) {
                Node<Item> ins = new Node(element, aux2, aux1);
                aux1.setNext(ins);
                aux2.setPrev(ins);
                size++;
                return true;
            }
            aux1 = aux2;
            aux2 = aux2.getNext();
        }
        return false;
    }

    public boolean addAll(Collection<? extends Item> c) {
        if (c.isEmpty()) return false;
        for (Iterator<? extends Item> it = c.iterator(); it.hasNext(); ) {
            add(it.next());
        }
        return true;
    }

    public Item remove(int index) {
        if (isEmpty()) return null;
        if (size == 1) {
            if (index == 0) {
                Node<Item> aux = head;
                head = null;
                size = 0;
                return aux.getItem();
            }
            throw new NoSuchElementException();
        }
        if (index == 0) {
            Node<Item> aux = head;
            head = head.getNext();
            size--;
            return aux.getItem();

        }
        Node<Item> aux = getNode(index);
        aux.getPrev().setNext(aux.getNext());
        aux.getNext().setPrev(aux.getPrev());
        size--;
        return aux.getItem();


    }

    public boolean removeAll(Collection<? extends Item> c) {
        if (c.isEmpty()) return false;
        for (Iterator<? extends Item> it = c.iterator(); it.hasNext(); ) {
            remove(indexOf(it.next()));
        }
        return true;
    }

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

        public Node(Item item, Node<Item> next, Node<Item> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
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

    }

    public class ItemIterator implements ListIterator<Item> {
        public Node<Item> current = head;//nodul curent
        public Node<Item> last = null; //ultimul nod accesat de metoda next() sau previous()
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
