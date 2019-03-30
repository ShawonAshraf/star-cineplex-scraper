package parser;

import model.Location;
import model.RawData;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CinplexDataParser implements Parser {
    public CinplexDataParser() {
        System.out.println("CineplexParser init");
    }

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

        var times = extractMovieTimes(timing);
        System.out.println(times);

        return null;
    }

    // get movie times
    public ArrayList<String> extractMovieTimes(String rawTimeString) {
        ArrayList<String> movieTimes = new ArrayList<>();

        String regex = "(\\d){2}:(\\d){2} (AM|PM)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rawTimeString);

        // poll for matches
        while (matcher.find()) {
            movieTimes.add(matcher.group());
        }

        return movieTimes;
    }
}
