import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SplittableRandom;

public class Test {
    public static void main(String args[]) throws FileNotFoundException {
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);
        String s = sc.nextLine();
        int id;
        Store.getInstance().name = s;
        System.out.println("Am creat magazinul " + Store.getInstance().name);
        while (sc.hasNextLine()) {
            s = sc.nextLine();
            Scanner r = new Scanner(s);
            r.useDelimiter(";");
            String depType = r.next();
            id = r.nextInt();
            if (depType.equals("BookDepartment")) {
                Store.getInstance().addDepartment(new BookDepartment(id, depType));
            } else if (depType.equals("MusicDepartment")) {
                Store.getInstance().addDepartment(new MusicDepartment(id, depType));
            } else if (depType.equals("SoftwareDepartment")) {
                Store.getInstance().addDepartment(new SoftwareDepartment(id, depType));
            } else if (depType.equals("VideoDepartment")) {
                Store.getInstance().addDepartment(new VideoDepartment(id, depType));
            }
            s = sc.nextLine();
            int nr = Integer.parseInt(s);
            System.out.println(nr + " produse");
            while (nr > 0) {
                s = sc.nextLine();
                r = new Scanner(s);
                r.useDelimiter(";");
                Item a = new Item();
                a.setName(r.next());
                a.setId(r.nextInt());
                a.setPrice(r.nextDouble());
                a.setDepartment(Store.getInstance().getDepartment(id).getName());
                Store.getInstance().getDepartment(id).addItem(a);
                nr--;
            }
            System.out.println(Store.getInstance().getDepartment(id));
            System.out.println(Store.getInstance().getDepartment(id).getItems());
        }
        file = new File(args[1]);
        sc = new Scanner(file);
        int nr = Integer.parseInt(sc.nextLine());
        System.out.println(nr + " clienti");
        Scanner r;
        while (nr > 0) {
            s = sc.nextLine();
            r = new Scanner(s);
            r.useDelimiter(";");
            Customer c = new Customer();
            c.name = r.next();
            c.shoppingCart = Store.getInstance().getShoppingCart(r.nextDouble());
            Store.getInstance().enter(c);
            nr--;
        }
        System.out.println(Store.getInstance().getCustomers());
        file = new File(args[2]);
        sc = new Scanner(file);
        nr = Integer.parseInt(sc.nextLine());
        System.out.println(nr + " evenimente");
        while (nr > 0) {
            s = sc.nextLine();
            r = new Scanner(s);
            r.useDelimiter(";");
            String event = r.next();
            if (event.equals("addItem")) {
                System.out.println("adaugare elem");
            } else if (event.equals("delItem")) {
                System.out.println("stergere element");
            } else if (event.equals("addProduct")) {
                System.out.println("adaugare produs");
            } else if (event.equals("modifyProduct")) {
                System.out.println("modificare produs");
            } else if (event.equals("delProduct")) {
                System.out.println("stergere produs");
            } else if (event.equals("getItem")) {
                System.out.println(" strategie ");
            } else if (event.equals("getTotal")) {
                System.out.println("produsele din lista clientului");
            } else if (event.equals("accept")) {
                System.out.println("accept");
            } else if (event.equals("getObservers")) {
                System.out.println(" get observers ");
            } else if (event.equals("getNotifications")) {
                System.out.println("get notif");
            }
            nr--;
        }
    }
}
