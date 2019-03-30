import model.RawData;
import parser.CinplexDataParser;
import parser.Parser;
import scrapper.CinplexScrapper;
import scrapper.Scrapper;
import utility.Serializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
//        Scrapper cineplexScrapper = new CinplexScrapper();
//        var scrappedData = cineplexScrapper.scrap();
//
//        Serializer.writeToFile("data.ser", scrappedData);

        ArrayList<RawData> data = (ArrayList<RawData>) Serializer.readFromFile("data.ser");
        Parser parser = new CinplexDataParser();

        parser.parse(data);

    }
}
