package web.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.pages.account.wallet.deposit.DepositPage;
import web.pages.account.wallet.PaySafeLightBoxModel;
import web.pages.account.wallet.TrustlyLightBoxModel;
import web.pages.account.wallet.withdrawal.WithdrawalPage;
import web.pages.home.HomePage;
import web.utils.HelperUtils;

import java.time.Duration;

public class WithdrawSteps {

    WebDriver driver = Hooks.driver;
    HomePage homePage = new HomePage(driver);
    DepositPage depositPage = new DepositPage(driver);
    TrustlyLightBoxModel trustlyLightBox = new TrustlyLightBoxModel(driver);
    WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(32));
    PaySafeLightBoxModel paySafeLightBoxModel = new PaySafeLightBoxModel(driver);
    WithdrawalPage withdrawalPage = new WithdrawalPage(driver);


    public WithdrawSteps() throws Exception {
    }

    @When("user places a {string} withdraw amount with an existing bank")
    public void user_places_a_withdraw_amount_with_an_existing_bank(String withdrawAmount) throws InterruptedException {
        withdrawalPage.withdrawTrustlyWithExistingBank(withdrawAmount);
    }

    @Then("the sportsbook should withdraw the amount")
    public void the_sportsbook_should_withdraw_the_amount() throws InterruptedException {
        HelperUtils.waitForElementVisible(driver, withdrawalPage.withdrawSuccessfulText);
        Thread.sleep(1000);
        Assert.assertEquals("Your withdrawal request will be processed as quickly as possible.", withdrawalPage.withdrawSuccessfulText.getText());
    }

}
