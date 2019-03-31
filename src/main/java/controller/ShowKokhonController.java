package controller;

import model.RawData;
import parser.CineplexDataParser;
import parser.Parser;
import scraper.CineplexScraper;
import scraper.Scraper;
import utility.JSONHelper;
import utility.Serializer;

import java.util.ArrayList;

public class ShowKokhonController {
    private Scraper cineplexScraper;
    private Parser cineplexParser;

    private final String rawDataFilePath = "output_dir/data.ser";
    private final String parsedDataFilePath = "output_dir/parsedData.ser";

    private final String outputJSONFilePath = "output_dir/cineplex_data.json";

    public ShowKokhonController() {
        cineplexScraper = new CineplexScraper();
        cineplexParser = new CineplexDataParser();
    }

    // run the controller
    public void run() {
        scrapCineplexData();
        parseScrappedData();
        writeJSON();
    }

    public void scrapCineplexData() {
        // scrap
        var scrappedData = cineplexScraper.scrap();
        Serializer.writeToFile(rawDataFilePath, scrappedData);


    }

    public void parseScrappedData() {
        // parse
        var scrappedData = (ArrayList<RawData>) Serializer.readFromFile(rawDataFilePath);
        var parsedData = cineplexParser.parse(scrappedData);
        Serializer.writeToFile(parsedDataFilePath, parsedData);
    }

    public void writeJSON() {
        // write to json
        var parsedData = Serializer.readFromFile(parsedDataFilePath);
        var jsonString = JSONHelper.parseObjectToJSONString(parsedData);
        JSONHelper.writeJSONStringToFile(jsonString, outputJSONFilePath);
    }
}
