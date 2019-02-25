package scrapper;

import config.CineplexConfig;
import config.ScrapperConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class CinplexScrapper implements Scrapper {
    private URL firefoxDriverURL;
    private WebDriver webDriver = null;

    @Override
    public void initHeadlessDriver() {
        // headless init
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        // init
        webDriver = new FirefoxDriver(firefoxOptions);
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
    public void scrap() {
        initDriverURL();
        initHeadlessDriver();

        try {
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

//            dates.forEach(date -> System.out.println(date.getText()));
//            showTimes.forEach(showTime -> System.out.println(showTime.getText()));

            for (WebElement date : dates) {
                System.out.println(date.getText());
                System.out.println("=====================");
                for (WebElement showTime : showTimes) {
                    System.out.println(showTime.getText());
                }
                System.out.println();
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
    }


}
