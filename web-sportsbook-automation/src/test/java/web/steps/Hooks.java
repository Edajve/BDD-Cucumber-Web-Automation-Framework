package web.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import web.utils.Driver;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setup() throws Exception {
        driver = Driver.getDriver();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
