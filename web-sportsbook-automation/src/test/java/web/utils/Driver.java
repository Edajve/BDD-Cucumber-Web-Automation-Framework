package web.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver driver;


    private Driver() {
    }

    public static WebDriver getDriver() throws Exception {

        String browser = System.getenv("BROWSER");
        if (browser == null) {
            switch (ConfigReader.getProperty("browser")) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addExtensions(new File("src/corsExtension.crx"));
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    DriverManagerType safari = DriverManagerType.SAFARI;
                    WebDriverManager.getInstance(safari).setup();
                    Class<?> safariClass = Class.forName(safari.browserClass());
                    driver = (WebDriver) safariClass.getDeclaredConstructor().newInstance();
                    break;
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
