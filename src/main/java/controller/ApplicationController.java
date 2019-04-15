package controller;

public class ApplicationController {
    private DbController dbController;
    private ShowKokhonController showKokhonController;

    public ApplicationController() {
        dbController = new DbController();
        showKokhonController = new ShowKokhonController();
    }

    public void fetchFromDB() {
        dbController.read();
    }

    public void scrapAndPushToDB() {
        var rawData = showKokhonController.scrapCineplexData();
        var parsedData = showKokhonController.parseScrappedData(rawData);

        dbController.write(parsedData);
    }

    public void clearDatabase() {
        dbController.clear();
    }
}
