import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class WishList extends ItemList {
    public WishList() {
        super(new SCComparator());
    }
}
