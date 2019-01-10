import java.util.Date;
import java.util.StringJoiner;

enum NotificationType {
    ADD, REMOVE, MODIFY;
}
public class Notification {
    public Date date;
    public NotificationType notificationType;
    public int depID;
    public int prodID;

    public Notification(Date date, NotificationType notificationType, int depID, int prodID) {
        this.date = date;
        this.notificationType = notificationType;
        this.depID = depID;
        this.prodID = prodID;
    }

    @Override
    public String toString() {
        return notificationType + ";" + prodID + ";" + depID;
    }
}
