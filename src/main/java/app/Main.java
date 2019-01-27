package app;

import config.ScrapperConfiguration;
import scrapper.Scrapper;

@SuppressWarnings("WeakerAccess")
public class Main {
    public static void main(String[] args) {
        Scrapper cineplexScrapper = new Scrapper(ScrapperConfiguration.cineplexURLString);
        cineplexScrapper.scrap();
    }
}
