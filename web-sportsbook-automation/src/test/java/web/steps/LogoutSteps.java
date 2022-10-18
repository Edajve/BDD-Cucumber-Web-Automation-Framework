package web.steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.pages.home.HomePage;
import web.pages.home.PersonalAccountModel;
import web.steps.lambdaMethods.Logout;

import java.time.Duration;

public class LogoutSteps {

    WebDriver driver = Hooks.driver;
    HomePage homePage = new HomePage(driver);
    WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(20));
    PersonalAccountModel personalAccountModel = new PersonalAccountModel(driver);

    public LogoutSteps() throws Exception {
    }

    @Given("^user logs out$")
    public void userLogsOut() throws InterruptedException {
        Logout logout = () -> {
            wait.until(ExpectedConditions.visibilityOf(homePage.addFundsButton));
            homePage.addFundsButton.click();
            Thread.sleep(2000);
            personalAccountModel.logoutButton.click();
            Thread.sleep(2000);
        };
        logout.logout();
    }

    @Then("user should see {string} text upon logout")
    public void userShouldSeeTextUponLogout(String logoutText) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"popupOverlay\"]/div/div[1]/h4")));
        Assert.assertEquals(logoutText, driver.findElement(By.xpath("//*[@id=\"popupOverlay\"]/div/div[1]/h4")).getText());
    }

    @And("clicks {string}")
    public void clicks(String arg0) {
    }
}