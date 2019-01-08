import java.io.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SplittableRandom;

public class Test {
    public static void main(String args[]) throws IOException {
        String s1 = "C:\\Users\\eugen\\Desktop\\JavaProjects\\TemaPOO\\test\\store.txt";
        String s2 = "C:\\Users\\eugen\\Desktop\\JavaProjects\\TemaPOO\\test\\customers.txt";
        String s3 = "C:\\Users\\eugen\\Desktop\\JavaProjects\\TemaPOO\\test\\events.txt";
        String s4 = "C:\\Users\\eugen\\Desktop\\JavaProjects\\TemaPOO\\test\\output.txt";
        FileWriter f = new FileWriter(s4);
        PrintWriter writer = new PrintWriter(f);
        File file = new File(s1);
        Scanner sc = new Scanner(file);
        String s = sc.nextLine();
        int id;
        Store.getInstance().name = s;
        writer.println("Am creat magazinul " + Store.getInstance().name);
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
            writer.println(nr + " produse");
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
            writer.println(Store.getInstance().getDepartment(id));
            writer.println(Store.getInstance().getDepartment(id).getItems());
        }
        file = new File(s2);
        sc = new Scanner(file);
        int nr = Integer.parseInt(sc.nextLine());
        writer.println(nr + " clienti");
        Scanner r;
        while (nr > 0) {
            s = sc.nextLine();
            r = new Scanner(s);
            r.useDelimiter(";");
            Customer c = new Customer();
            c.name = r.next();
            c.shoppingCart = Store.getInstance().getShoppingCart(r.nextDouble());
            c.wishList = new WishList();
            Store.getInstance().enter(c);
            nr--;
        }
        writer.println(Store.getInstance().getCustomers());
        file = new File(s3);
        sc = new Scanner(file);
        nr = Integer.parseInt(sc.nextLine());
        while (nr > 0) {
            s = sc.nextLine();
            r = new Scanner(s);
            r.useDelimiter(";");
            String event = r.next();
            if (event.equals("addItem")) {
                Item adaug = Store.getInstance().getItem(r.nextInt());
                String where = r.next();
                if (where.equals("ShoppingCart")) {
                    String n = r.next();
                    Customer c = Store.getInstance().getCustomer(n);
                    c.shoppingCart.add(adaug);
                } else if (where.equals("WishList")) {
                    String n = r.next();
                    Customer c = Store.getInstance().getCustomer(n);
                    c.wishList.add(adaug);
                }
            } else if (event.equals("delItem")) {
                Item sterg = Store.getInstance().getItem(r.nextInt());
                String where = r.next();
                if (where.equals("ShoppingCart")) {
                    String n = r.next();
                    Customer c = Store.getInstance().getCustomer(n);
                    int ind = c.shoppingCart.indexOf(sterg);
                    c.shoppingCart.remove(ind);
                } else if (where.equals("WishList")) {
                    String n = r.next();
                    Customer c = Store.getInstance().getCustomer(n);
                    int ind = c.wishList.indexOf(sterg);
                    c.wishList.remove(ind);
                }
            } else if (event.equals("addProduct")) {
                writer.println("adaugare produs");
            } else if (event.equals("modifyProduct")) {
                writer.println("modificare produs");
            } else if (event.equals("delProduct")) {
                writer.println("stergere produs");
            } else if (event.equals("getItem")) {
                writer.println(" strategie ");
            } else if (event.equals("getTotal")) {
                writer.println("produsele din lista clientului");
            } else if (event.equals("accept")) {
                writer.println("accept");
            } else if (event.equals("getObservers")) {
                writer.println(" get observers ");
            } else if (event.equals("getNotifications")) {
                writer.println("get notif");
            }
            nr--;
        }
        for (Iterator<Customer> it = Store.getInstance().getCustomers().iterator(); it.hasNext(); ) {
            Customer aux = it.next();
            writer.println("ShoppingCart-ul lui " + aux.name + ":\n" + aux.shoppingCart);
            writer.println("WishList-ul lui " + aux.name + ":\n" + aux.wishList);
        }
        writer.close();
    }
}
