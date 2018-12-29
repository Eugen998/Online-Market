import java.util.Iterator;

public class SoftwareDepartment extends Department {
    @Override
    public void accept(ShoppingCart shoppingCart) {
        double newPrice;
        double min = shoppingCart.getItem(0).getPrice();
        for (Iterator<Item> it = items.iterator(); it.hasNext(); ) {
            if (it.next().getPrice() < min) min = it.next().getPrice();
        }
        if (shoppingCart.buget < min) {
            for (Iterator<Item> it = shoppingCart.listIterator(); it.hasNext(); ) {
                if (it.next().getDepartment().equals("software")) {
                    newPrice = (0.8) * it.next().getPrice();
                    it.next().setPrice(newPrice);
                }
            }
        }
    }
}
