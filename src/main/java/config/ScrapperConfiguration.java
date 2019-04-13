package config;

@SuppressWarnings("WeakerAccess")
public class ScrapperConfiguration {
    // props
    public static final String cineplexURLString;
    public static final String blockbusterURLString;

//    public static String chromeDriverURLString;

    // static initializer
    static {
        cineplexURLString = "http://www.cineplexbd.com/cineplexbd/showtime";
        blockbusterURLString = "";
    }
}
