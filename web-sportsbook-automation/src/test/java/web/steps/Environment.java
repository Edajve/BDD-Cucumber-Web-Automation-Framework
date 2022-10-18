package web.steps;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.pages.home.HomePage;
import web.utils.ConfigReader;

import java.time.Duration;

public class Environment {

    WebDriver driver = Hooks.driver;
    WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(40));
    HomePage homePage = new HomePage(Hooks.driver);

    public Environment() throws Exception {
    }

    @Given("^user goes to sportsbook homepage$")
    public void userGoesToSportsbookHomepage() throws Exception {
        switch (ConfigReader.getProperty("webEnvironment")) {
            case "local":
                driver.get("http://localhost:9000/en-us/home");
                wait.until(ExpectedConditions.visibilityOf(homePage.firstOddSelection));
                break;
            case "ia":
                driver.get("https://ia.fubosportsbook.com/en-us/home");
                wait.until(ExpectedConditions.visibilityOf(homePage.firstOddSelection));
                break;
            case "dev5":
                driver.get("https://fubo-dev5.amelcobetting.com/en-us/home?geocomply=false");
                wait.until(ExpectedConditions.visibilityOf(homePage.firstOddSelection));
                break;
            case "nj":
                driver.get("https://nj.fubosportsbook.com/en-us/home");
                wait.until(ExpectedConditions.visibilityOf(homePage.firstOddSelection));
                break;
            case "uat5":
                driver.get("https://dge:Amelco-Fubo-uat5!@fubo-uat5.amelcobetting.com/en-us/home?geocomply=false");
                wait.until(ExpectedConditions.visibilityOf(homePage.firstOddSelection));
                break;
            case "uat3":
                driver.get("https://fubo-uat3.amelcobetting.com/en-us/home?geocomply=false");
                wait.until(ExpectedConditions.visibilityOf(homePage.firstOddSelection));
                break;
            default:
                throw new Exception("No environment has been selected");
        }
    }
}
