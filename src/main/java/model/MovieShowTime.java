package model;

import java.util.ArrayList;

public class MovieShowTime {
    private ArrayList<String> showTimes;
    private String date;

    public MovieShowTime(ArrayList<String> showTimes, String date) {
        this.showTimes = showTimes;
        this.date = date;
    }

    public ArrayList<String> getShowTimes() {
        return showTimes;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "MovieShowTime{" +
                "showTimes=" + showTimes +
                ", date='" + date + '\'' +
                '}';
    }
}
