package web.steps;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.pages.home.HomePage;
import web.utils.HelperUtils;

import java.time.Duration;


public class WagerSteps {

    WebDriver driver = Hooks.driver;
    HomePage homePage = new HomePage(driver);
    WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(20));
    HelperUtils utils = new HelperUtils();

    public WagerSteps() {
    }

    @When("user places a bet for {string} dollars")
    public void userPlacesABetForDollars(String wagerAmount) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.firstOddSelection));
        HelperUtils.addQueryToEndOfUrl("?geocomply=false");
        HelperUtils.waitForElementVisible(driver, homePage.firstOddSelection);
        homePage.selectASingleOdd();
        homePage.fillOutSingleBetInBetSlip(wagerAmount);
    }

    @Then("^bet should be placed$")
    public void bet_should_be_placed() {
        wait.until(ExpectedConditions.visibilityOf(homePage.betPlacedText));
        Assert.assertEquals("1 Bet Placed", homePage.betPlacedText.getAttribute("innerHTML"));
    }


    @When("user places {string} bets for {string} dollars")
    public void userPlacesBetsForDollars(String howManyOddsSelected, String wagerAmount) throws InterruptedException {
        int numberOfBets = Integer.parseInt(howManyOddsSelected);
        HelperUtils.waitForElementVisible(driver, homePage.firstOddSelection);
        HelperUtils.addQueryToEndOfUrl("?geocomply=false");
        homePage.selectMultipleOdds(String.valueOf(numberOfBets));
        homePage.fillOutMultipleBetsInBetSlip(wagerAmount);
    }

    @Then("^multiple bet should be placed$")
    public void multipleBetShouldBePlaced() {
        HelperUtils.waitForElementVisible(driver, homePage.betPlacedText);
        Assert.assertTrue(homePage.betPlacedText.toString().contains("1 Bet Placed"));
    }

    @Then("^\"([^\"]*)\" bet should be placed$")
    public void betShouldBePlaced(String amountOfBetsPlaced) {
        HelperUtils.waitForElementVisible(driver, homePage.betPlacedText);
        Assert.assertEquals(amountOfBetsPlaced + " Bets Placed", homePage.betPlacedText.getAttribute("innerHTML"));
    }


}
