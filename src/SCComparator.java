import java.util.Comparator;

public class SCComparator implements Comparator<Item> {
    public int compare(Item a, Item b) {
        if (a.getPrice() != b.getPrice()) {
            if (a.getPrice() < b.getPrice()) return -1;
            else return 1;
        } else return a.getName().compareTo(b.getName());
    }
}
