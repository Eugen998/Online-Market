import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ShoppingCart extends ItemList implements Visitor {
    public double buget;

    public ShoppingCart() {
        super(new SCComparator());
    }

    public ShoppingCart(double buget) {
        super(new SCComparator());
        this.buget = buget;
    }

    @Override
    public boolean add(Item element) {
        if (buget < element.getPrice()) return false;
        if (isEmpty()) {  //daca lista este goala
            head = new Node(element);
            size++;
            buget = buget - element.getPrice();
            return true;
        }
        if (c.compare(element, head.getItem()) < 0) {    //daca trebuie adaugat inaintea primului element
            Node<Item> ins = new Node(element);
            Node<Item> aux = head;
            head = ins;
            head.setNext(aux);
            aux.setPrev(head);
            size++;
            buget = buget - element.getPrice();
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
                buget = buget - element.getPrice();
                return true;
            }
        }
        while (aux2 != null) {
            if (c.compare(element, aux1.getItem()) > 0 && c.compare(element, aux2.getItem()) < 0) {
                Node<Item> ins = new Node(element, aux2, aux1);
                aux1.setNext(ins);
                aux2.setPrev(ins);
                size++;
                buget = buget - element.getPrice();
                return true;
            }
            aux1 = aux2;
            aux2 = aux2.getNext();
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Item> c) {
        if (c.isEmpty()) return false;
        for (Iterator<? extends Item> it = c.iterator(); it.hasNext(); ) {
            add(it.next());
        }
        return true;
    }

    @Override
    public Item remove(int index) {
        if (isEmpty()) return null;
        if (size == 1) {
            if (index == 0) {
                Node<Item> aux = head;
                head = null;
                size = 0;
                buget = buget + aux.getItem().getPrice();
                return aux.getItem();
            }
            throw new NoSuchElementException();
        }
        if (index == 0) {
            Node<Item> aux = head;
            head = head.getNext();
            size--;
            buget = buget + aux.getItem().getPrice();
            return aux.getItem();

        }
        Node<Item> aux = getNode(index);
        aux.getPrev().setNext(aux.getNext());
        aux.getNext().setPrev(aux.getPrev());
        size--;
        buget = buget + aux.getItem().getPrice();
        return aux.getItem();


    }

    @Override
    public boolean removeAll(Collection<? extends Item> c) {
        if (c.isEmpty()) return false;
        for (Iterator<? extends Item> it = c.iterator(); it.hasNext(); ) {
            remove(indexOf(it.next()));
        }
        return true;
    }
}
