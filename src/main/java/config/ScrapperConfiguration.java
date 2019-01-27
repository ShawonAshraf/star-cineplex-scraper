package config;

public class ScrapperConfiguration {
    // props
    public static String cineplexURLString;
    public static String blockbusterURLString;

    public static String cineplexJSONDumpFile;
    public static String blockbusterJSONDumpFile;

    public static String firefoxDriverURLString;
//    public static String chromeDriverURLString;

    // static initializer
    static {
        cineplexURLString = "http://www.cineplexbd.com/cineplexbd/showtime";
        blockbusterURLString = "";

        cineplexJSONDumpFile = "/scrapper-dump/cineplex.json";
        blockbusterJSONDumpFile = "/scrapper-dump/blockbuster.json";

        firefoxDriverURLString = "geckodriver";
    }
}
