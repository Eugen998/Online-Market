import java.util.Comparator;

public class SCComparator implements Comparator<Item> {
    public int compare(Item a, Item b) {
        if (a.getPrice() != b.getPrice()) return (int) (b.getPrice() - a.getPrice());
        else return b.getName().compareTo(a.getName());
    }
}
