import java.util.Vector;

public class Store {
    private static Store s = null;
    public String name;
    public Vector<Customer> Customers;
    public Vector<Department> Departments;

    public Store() {
    }

    public Store(String name) {
        this.name = name;
        Customers = null;
        Departments = null;
    }

    public static Store getInstance() {
        if (s == null)
            s = new Store();
        return s;
    }

    public void enter(Customer c) {
        Customers.add(c);
    }

    public void exit(Customer c) {
        Customers.remove(c);
    }

    public ShoppingCart getShoppingCart(Double b) {
        //to do
        return null;
    }

    public Vector<Customer> getCustomers() {
        return Customers;
    }

    public Vector<Department> getDepartments() {
        return Departments;
    }

    public void addDepartment(Department d) {
        Departments.add(d);
    }

    public Department getDepartment(Integer id) {
        //to do
        return null;
    }
}
