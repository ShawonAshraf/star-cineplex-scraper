package parser;

import model.Movie;
import model.MovieInfo;
import model.RawData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

public class CineplexParser implements Parser {
    @Override
    public ArrayList<Movie> parse(ArrayList<RawData> rawData) {
        ArrayList<Movie> movieList = new ArrayList<>();

        for (var data : rawData) {
            var location = data.getLocation();
            var dates = data.getMovieDates().keySet();

            for (var date : dates) {
                var movieInfoArray = data.getMovieDates().get(date);

                for (var movieInfo : movieInfoArray) {
                    // get name and show time
                    var parsedData = parseMovieInfo(movieInfo);
                    var name = parsedData.movieName;
                    var showTimes = parsedData.showTimes;

                    // create info object
                    // showTime, locations, dates
                    var info = new MovieInfo(showTimes, new ArrayList<>(), new ArrayList<>());
                    var movie = new Movie(name, info);

                    // enter data

                }
            }
        }

        return movieList;
    }

    // since Java doesn't have tuples ;_;
    private class MovieDateAndTime {
        public String movieName;
        public ArrayList<String> showTimes;

        public MovieDateAndTime(String movieName, ArrayList<String> showTimes) {
            this.movieName = movieName;
            this.showTimes = showTimes;
        }
    }

    private MovieDateAndTime parseMovieInfo(String movieInfo) {
        var splitData = movieInfo.split(" ");

        var name = splitData[0];
        var showTimes = parseShowTimes(splitData[1]);

        return new MovieDateAndTime(name, showTimes);
    }

    // use regex to get showtimes from the string
    private ArrayList<String> parseShowTimes(String showTimes) {
        ArrayList<String> times = new ArrayList<>();
        var regex = "(\\d){2}:(\\d){2} (AM|PM)";

        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(showTimes);

        while (matcher.find()) {
            times.add(matcher.group());
        }

        return times;
    }

    private Date parseDateFromString(String dateString) {
        var dateFormat = "EEEE, MMMM d, yyyy";
        Date date = null;

        try {
            date = new SimpleDateFormat(dateFormat).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
