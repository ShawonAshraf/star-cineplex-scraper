package parser;

import model.Location;
import model.RawData;

import java.util.ArrayList;

public interface Parser {
    ArrayList<Location> parse(ArrayList<RawData> rawData);
}
