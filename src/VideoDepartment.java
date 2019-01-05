import java.util.Iterator;

public class VideoDepartment extends Department {
    public VideoDepartment(int id, String name) {
        super(id, name);
    }

    @Override
    public void accept(ShoppingCart shoppingCart) {
        double max = 0;
        double s = 0;
        double newPrice;
        for (Iterator<Item> it = items.iterator(); it.hasNext(); ) {
            if (it.next().getPrice() > max) max = it.next().getPrice();
        }
        for (Iterator<Item> it = shoppingCart.listIterator(); it.hasNext(); ) {
            if (it.next().getDepartment().equals("video")) {
                s = s + it.next().getPrice();
            }
        }
        shoppingCart.buget = shoppingCart.buget + (0.05) * s;
        if (s > max) {
            for (Iterator<Item> it = shoppingCart.listIterator(); it.hasNext(); ) {
                if (it.next().getDepartment().equals("video")) {
                    newPrice = (0.85) * it.next().getPrice();
                    it.next().setPrice(newPrice);
                }
            }
        }

    }
}
