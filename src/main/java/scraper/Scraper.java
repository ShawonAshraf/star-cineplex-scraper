package scraper;

import model.RawData;

import java.util.ArrayList;

public interface Scraper {
    ArrayList<RawData> scrap();
    void initHeadlessDriver();
    void initDriverURL();
}
