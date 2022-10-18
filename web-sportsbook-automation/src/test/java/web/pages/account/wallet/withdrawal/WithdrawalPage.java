package web.pages.account.wallet.withdrawal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.PageObject;
import web.utils.HelperUtils;


public class WithdrawalPage extends PageObject {

    @FindBy(css = "input[placeholder='']")
    public WebElement withdrawAmountInputField;

    @FindBy(xpath = "//button[contains(@class,'withdrawButton-1')]")
    public WebElement withdrawButton;

    @FindBy(xpath = "//*[text()='Your withdrawal request will be processed as quickly as possible.']")
    public WebElement withdrawSuccessfulText;

    public WithdrawalPage(WebDriver driver){
        super(driver);
    }

    public WithdrawalPage withdrawTrustlyWithExistingBank(String amountWithdraw) throws InterruptedException {
        HelperUtils.waitForElementVisible(driver, withdrawAmountInputField);
        withdrawAmountInputField.sendKeys(amountWithdraw);
        Thread.sleep(1000);
        HelperUtils.waitForElementVisible(driver, withdrawButton);
        withdrawButton.click();
        return new WithdrawalPage(driver);
    }
}
