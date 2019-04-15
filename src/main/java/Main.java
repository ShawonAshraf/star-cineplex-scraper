import controller.ApplicationController;

public class Main {
    public static void main(String[] args) {
        ApplicationController app = new ApplicationController();
        app.scrapAndPushToDB();
        app.fetchFromDB();
    }
}
