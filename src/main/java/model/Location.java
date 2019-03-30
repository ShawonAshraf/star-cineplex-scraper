package model;

import java.util.ArrayList;

public class Location {
    private String name;
    private ArrayList<Movie> movies;

    public Location(String name, ArrayList<Movie> movies) {
        this.name = name;
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
