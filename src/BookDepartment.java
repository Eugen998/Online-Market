
public class BookDepartment extends Department {
    public BookDepartment(int id, String name) {
        super(id, name);
    }


    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
