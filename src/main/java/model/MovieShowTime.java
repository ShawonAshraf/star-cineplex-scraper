package model;

import java.util.ArrayList;

public class MovieShowTime {
    private ArrayList<String> timings;
    private String date;

    public MovieShowTime(ArrayList<String> timings, String date) {
        this.timings = timings;
        this.date = date;
    }

    public ArrayList<String> getTimings() {
        return timings;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "MovieShowTime{" +
                "timings=" + timings +
                ", date='" + date + '\'' +
                '}';
    }
}
