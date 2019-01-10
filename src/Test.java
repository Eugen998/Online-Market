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
        }
        file = new File(s2);
        sc = new Scanner(file);
        int nr = Integer.parseInt(sc.nextLine());
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
        file = new File(s3);
        sc = new Scanner(file);
        nr = Integer.parseInt(sc.nextLine());
        while (nr > 0) {
            s = sc.nextLine();
            r = new Scanner(s);
            r.useDelimiter(";");
            String event = r.next();
            if (event.equals("addItem")) {
                Item search = Store.getInstance().getItem(r.nextInt());
                Item adaug = new Item();
                adaug.setId(search.getId());
                adaug.setDepartment(search.getDepartment());
                adaug.setPrice(search.getPrice());
                adaug.setName(search.getName());
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
                int depId = r.nextInt();
                int itemId = r.nextInt();
                double p = r.nextDouble();
                String n = r.next();
                Department d = Store.getInstance().getDepartment(depId);
                Item adaug = new Item();
                adaug.setName(n);
                adaug.setId(itemId);
                adaug.setDepartment(d.getName());
                adaug.setPrice(p);
                d.addItem(adaug);
                //Notify observers!!!!
            } else if (event.equals("modifyProduct")) {
                int depId = r.nextInt();
                int itemId = r.nextInt();
                double newPrice = r.nextDouble();
                Item find = Store.getInstance().getItem(itemId);
                Item mod = new Item();
                mod.setPrice(newPrice);
                mod.setDepartment(find.getDepartment());
                mod.setId(find.getId());
                mod.setName(find.getName());
                for (Iterator<Customer> it = Store.getInstance().getCustomers().iterator(); it.hasNext(); ) {
                    Customer c = it.next();
                    if (c.shoppingCart.contains(find)) {
                        int index = c.shoppingCart.indexOf(find);
                        double val = c.shoppingCart.buget + find.getPrice();
                        if (val >= mod.getPrice()) {
                            c.shoppingCart.remove(index);
                            c.shoppingCart.add(mod);
                        } else {
                            c.shoppingCart.remove(index);
                        }
                    }
                    if (c.wishList.contains(find)) {
                        int index = c.wishList.indexOf(find);
                        c.wishList.remove(index);
                        c.wishList.add(mod);
                    }
                }
                find.setPrice(newPrice);
                //NOTIFY OBSERVERS!!!!
            } else if (event.equals("delProduct")) {
                int delId = r.nextInt();
                Item find = Store.getInstance().getItem(delId);
                for (Iterator<Customer> it = Store.getInstance().getCustomers().iterator(); it.hasNext(); ) {
                    Customer c = it.next();
                    if (c.shoppingCart.contains(find)) {
                        int index = c.shoppingCart.indexOf(find);
                        c.shoppingCart.remove(index);
                    }
                    if (c.wishList.contains(find)) {
                        int index = c.wishList.indexOf(find);
                        c.wishList.remove(index);
                    }
                }
                Store.getInstance().delItem(find);
            } else if (event.equals("getItem")) {
                writer.println(" strategia nu este implementata ");
            } else if (event.equals("getItems")) {
                String from = r.next();
                String who = r.next();
                Customer victim = Store.getInstance().getCustomer(who);
                if (from.equals("ShoppingCart")) {
                    writer.println(victim.shoppingCart);
                } else writer.println(victim.wishList);
            } else if (event.equals("getTotal")) {
                String from = r.next();
                String who = r.next();
                Customer victim = Store.getInstance().getCustomer(who);
                if (from.equals("ShoppingCart")) {
                    writer.println(victim.shoppingCart.getTotalPrice());
                } else writer.println(victim.wishList.getTotalPrice());

            } else if (event.equals("accept")) {
                writer.println("accept");
            } else if (event.equals("getObservers")) {
                int depId = r.nextInt();
            } else if (event.equals("getNotifications")) {
                String who = r.next();
                Customer victim = Store.getInstance().getCustomer(who);
                writer.println(victim.getNotifications());
            }
            nr--;
        }
        writer.close();
    }
}
