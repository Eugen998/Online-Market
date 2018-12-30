import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

abstract public class Department extends Observable implements Subject {
    public int id;
    public String name;
    public Vector<Item> items;
    public Vector<Customer> customers;
    public Department() {
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
        items = new Vector<Item>();
        customers = new Vector<Customer>();
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

    public int getId() {
        return id;
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

    public abstract void accept(ShoppingCart shoppingCart);

}
