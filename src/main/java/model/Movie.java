package model;

public class Movie {
    private String name;
    private MovieInfo info;

    public Movie(String name, MovieInfo info) {
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MovieInfo getInfo() {
        return info;
    }

    public void setInfo(MovieInfo info) {
        this.info = info;
    }
}
