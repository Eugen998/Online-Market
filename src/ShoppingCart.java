import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ShoppingCart extends ItemList implements Visitor {
    public double buget;

    public ShoppingCart() {
        super();
    }

    public ShoppingCart(double buget) {
        super();
        this.buget = buget;
    }

    @Override
    public boolean add(Item element) {
        if (buget > element.getPrice()) {
            super.add(element);
            buget = buget - element.getPrice();
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Item> c) {
        return true;
    }

    @Override
    public Item remove(int index) {
        Item aux = super.remove(index);
        if (aux != null) {
            buget = buget + aux.getPrice();
            return aux;
        }
        return null;
    }

    @Override
    public boolean removeAll(Collection<? extends Item> c) {
        return true;
    }

    public void visit(BookDepartment bookDepartment) {
        for (Iterator<Item> it = this.listIterator(); it.hasNext(); ) {
            if (it.next().getDepartment().equals("book")) {
                double newPrice = (0.9) * it.next().getPrice();
                it.next().setPrice(newPrice);
            }
        }
    }

    public void visit(MusicDepartment musicDepartment) {
        double s = 0;
        for (Iterator<Item> it = this.listIterator(); it.hasNext(); ) {
            if (it.next().getDepartment().equals("music")) {
                s = s + it.next().getPrice();
            }
        }
        buget = buget + (0.1) * s;
    }

    public void visit(SoftwareDepartment softwareDepartment) {
        double newPrice;
        double min = getItem(0).getPrice();
        for (Iterator<Item> it = this.listIterator(); it.hasNext(); ) {
            if (it.next().getPrice() < min) min = it.next().getPrice();
        }
        if (buget < min) {
            for (Iterator<Item> it = this.listIterator(); it.hasNext(); ) {
                if (it.next().getDepartment().equals("software")) {
                    newPrice = (0.8) * it.next().getPrice();
                    it.next().setPrice(newPrice);
                }
            }
        }
    }

    public void visit(VideoDepartment videoDepartment) {
        double max = 0;
        double s = 0;
        double newPrice;
        for (Iterator<Item> it = this.listIterator(); it.hasNext(); ) {
            if (it.next().getPrice() > max) max = it.next().getPrice();
        }
        for (Iterator<Item> it = this.listIterator(); it.hasNext(); ) {
            if (it.next().getDepartment().equals("video")) {
                s = s + it.next().getPrice();
            }
        }
        buget = buget + (0.05) * s;
        if (s > max) {
            for (Iterator<Item> it = this.listIterator(); it.hasNext(); ) {
                if (it.next().getDepartment().equals("video")) {
                    newPrice = (0.85) * it.next().getPrice();
                    it.next().setPrice(newPrice);
                }
            }
        }

    }


}
