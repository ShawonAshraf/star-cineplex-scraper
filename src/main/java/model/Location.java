package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Location implements Serializable {
    private String name;
    private ArrayList<Dates> dates;

    public Location(String name) {
        this.name = name;
        dates = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
                ", dates=" + dates +
                '}';
    }
}
