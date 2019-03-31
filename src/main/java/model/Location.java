package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Location implements Serializable {
    private String locationName;
    private ArrayList<Dates> dates;

    public Location(String locationName) {
        this.locationName = locationName;
        dates = new ArrayList<>();
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public ArrayList<Dates> getDates() {
        return dates;
    }

    public void setDates(ArrayList<Dates> dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationName='" + locationName + '\'' +
                ", dates=" + dates +
                '}';
    }
}
