import controller.ApplicationController;
import controller.DbController;
import controller.ShowKokhonController;

public class Main {
    public static void main(String[] args) {
        ApplicationController app = new ApplicationController();
        app.scrapAndPushToDB();
        app.fetchFromDB();
    }
}
