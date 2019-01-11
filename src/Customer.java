import java.util.*;

public class Customer implements Observer {

    public String name;
    public ShoppingCart shoppingCart;
    public WishList wishList;
    public Vector<Notification> notifications = new Vector<>();

    @Override
    public void update(Notification notification) {
        notifications.add(notification);
    }

    public String toString() {
        return "{ " + name + " , buget: " + shoppingCart.buget + " }";
    }

    public String getNotifications() {
        StringJoiner s = new StringJoiner(",", "[", "]");
        for (Iterator<Notification> it = notifications.iterator(); it.hasNext(); ) {
            s.add(it.next().toString());
        }
        return s.toString();
    }

    public boolean checkIfObserver(Department department) {
        for (ItemList.ItemIterator it = wishList.listIterator(); it.hasNext(); ) {
            if (it.next().getDepartment().equals(department.getName())) return true;
        }
        return false;
    }

    public boolean checkIfCustomer(Department department) {
        for (ItemList.ItemIterator it = shoppingCart.listIterator(); it.hasNext(); ) {
            if (it.next().getDepartment().equals(department.getName())) return true;
        }
        return false;
    }
}
