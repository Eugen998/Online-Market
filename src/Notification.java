import java.util.Date;

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
}
