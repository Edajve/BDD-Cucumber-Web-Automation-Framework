package web.pages.account.wallet.deposit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.PageObject;
import web.pages.account.wallet.TrustlyLightBoxModel;
import web.utils.HelperUtils;

import java.util.List;

public class DepositPage extends PageObject {

    //Currently Trustly and Paysafe are out third party providers
    @FindBy(xpath = "//div[contains(@class,'-methodHover-1')]")
    public List<WebElement> paymentProvidersList;

    @FindBy(xpath = "/html/body/div[6]/div/div/div/div[3]/div/div[2]/div/div/div/div[1]/div[1]/div/div[2]/figure/div[1]")
    public WebElement paySafeProvider;

    @FindBy(css = "input[placeholder='']")
    public WebElement depositAmountInputField;

    @FindBy(xpath = "//div[contains(@class,'checkboxImage-1')]")
    public WebElement depositPromotionCheckbox;

    @FindBy(xpath = "//button[contains(@class,'depositButton-1')]")
    public WebElement depositButtonForTrustly;

    @FindBy(xpath = "//button[contains(@class,'depositButtonPaysafe')]")
    public WebElement depositButtonForPaySafe;

    @FindBy(xpath = "//*[text()='Your deposit was successful']")
    public WebElement depositSuccessfulText;

    @FindBy(xpath = "//button[contains(@class,'continueButton-1')]/span")
    public WebElement continueButtonForNewUserDeposit;

    @FindBy(xpath = "body > div:nth-child(12) > div > div > div > div.c6fditt7mhgo22-content-1.c6fditt7mhgo22-tight-1 > div > div:nth-child(2) > div > div > div > div:nth-child(2) > div:nth-child(2) > div.cholg5jlgno826-statusContainer-1.cholg5jlgno826-statusError-1 > div > div")
    public WebElement depositErrorMessageDiv;

    public DepositPage(WebDriver driver) {
        super(driver);
    }

    public DepositPage depositTrustlyWithExistingBank(String depositAmount) {
        depositAmountInputField.click();
        depositAmountInputField.sendKeys(depositAmount);
        HelperUtils.waitForElementClickable(driver, depositButtonForTrustly);
        depositButtonForTrustly.click();
        return new DepositPage(driver);
    }

    public TrustlyLightBoxModel depositTrustlyWithNewBank(String depositAmount) throws InterruptedException {
        HelperUtils.waitForElementVisible(driver, depositAmountInputField);
        depositAmountInputField.click();
        depositAmountInputField.sendKeys(depositAmount);
        Thread.sleep(500);
        continueButtonForNewUserDeposit.click();
        return new TrustlyLightBoxModel(driver);
    }

    public TrustlyLightBoxModel depositPaySafeNewCreditCard(String depositAmount) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", paymentProvidersList.get(1));
        Thread.sleep(2000);
        depositAmountInputField.click();
        depositAmountInputField.sendKeys(depositAmount);
        Thread.sleep(500);
        depositButtonForPaySafe.click();
        return new TrustlyLightBoxModel(driver);
    }
}