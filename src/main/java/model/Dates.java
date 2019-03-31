package model;

import java.util.ArrayList;

public class Dates {
    private String dateString;
    private ArrayList<Movie> moviesOnThisDate;

    public Dates(String dateString) {
        this.dateString = dateString;
        moviesOnThisDate = new ArrayList<>();
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public ArrayList<Movie> getMoviesOnThisDate() {
        return moviesOnThisDate;
    }

    public void setMoviesOnThisDate(ArrayList<Movie> moviesOnThisDate) {
        this.moviesOnThisDate = moviesOnThisDate;
    }

    @Override
    public String toString() {
        return "Dates{" +
                "dateString='" + dateString + '\'' +
                ", moviesOnThisDate=" + moviesOnThisDate +
                '}';
    }
}
