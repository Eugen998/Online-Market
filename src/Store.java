import java.util.Iterator;
import java.util.Vector;

public class Store {
    private static Store s = null;
    public String name;
    public Vector<Customer> customers;
    public Vector<Department> departments;

    public Store() {
    }

    public Store(String name) {
        this.name = name;
        customers = null;
        departments = null;
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
        //to do
        return null;
    }

    public Vector<Customer> getCustomers() {
        return customers;
    }

    public Vector<Department> getDepartments() {
        return departments;
    }

    public void addDepartment(Department d) {
        departments.add(d);
    }

    public Department getDepartment(Integer id) {
        for (Iterator<Department> it = departments.iterator(); it.hasNext(); ) {
            if (it.next().getId() == id) return it.next();
        }
        return null;
    }
}
