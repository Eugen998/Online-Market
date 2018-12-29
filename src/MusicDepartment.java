import java.util.Iterator;

public class MusicDepartment extends Department {
    @Override
    public void accept(ShoppingCart shoppingCart) {
        double s = 0;
        for (Iterator<Item> it = shoppingCart.listIterator(); it.hasNext(); ) {
            if (it.next().getDepartment().equals("music")) {
                s = s + it.next().getPrice();
            }
        }
        shoppingCart.buget = shoppingCart.buget + (0.1) * s;
    }
}
