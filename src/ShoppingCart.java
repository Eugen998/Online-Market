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
        for (ItemIterator it = this.listIterator(); it.hasNext(); ) {
            Item aux = it.next();
            if (aux.getDepartment().equals("BookDepartment")) {
                double newPrice = (0.9) * aux.getPrice();
                aux.setPrice(newPrice);
            }
        }
    }

    public void visit(MusicDepartment musicDepartment) {
        double s = 0;
        for (ItemIterator it = this.listIterator(); it.hasNext(); ) {
            Item aux = it.next();
            if (aux.getDepartment().equals("MusicDepartment")) {
                s = s + aux.getPrice();
            }
        }
        buget = buget + (0.1) * s;
    }

    public void visit(SoftwareDepartment softwareDepartment) {
        double newPrice;
        double min = softwareDepartment.getCheapest().getPrice();
        if (buget < min) {
            for (Iterator<Item> it = this.listIterator(); it.hasNext(); ) {
                Item aux = it.next();
                if (aux.getDepartment().equals("SoftwareDepartment")) {
                    newPrice = (0.8) * aux.getPrice();
                    aux.setPrice(newPrice);
                }
            }
        }
    }

    public void visit(VideoDepartment videoDepartment) {
        double max = videoDepartment.getMostExpensive().getPrice();
        double s = 0;
        double newPrice;
        for (ItemIterator it = this.listIterator(); it.hasNext(); ) {
            Item aux = it.next();
            if (aux.getDepartment().equals("VideoDepartment")) {
                s = s + aux.getPrice();
            }
        }
        buget = buget + (0.05) * s;
        if (s > max) {
            for (ItemIterator it = this.listIterator(); it.hasNext(); ) {
                Item aux = it.next();
                if (aux.getDepartment().equals("VideoDepartment")) {
                    newPrice = (0.85) * aux.getPrice();
                    aux.setPrice(newPrice);
                }
            }
        }
    }


}
