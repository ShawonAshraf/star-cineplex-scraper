package model;

import java.util.ArrayList;

public class Location {
    private String name;
    private ArrayList<Date> dates;

    public Location(String name, ArrayList<Date> dates) {
        this.name = name;
        this.dates = dates;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Date> getDates() {
        return dates;
    }
}
