package model;

import java.util.ArrayList;
import java.util.Date;

public class MovieInfo {
    private ArrayList<String> showTimes;
    private ArrayList<String> locations;
    private ArrayList<Date> dates;

    public MovieInfo() {
        showTimes = new ArrayList<>();
        locations = new ArrayList<>();
        dates = new ArrayList<>();
    }

    public MovieInfo(ArrayList<String> showTimes, ArrayList<String> locations, ArrayList<Date> dates) {
        this.showTimes = showTimes;
        this.locations = locations;
        this.dates = dates;
    }

    public ArrayList<String> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(ArrayList<String> showTimes) {
        this.showTimes = showTimes;
    }

    public ArrayList<String> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<String> locations) {
        this.locations = locations;
    }

    public ArrayList<Date> getDates() {
        return dates;
    }

    public void setDates(ArrayList<Date> dates) {
        this.dates = dates;
    }
}
