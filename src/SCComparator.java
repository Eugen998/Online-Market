import java.util.Comparator;

public class SCComparator implements Comparator<Item> {
    public int compare(Item a, Item b) {
        if (a.getPrice() != b.getPrice()) return (int) (a.getPrice() - b.getPrice());
        else return a.getName().compareTo(b.getName());
    }
}
