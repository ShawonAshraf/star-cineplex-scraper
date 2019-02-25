package scrapper;

import config.CineplexConfig;
import config.ScrapperConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class CinplexScrapper implements Scrapper {
    private URL firefoxDriverURL;
    private WebDriver webDriver = null;

    @Override
    public void scrap() {
        System.out.println(String.format("Scrapping from => %s", CineplexConfig.urlString));

        try {
            firefoxDriverURL = getClass().getClassLoader().getResource(ScrapperConfiguration.firefoxDriverURLString);
            assert firefoxDriverURL != null;

            // set the prop
            System.setProperty(ScrapperConfiguration.firefoxDriverName,
                    Paths.get(firefoxDriverURL.toURI()).toFile().getAbsolutePath());

            // log
            System.out.println("driver in use => " + firefoxDriverURL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            // init
            webDriver = new FirefoxDriver();
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

            final String selectorText = CineplexConfig.locationNames[0];

            locations.forEach(option -> {
                if (option.getText().equals(selectorText)) {
                    System.out.println("Selecting =>" + option.getText());
                    option.click();
                }
            });
            button.click();

            // get text from timeSection
            final String timeSectionClass = CineplexConfig.timeInformationClass;
            final String dateInfoClass = CineplexConfig.dateInfoClass;

            // wait until it loads
            waiter = new WebDriverWait(webDriver, 8);
            waiter.until(ExpectedConditions.visibilityOfElementLocated(By.className(timeSectionClass)));

            var showTimes = webDriver.findElements(By.className(timeSectionClass));
            var dates = webDriver.findElements(By.className(dateInfoClass));

            dates.forEach(date -> System.out.println(date.getText()));
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
