import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

abstract public class Department extends Observable implements Subject {
    private int id;
    private String name;
    private Vector<Item> items;
    private Vector<Customer> customers;

    public Department() {
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
        items = new Vector<Item>();
        customers = new Vector<Customer>();
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
        deleteObserver(c);
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
        super.addObserver(c);
    }

    public void removeObserver(Customer c) {
        deleteObserver(c);
    }

    public void notifyAllObservers(Notification notification) {
        //to do
    }

    public String toString() {
        return "{ " + name + " " + id + " }";
    }

    public abstract void accept(Visitor visitor);

}
