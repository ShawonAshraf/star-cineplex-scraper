package scrapper;

import model.RawData;

import java.util.ArrayList;

public interface Scrapper {
    ArrayList<RawData> scrap();
    void initHeadlessDriver();
    void initDriverURL();
}
