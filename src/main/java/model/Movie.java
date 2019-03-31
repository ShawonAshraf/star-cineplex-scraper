package model;

import java.util.ArrayList;

public class Movie {
    private String name;
    private ArrayList<String> showTimes;

    public Movie(String name, ArrayList<String> showTimes) {
        this.name = name;
        this.showTimes = showTimes;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getShowTimes() {
        return showTimes;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", showTimes=" + showTimes +
                '}';
    }
}
