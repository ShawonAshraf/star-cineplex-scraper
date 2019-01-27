package scrapper;

import config.ScrapperConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;


public class Scrapper {
    private final String urlToScrapFrom;

    public Scrapper(String urlToScrapFrom) {
        this.urlToScrapFrom = urlToScrapFrom;
    }

    public void scrap() {
        URL firefoxDriverURL;
        WebDriver webDriver = null;

        System.out.println(String.format("Scrapping from => %s", urlToScrapFrom));

        try {
            firefoxDriverURL = getClass().getClassLoader().getResource(ScrapperConfiguration.firefoxDriverURLString);
            assert firefoxDriverURL != null;

            // set the prop
            System.setProperty("webdriver.gecko.driver", Paths.get(firefoxDriverURL.toURI()).toFile().getAbsolutePath());

            // log
            System.out.println("driver in use => " + firefoxDriverURL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            // init
            webDriver = new FirefoxDriver();
            // get the url
            webDriver.get(ScrapperConfiguration.cineplexURLString);
            // wait until the page loads
            // since it's a crappy little faggit page
            WebDriverWait waiter = new WebDriverWait(webDriver, 15);
            waiter.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("html")));

            // start
            var button = webDriver.findElement(By.id("goShowtime"));
            var locationSelector = webDriver.findElement(By.id("locationId"));
            var locations = locationSelector.findElements(By.tagName("option"));

            final String selectorText = "Bashundhara Shopping Mall, Panthapath";

            locations.forEach(option -> {
                if (option.getText().equals(selectorText)) {
                    System.out.println("Selecting =>" + option.getText());
                    option.click();
                }
            });
            button.click();

            // get text from timeSection
            final String timeSectionClass = "col-xs-12";

            // wait until it loads
            waiter = new WebDriverWait(webDriver, 8);
            waiter.until(ExpectedConditions.visibilityOfElementLocated(By.className(timeSectionClass)));

            var showTimes = webDriver.findElements(By.className(timeSectionClass));
            showTimes.forEach(showTime -> System.out.println(showTime.getText()));

        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            // close the driver
            if (webDriver != null) {
                System.out.println("\nClosing Web Driver ....");
                webDriver.close();
            }
        }
    }
}
