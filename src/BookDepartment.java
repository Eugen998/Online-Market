import java.util.Iterator;

public class BookDepartment extends Department {
    @Override
    public void accept(ShoppingCart shoppingCart) {
        for (Iterator<Item> it = shoppingCart.listIterator(); it.hasNext(); ) {
            if (it.next().getDepartment().equals("book")) {
                double newPrice = (0.9) * it.next().getPrice();
                it.next().setPrice(newPrice);
            }
        }
    }
}
