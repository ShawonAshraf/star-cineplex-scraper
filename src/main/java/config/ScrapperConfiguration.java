package config;

@SuppressWarnings("WeakerAccess")
public class ScrapperConfiguration {
    // props
    public static final String cineplexURLString;
    public static final String blockbusterURLString;

    public static final String cineplexJSONDumpFile;
    public static final String blockbusterJSONDumpFile;

    public static final String firefoxDriverURLString;
    public static final String firefoxDriverName;
//    public static String chromeDriverURLString;

    // static initializer
    static {
        cineplexURLString = "http://www.cineplexbd.com/cineplexbd/showtime";
        blockbusterURLString = "";

        cineplexJSONDumpFile = "/scraper-dump/cineplex.json";
        blockbusterJSONDumpFile = "/scraper-dump/blockbuster.json";

        firefoxDriverURLString = "geckodriver";
        firefoxDriverName = "webdriver.gecko.driver";
    }
}
