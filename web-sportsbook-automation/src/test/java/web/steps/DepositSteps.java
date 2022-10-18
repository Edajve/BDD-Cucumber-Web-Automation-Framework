package web.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.pages.limits.LimitPage;
import web.pages.account.wallet.deposit.DepositPage;
import web.pages.account.wallet.PaySafeLightBoxModel;
import web.pages.account.wallet.TrustlyLightBoxModel;
import web.pages.home.HomePage;
import web.utils.HelperUtils;

import java.time.Duration;

public class DepositSteps {

    WebDriver driver = Hooks.driver;
    HomePage homePage = new HomePage(driver);
    DepositPage depositPage = new DepositPage(driver);
    TrustlyLightBoxModel trustlyLightBox = new TrustlyLightBoxModel(driver);
    WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(32));
    PaySafeLightBoxModel paySafeLightBoxModel = new PaySafeLightBoxModel(driver);
    LimitPage limitPage = new LimitPage(driver);

    public DepositSteps() throws Exception {
    }

    @Then("^the sportsbook should deposit the amount$")
    public void theSportsbookShouldDepositTheAmount() throws InterruptedException {
        Thread.sleep(2000);
        HelperUtils.waitForElementVisible(driver, depositPage.depositSuccessfulText);
        Thread.sleep(1000);
        Assert.assertEquals("Your deposit was successful", depositPage.depositSuccessfulText.getText());
    }

    @And("^user places a \"([^\"]*)\" deposit amount through Trustly with new bank$")
    public void userPlacesADepositAmountThroughTrustlyWithNewBank(String amountOfMoneyDeposited) throws Throwable {
        HelperUtils.waitForElementVisible(driver, depositPage.depositAmountInputField);
        depositPage.depositTrustlyWithNewBank(amountOfMoneyDeposited);

        trustlyLightBox.waitUntilTrustlyLightBoxIsOpen();
        driver.switchTo().frame(0);

        HelperUtils.waitForElementVisible(driver, trustlyLightBox.demoBankIcon);
        trustlyLightBox.loginIntoTrustlyDemoBankAndChooseABank();
        trustlyLightBox.chooseTrustlyBank();
        driver.switchTo().defaultContent();
    }

    @When("^user places a \"([^\"]*)\" deposit amount through credit card with Paysafe for the first time$")
    public void user_places_a_deposit_amount_through_credit_card_with_PaysafeForTheFirstTime(String depositAmount) throws Exception {
        Thread.sleep(2000);
        depositPage.depositPaySafeNewCreditCard(depositAmount);
        Thread.sleep(6000);
        driver.switchTo().frame(0);
        paySafeLightBoxModel.choosePaySafeProvider("debit/credit");
        paySafeLightBoxModel.newCreditCardPaySafeFlow(
                "4206720389883775",
                "Test Automation",
                "11",
                "2027",
                "111"
        );
    }

    @And("^user places a \"([^\"]*)\" deposit amount with an existing credit card$")
    public void userPlacesADepositAmountWithAnExistingCreditCard(String depositAmount) throws Throwable {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(2000);
        js.executeScript("arguments[0].click();", depositPage.paySafeProvider);

        depositPage.depositAmountInputField.click();
        depositPage.depositAmountInputField.sendKeys(depositAmount);
        Thread.sleep(1000);
        depositPage.depositButtonForPaySafe.click();

        Thread.sleep(6000);
        driver.switchTo().frame(0);
        paySafeLightBoxModel.choosePaySafeProvider("debit/credit");
        paySafeLightBoxModel.existingCreditCardPaySafeFlow();
    }

    @And("^user places a \"([^\"]*)\" deposit amount with an existing bank$")
    public void userPlacesADepositAmountWithAnExistingBank(String depositAmount) throws Throwable {
        Thread.sleep(6600);
        depositPage.depositTrustlyWithExistingBank(depositAmount);
    }

    @When("^user opens \"([^\"]*)\" model$")
    public void userOpensModel(String modelBeingOpened) throws Throwable {
        switch (modelBeingOpened) {
            case "my account":
                HelperUtils.waitForElementVisible(driver, homePage.addFundsButton);
                homePage.clickAddFundsButton();
                break;
            default:
                throw new Exception("No model has been selected");
        }
    }

    @When("tries to deposit more their daily limit")
    public void tries_to_deposit_more_their_daily_limit() {


    }

    @Then("the sportsbook should show a {string} error message")
    public void the_sportsbook_should_show_a_error_message(String errorMessage) throws InterruptedException {
        HelperUtils.waitForElementVisible(driver, limitPage.depositLimitErrorMessageDiv);
        Assert.assertEquals(errorMessage, limitPage.getDepositLimitErrorMessage());
    }

    @And("tries to deposit more their {string} limit")
    public void triesToDepositMoreTheirLimit(String limitDate) throws Exception {
        HelperUtils.waitForElementVisible(driver, depositPage.depositAmountInputField);
        switch (limitDate) {
            case "daily":
                //the account this step definition is attached to has a daily deposit of $30
                depositPage.depositAmountInputField.sendKeys("35");
                break;
            case "weekly":
                //the account this step definition is attached to has a weekly deposit of $40
                depositPage.depositAmountInputField.sendKeys("45");
                break;
            case "monthly":
                //the account this step definition is attached to has a weekly deposit of $50
                depositPage.depositAmountInputField.sendKeys("55");
                break;
            default:
                throw new Exception("No limit Date has been selected");
        }
    }
}
