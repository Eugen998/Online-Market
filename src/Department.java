import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

abstract public class Department implements Subject {
    private int id;
    private String name;
    private Vector<Item> items;
    private Vector<Customer> customers;
    private Vector<Customer> observers;

    public Department() {
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
        items = new Vector<Item>();
        customers = new Vector<Customer>();
        observers = new Vector<Customer>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void enter(Customer c) {
        customers.add(c);
    }

    public void exit(Customer c) {
        observers.remove(c);
        customers.remove(c);
    }

    public Vector<Customer> getCustomers() {
        return customers;
    }

    public Vector<Item> getItems() {
        return items;
    }

    public void addItem(Item i) {
        items.add(i);
    }

    public void addObserver(Customer c) {
        observers.add(c);
    }

    public void removeObserver(Customer c) {
        observers.remove(c);
    }

    public Vector<Customer> getObservers() {
        return observers;
    }

    public void notifyAllObservers(Notification notification) {
        for (Iterator<Customer> it = observers.iterator(); it.hasNext(); ) {
            it.next().notifications.add(notification);
        }
    }

    public String toString() {
        return "{ " + name + " " + id + " }";
    }

    public Item getCheapest() {
        Item min = items.get(0);
        for (Iterator<Item> it = items.iterator(); it.hasNext(); ) {
            Item aux = it.next();
            if (aux.getPrice() < min.getPrice()) min = aux;
        }
        return min;
    }

    public Item getMostExpensive() {
        Item max = items.get(0);
        for (Iterator<Item> it = items.iterator(); it.hasNext(); ) {
            Item aux = it.next();
            if (aux.getPrice() > max.getPrice()) max = aux;
        }
        return max;
    }

    public abstract void accept(Visitor visitor);

}
