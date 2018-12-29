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

    public Department getBookDepartment() {
        for (Iterator<Department> it = departments.iterator(); it.hasNext(); ) {
            if (it.next() instanceof BookDepartment) return it.next();
        }
        return null;
    }

    public Department getMusicDepartment() {
        for (Iterator<Department> it = departments.iterator(); it.hasNext(); ) {
            if (it.next() instanceof MusicDepartment) return it.next();
        }
        return null;
    }

    public Department getSoftwareDepartment() {
        for (Iterator<Department> it = departments.iterator(); it.hasNext(); ) {
            if (it.next() instanceof SoftwareDepartment) return it.next();
        }
        return null;
    }

    public Department getVideoDepartment() {
        for (Iterator<Department> it = departments.iterator(); it.hasNext(); ) {
            if (it.next() instanceof VideoDepartment) return it.next();
        }
        return null;
    }

}
