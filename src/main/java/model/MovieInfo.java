package model;

import java.util.ArrayList;
import java.util.Date;

public class MovieInfo {
    private String location;
    private Date date;
    private ArrayList<String> showTimes;

    public MovieInfo() {
        showTimes = new ArrayList<>();
    }

    public MovieInfo(String location, Date date, ArrayList<String> showTimes) {
        this.location = location;
        this.date = date;
        this.showTimes = showTimes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<String> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(ArrayList<String> showTimes) {
        this.showTimes = showTimes;
    }
}
