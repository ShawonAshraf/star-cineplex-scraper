import parser.CineplexParser;
import scrapper.CinplexScrapper;
import scrapper.Scrapper;

public class Main {
    public static void main(String[] args) {
        Scrapper cineplexScrapper = new CinplexScrapper();
        var scrappedData = cineplexScrapper.scrap();

        scrappedData.forEach(rawData -> System.out.println(rawData));
    }
}
