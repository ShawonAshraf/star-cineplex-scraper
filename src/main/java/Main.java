import model.RawData;
import parser.CineplexDataParser;
import parser.Parser;
import utility.Serializer;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        Scrapper cineplexScrapper = new CinplexScrapper();
//        var scrappedData = cineplexScrapper.scrap();
//
//        Serializer.writeToFile("data.ser", scrappedData);

        ArrayList<RawData> data = (ArrayList<RawData>) Serializer.readFromFile("data.ser");
        Parser parser = new CineplexDataParser();

        parser.parse(data);

    }
}
