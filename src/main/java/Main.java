import config.ScrapperConfiguration;
import scrapper.Scrapper;

public class Main {
    public static void main(String[] args) {
        Scrapper cineplexScrapper = new Scrapper(ScrapperConfiguration.cineplexURLString);
        cineplexScrapper.scrap();
    }
}
