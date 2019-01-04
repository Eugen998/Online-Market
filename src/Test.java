import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String args[]) throws FileNotFoundException {
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);
        String s = sc.nextLine();
        Store.getInstance().name = s;
        while (sc.hasNextLine()) {
            s = sc.nextLine();
            Scanner r = new Scanner(s);
            r.useDelimiter(";");
            System.out.println(r.next() + " ,id: " + r.nextInt());
            s = sc.nextLine();
            int nr = Integer.parseInt(s);
            System.out.println(nr + " produse");
            while (nr > 0) {
                s = sc.nextLine();
                r = new Scanner(s);
                r.useDelimiter(";");
                System.out.println("Produs: " + r.next() + " id: " + r.nextInt() + " pret: " + r.nextFloat());
                nr--;
            }
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
            System.out.println("Nume: " + r.next() + " buget " + r.nextFloat());
            nr--;
        }
    }
}
