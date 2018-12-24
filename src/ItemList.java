import java.util.ListIterator;

abstract class ItemList<Item> {
    public Node head;
    public Node tail;
    public int size;


    public class Node {
        Item item;
        Node next;
        Node prev;

        public Node(Item item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    public class ItemIterator implements ListIterator {
        private Node current = ;
        private Node next;
        private int nextIndex;

    }
}
