package scrapper;

import config.ScrapperConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class Scrapper {
    private final String urlToScrapFrom;

    public Scrapper(String urlToScrapFrom) {
        this.urlToScrapFrom = urlToScrapFrom;
    }

    public void scrap() {
        System.out.println(String.format("Scrapping from => %s", urlToScrapFrom));

        // init the firefox driver
        try {
            URL firefoxDriverURL = getClass().getClassLoader().getResource(ScrapperConfiguration.firefoxDriverURLString);
            System.out.println("driver => " + firefoxDriverURL);
            // driver can't be null!
            assert firefoxDriverURL != null;
            System.setProperty("webdriver.gecko.driver", Paths.get(firefoxDriverURL.toURI()).toFile().getAbsolutePath());

            WebDriver webDriver = new FirefoxDriver();

            // get the url
            webDriver.get(ScrapperConfiguration.cineplexURLString);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}