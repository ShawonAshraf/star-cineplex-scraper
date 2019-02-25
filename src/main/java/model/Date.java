package model;

import java.util.ArrayList;

public class Date {
    private String dateString;
    private ArrayList<Movie> moviesOnTheDate;

    public Date(String dateString, ArrayList<Movie> moviesOnTheDate) {
        this.dateString = dateString;
        this.moviesOnTheDate = moviesOnTheDate;
    }

    public String getDateString() {
        return dateString;
    }

    public ArrayList<Movie> getMoviesOnTheDate() {
        return moviesOnTheDate;
    }
}
