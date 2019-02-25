package parser;

import model.Movie;
import model.MovieInfo;
import model.RawData;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
    }

    private MovieDateAndTime parseMovieInfo(String movieInfo) {
        return null;
    }

    private Date parseDateFromString(String dateString) {
        return null;
    }
}
