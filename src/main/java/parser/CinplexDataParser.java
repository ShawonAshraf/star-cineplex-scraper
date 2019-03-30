package parser;

import model.Location;
import model.RawData;

import java.util.ArrayList;

public class CinplexDataParser implements Parser {
    @Override
    public ArrayList<Location> parse(ArrayList<RawData> rawData) {
        // fetch locations
        ArrayList<String> locations = new ArrayList<>();
        rawData.forEach(k -> locations.add(k.getLocation()));

        // check for bashundhara
        var bashundhara = locations.get(0);


        // get movieData
        var movieDates = rawData.get(0).getMovieDates();
        var moviesOnADate = movieDates.get("Thursday, April 4, 2019");
        var movieA = moviesOnADate.get(0);

        String[] tokens = movieA.split("\n");
        String movieName = tokens[0];
        String timing = tokens[1];

        System.out.println(movieName);
        System.out.println(timing);

        return null;
    }
}
