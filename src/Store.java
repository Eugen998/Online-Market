import java.util.Currency;
import java.util.Iterator;
import java.util.Vector;

public class Store {
    private static Store s = null;
    public String name;
    public Vector<Customer> customers;
    public Vector<Department> departments;

    private Store() {
        name = new String();
        customers = new Vector<Customer>();
        departments = new Vector<Department>();
    }

    public static Store getInstance() {
        if (s == null)
            s = new Store();
        return s;
    }

    public void enter(Customer c) {
        customers.add(c);
    }

    public void exit(Customer c) {
        customers.remove(c);
    }

    public ShoppingCart getShoppingCart(Double b) {
        return new ShoppingCart(b);
    }

    public Vector<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomer(String name) {
        for (Iterator<Customer> it = customers.iterator(); it.hasNext(); ) {
            Customer ret = it.next();
            if (ret.name.equals(name)) return ret;
        }
        return null;
    }

    public Vector<Department> getDepartments() {
        return departments;
    }

    public void addDepartment(Department d) {
        departments.add(d);
    }

    public Department getDepartment(int id) {
        for (Iterator<Department> it = departments.iterator(); it.hasNext(); ) {
            Department d = it.next();
            int comp = d.getId();
            if (comp == id) return d;
        }
        return null;
    }

    public Item getItem(int id) {
        for (Iterator<Department> it = Store.getInstance().getDepartments().iterator(); it.hasNext(); ) {
            Department aux = it.next();
            for (Iterator<Item> iter = aux.getItems().iterator(); iter.hasNext(); ) {
                Item i = iter.next();
                if (i.getId() == id) return i;
            }
        }
        return null;
    }

}
