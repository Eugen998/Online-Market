import java.util.*;

public class Customer implements Observer {

    public String name;
    public ShoppingCart shoppingCart;
    public WishList wishList;
    public Vector<Notification> notifications;

    @Override
    public void update(Observable o, Object arg) {

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
}
