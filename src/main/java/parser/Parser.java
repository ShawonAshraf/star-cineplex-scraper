package parser;

import model.RawData;

import java.util.ArrayList;

public interface Parser {
    ArrayList<Movie> parse(ArrayList<RawData> rawData);
}
