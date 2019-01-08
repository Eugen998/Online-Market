import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class WishList extends ItemList {
    public WishList() {
        super();
    }

    @Override
    public boolean add(Item element) {
        return super.add(element);
    }

    @Override
    public Item remove(int index) {
        return super.remove(index);
    }

    @Override
    public boolean addAll(Collection<? extends Item> c) {
        return true;
    }

    @Override
    public boolean removeAll(Collection<? extends Item> c) {
        return true;
    }
}
