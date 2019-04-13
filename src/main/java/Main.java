import controller.DbController;
import controller.ShowKokhonController;

public class Main {
    public static void main(String[] args) {
        ShowKokhonController controller = new ShowKokhonController();
        controller.run();

        DbController db = new DbController();
        db.write();
    }
}
