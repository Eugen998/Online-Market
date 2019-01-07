import java.util.Iterator;

public class SoftwareDepartment extends Department {
    public SoftwareDepartment(int id, String name) {
        super(id, name);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
