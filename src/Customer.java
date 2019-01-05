import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

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
}
