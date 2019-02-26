package model;

import java.util.ArrayList;

public class Movie {
    private String name;
    private ArrayList<MovieInfo> info;

    public Movie(String name, ArrayList<MovieInfo> info) {
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<MovieInfo> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<MovieInfo> info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", info=" + info +
                '}';
    }
}
