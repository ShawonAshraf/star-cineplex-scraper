package controller;

import model.RawData;
import parser.CineplexDataParser;
import parser.Parser;
import scrapper.CineplexScrapper;
import scrapper.Scrapper;
import utility.JSONHelper;
import utility.Serializer;

import java.util.ArrayList;

public class ShowKokhonController {
    private Scrapper cineplexScrapper;
    private Parser cineplexParser;

    private final String rawDataFilePath = "output_dir/data.ser";
    private final String parsedDataFilePath = "output_dir/parsedData.ser";

    private final String outputJSONFilePath = "output_dir/cineplex_data.json";

    public ShowKokhonController() {
        cineplexScrapper = new CineplexScrapper();
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
        var scrappedData = cineplexScrapper.scrap();
        Serializer.writeToFile(rawDataFilePath, scrappedData);


    }

    public void parseScrappedData() {
        // parse
        System.out.println("Parsing scrapped data .....");
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
