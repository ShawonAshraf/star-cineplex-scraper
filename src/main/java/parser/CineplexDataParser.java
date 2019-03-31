package parser;

import model.Dates;
import model.Location;
import model.Movie;
import model.RawData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CineplexDataParser implements Parser {
    public CineplexDataParser() {
        System.out.println("CineplexParser init");
    }

    @Override
    public ArrayList<Location> parse(ArrayList<RawData> rawData) {
        // fetch locationNames
        ArrayList<String> locationNames = new ArrayList<>();
        rawData.forEach(k -> locationNames.add(k.getLocation()));
        ArrayList<Location> locations = new ArrayList<>();


        for (int i = 0; i < locationNames.size(); i++) {
            // current location object
            String locationName = locationNames.get(i);
            Location location = new Location(locationName);

            // get dates for this location
            // contained in a set here
            var dates = rawData.get(i).getMovieDates();
            dates.keySet().forEach(date -> {
                var moviesOnThisDate = dates.get(date);
                // create a dates instance
                var thisDate = new Dates(date);

                moviesOnThisDate.forEach(movieOnThisDate -> {
                    // get movie name and showTimeString
                    var nameAndShowTimeString = extractNameAndShowTimeAsString(movieOnThisDate);
                    var name = nameAndShowTimeString[0];
                    var showTimes = extractIndividualShowTimes(nameAndShowTimeString[1]);

                    // create a movie instance
                    Movie movie = new Movie(name, showTimes);
                    thisDate.getMoviesOnThisDate().add(movie);
                });

                location.getDates().add(thisDate);
            });

            // add to locations list
            locations.add(location);
        }

        return null;
    }

    // extract movie name and showTimeString
    public String[] extractNameAndShowTimeAsString(String info) {
        return info.split("\n");
    }


    // extract individual show times from movieTimes string
    // for example, from 11:00 AM 04:50 PM to [ 11:00 AM, 04:50 PM ]
    public ArrayList<String> extractIndividualShowTimes(String rawTimeString) {
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
