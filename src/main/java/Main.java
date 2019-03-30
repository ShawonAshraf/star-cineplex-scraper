import model.RawData;
import scrapper.CinplexScrapper;
import scrapper.Scrapper;
import utility.Serializer;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        Scrapper cineplexScrapper = new CinplexScrapper();
//        var scrappedData = cineplexScrapper.scrap();
//
//        Serializer.writeToFile("data.ser", scrappedData);

        ArrayList<RawData> data = (ArrayList<RawData>) Serializer.readFromFile("data.ser");
        System.out.println(data);
    }
}
