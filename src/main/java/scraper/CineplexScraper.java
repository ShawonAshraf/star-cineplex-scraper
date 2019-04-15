package scraper;

import config.CineplexConfig;
import io.github.bonigarcia.wdm.DriverManagerType;
import model.RawData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

public class CineplexScraper implements Scraper {
    private WebDriver webDriver = null;

    @Override
    public void setupWebDriver() {
        System.out.println("WebDriver setup started ......... @ " + new Date().toString());

        // driver url
        var driverURL = this.getClass()
                .getClassLoader()
                .getResource("geckodriver");

        try {
            System.setProperty(
                    "webdriver.gecko.driver",
                    Paths.get(driverURL.toURI()).toFile().getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
        // configure headless driver
        FirefoxBinary binary = new FirefoxBinary();
        binary.addCommandLineOptions("--headless");

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(binary);

        webDriver = new FirefoxDriver(options);
        System.out.println("WebDriver setup finished ......... @ " + new Date().toString());
    }

    @Override
    public ArrayList<RawData> scrap() {
        setupWebDriver();
        ArrayList<RawData> data = new ArrayList<>();

        try {
            System.out.println("Scraper init .......... @ " + new Date().toString());
            System.out.println(String.format("Scraping from => %s", CineplexConfig.urlString));
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
                System.out.println("\nClosing Web Driver .... @ " + new Date().toString());
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
