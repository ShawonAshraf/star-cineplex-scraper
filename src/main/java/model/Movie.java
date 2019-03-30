package model;

import java.util.ArrayList;

public class Movie {
    private String name;
    private ArrayList<MovieShowTime> showTimes;

    public Movie(String name, ArrayList<MovieShowTime> showTimes) {
        this.name = name;
        this.showTimes = showTimes;
    }

    public String getName() {
        return name;
    }

    public ArrayList<MovieShowTime> getShowTimes() {
        return showTimes;
    }
}
