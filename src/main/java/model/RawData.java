package model;

import java.util.ArrayList;
import java.util.HashMap;

public class RawData {
    private String location;
    private HashMap<String, ArrayList<String>> movieDates;

    public RawData(String location) {
        this.location = location;
        movieDates = new HashMap<>();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public HashMap<String, ArrayList<String>> getMovieDates() {
        return movieDates;
    }

    public void setMovieDates(HashMap<String, ArrayList<String>> movieDates) {
        this.movieDates = movieDates;
    }

    @Override
    public String toString() {
        return "RawData{" +
                "location='" + location + '\'' +
                ", movieDates=" + movieDates +
                '}';
    }
}
