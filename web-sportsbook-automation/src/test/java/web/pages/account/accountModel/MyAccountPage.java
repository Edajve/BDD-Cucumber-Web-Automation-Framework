package web.pages.account.accountModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.limits.LimitPage;
import web.pages.PageObject;
import web.pages.account.wallet.withdrawal.WithdrawalPage;

public class MyAccountPage extends PageObject {
    //This class will hold only the links that are on the left hand side of the 'My Accounts' model.
    //Elements that are of the 'My Account' page will be held in its own class

    @FindBy(xpath = "//*[text()='Withdraw']")
    public WebElement withdrawTab;

    @FindBy(xpath = "//*[text()='Account Settings']")
    public WebElement accountSettingsTab;

    @FindBy(xpath = "//*[text()='Limits']")
    public WebElement limitsTab;

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public WithdrawalPage navigateToWithdrawPage() {
        withdrawTab.click();
        return new WithdrawalPage(driver);
    }

    public LimitPage navigateToLimitsPage(){
        accountSettingsTab.click();
        limitsTab.click();
        return new LimitPage(driver);
    }
}
