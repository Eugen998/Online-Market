import java.util.Collection;
import java.util.ListIterator;

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

    @Override
    public boolean addAll(Collection<? extends Item> c) {
        return false;
    }

    @Override
    public Item remove(int index) {
        return null;
    }

    @Override
    public boolean removeAll(Collection<? extends Item> c) {
        return false;
    }
}
