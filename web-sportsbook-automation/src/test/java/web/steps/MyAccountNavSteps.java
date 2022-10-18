package web.steps;

import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.pages.account.accountModel.MyAccountPage;
import web.pages.account.wallet.deposit.DepositPage;
import web.pages.home.HomePage;
import web.pages.onboarding.login.LoginModel;
import web.utils.HelperUtils;

import java.time.Duration;

public class MyAccountNavSteps {
    WebDriver driver = Hooks.driver;
    DepositPage depositPage = new DepositPage(driver);
    LoginModel loginModel = new LoginModel(driver);
    MyAccountPage myAccount = new MyAccountPage(driver);
    HomePage homePage = new HomePage(driver);
    WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(32));

    public MyAccountNavSteps() throws Exception {
    }
    @And("^navigates to \"([^\"]*)\" page$")
    public void navigatesToPage(String navStep) throws Throwable {
        switch (navStep) {
            case "deposit":
                //my account model defaults to deposit page
                break;
            case "withdraw":
                HelperUtils.waitForElementClickable(driver, depositPage.depositAmountInputField);
                myAccount.navigateToWithdrawPage();
                Thread.sleep(7000);
                break;
            case "Limits":
                HelperUtils.waitForElementClickable(driver, depositPage.depositAmountInputField);
                myAccount.navigateToLimitsPage();
                break;
            case "login":
            HelperUtils.waitForElementVisible(driver, homePage.loginButton);
            homePage.navigateToLoginPage();
            break;
            default:
                throw new Exception("No My Account Wallet tab has been chosen");
        }
    }
}
