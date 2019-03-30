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

        var movieDates = data.get(0).getMovieDates();

        String key = "Thursday, April 4, 2019";
        var movies = movieDates.get(key);

//        System.out.println(movies);

        var movieA = movies.get(0);

    }
}
