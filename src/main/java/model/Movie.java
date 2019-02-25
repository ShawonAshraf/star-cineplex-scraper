package model;

import java.util.ArrayList;
import java.util.Date;

public class Movie {
    private String name;
    private ArrayList<String> showTimes;
    private ArrayList<String> locations;
    private ArrayList<Date> dates;

    public Movie(String name, ArrayList<String> showTimes, ArrayList<String> locations, ArrayList<Date> dates) {
        this.name = name;
        this.showTimes = showTimes;
        this.locations = locations;
        this.dates = dates;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getShowTimes() {
        return showTimes;
    }

    public ArrayList<String> getLocation() {
        return locations;
    }

    public ArrayList<Date> getDates() {
        return dates;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", showTimes=" + showTimes +
                ", location='" + locations + '\'' +
                ", dates=" + dates +
                '}';
    }
}
