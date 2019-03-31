package scraper;

import config.CineplexConfig;
import config.ScrapperConfiguration;
import model.RawData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

public class CineplexScraper implements Scraper {
    private URL firefoxDriverURL;
    private WebDriver webDriver = null;

    @Override
    public void initHeadlessDriver() {
        System.out.println("WebDriver init .......... @ " + new Date().toString());
        // headless init
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        // init
        webDriver = new FirefoxDriver(firefoxOptions);
        System.out.println("WebDriver init complete. @ " + new Date().toString());
    }

    @Override
    public void initDriverURL() {
        try {
            firefoxDriverURL = getClass().getClassLoader().getResource(ScrapperConfiguration.firefoxDriverURLString);
            assert firefoxDriverURL != null;

            // set the prop
            System.setProperty(ScrapperConfiguration.firefoxDriverName,
                    Paths.get(firefoxDriverURL.toURI()).toFile().getAbsolutePath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<RawData> scrap() {
        initDriverURL();
        initHeadlessDriver();

        ArrayList<RawData> data = new ArrayList<>();

        try {
            System.out.println("Scraper init .......... @ " + new Date().toString());
            System.out.println(String.format("Scrapping from => %s", CineplexConfig.urlString));
            // get the url
            webDriver.get(CineplexConfig.urlString);
            // wait until the page loads
            // since it's a crappy little faggit page
            WebDriverWait waiter = new WebDriverWait(webDriver, 15);
            waiter.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("html")));

            // start
            var button = webDriver.findElement(By.id(CineplexConfig.goButtonId));
            var locationSelector = webDriver.findElement(By.id(CineplexConfig.locationsId));
            var locations = locationSelector.findElements(By.tagName(CineplexConfig.locationSelectorTag));


            for (var location : locations) {
                for (var locationName : CineplexConfig.locationNames) {
                    if (location.getText().equals(locationName)) {
                        location.click();
                        button.click();

                        // wait until it loads
                        waiter = new WebDriverWait(webDriver, 8);
                        waiter.until(ExpectedConditions.visibilityOfElementLocated(
                                By.className(CineplexConfig.timeInformationClass)));

                        var rawData = scrapShowTimeAndDateByLocation(locationName);
                        data.add(rawData);
                    }
                }
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            // close the driver
            if (webDriver != null) {
                System.out.println("\nClosing Web Driver ....");
                webDriver.close();
            }
        }

        System.out.println("Scraping complete. @ " + new Date().toString());
        return data;
    }

    private RawData scrapShowTimeAndDateByLocation(String location) {
        RawData rawData = new RawData(location);

        var showTimes = webDriver.findElements(By.className(CineplexConfig.timeInformationClass));
        var dates = webDriver.findElements(By.className(CineplexConfig.dateInfoClass));

        for (var date : dates) {
            var dateString = date.getText();

            // init rawData date
            rawData.getMovieDates().put(dateString, new ArrayList<>());

            for (var showTime : showTimes) {
                var showTimeString = showTime.getText();

                rawData.getMovieDates().get(dateString).add(showTimeString);
            }
        }

        return rawData;
    }

}
