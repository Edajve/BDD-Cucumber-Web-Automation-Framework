package web.pages.account.wallet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.PageObject;
import web.pages.account.wallet.deposit.DepositPage;
import web.utils.HelperUtils;

import java.util.List;

public class PaySafeLightBoxModel extends PageObject {
    public PaySafeLightBoxModel(WebDriver driver) throws Exception {
        super(driver);
    }

    @FindBy(css = "li[class='payment-methods']")
    public List<WebElement> paysafePaymentProviders;

    @FindBy(css = "div[class='card-heading-section ga-radio-saved-cards']")
    public WebElement card3775DropDownSelect;

    @FindBy(css = "input[formcontrolname='cvv']")
    public WebElement cvvInputField;

    @FindBy(id = "cardNumber")
    public WebElement cardNumberInput;

    @FindBy(id = "cardHolderName")
    public WebElement cardHolderInput;

    @FindBy(id = "cardExpiryMonth")
    public WebElement cardExpireMonthInput;

    @FindBy(id = "cardExpiryYear")
    public WebElement cardExpireYearInput;

    @FindBy(id = "cardSecurityCode")
    public WebElement cvvInput;

    @FindBy(css = "button[tabindex='0']")
    public WebElement payButton;


    public PaySafeLightBoxModel choosePaySafeProvider(String paymentProvider) throws Exception {
        switch (paymentProvider) {
            case "vipp":
                HelperUtils.waitForElementVisible(driver, paysafePaymentProviders.get(0));
                paysafePaymentProviders.get(0).click();
                break;
            case "paysafe-cash":
                HelperUtils.waitForElementVisible(driver, paysafePaymentProviders.get(1));
                paysafePaymentProviders.get(1).click();
                break;
            case "skrill":
                HelperUtils.waitForElementVisible(driver, paysafePaymentProviders.get(2));
                paysafePaymentProviders.get(2).click();
                break;
            case "debit/credit":
                HelperUtils.waitForElementVisible(driver, paysafePaymentProviders.get(3));
                paysafePaymentProviders.get(3).click();
                break;
            case "play+":
                HelperUtils.waitForElementVisible(driver, paysafePaymentProviders.get(4));
                paysafePaymentProviders.get(4).click();
                break;
            default:
                throw new Exception("No Paysafe Payment Provider has been selected");
        }
        driver.switchTo().defaultContent();
        return new PaySafeLightBoxModel(driver);
    }

    public DepositPage existingCreditCardPaySafeFlow() {
        driver.switchTo().frame(0);
        HelperUtils.waitForElementClickable(driver, card3775DropDownSelect);
        card3775DropDownSelect.click();
        cvvInputField.sendKeys("111");
        payButton.click();
        driver.switchTo().defaultContent();
        return new DepositPage(driver);
    }

    public DepositPage newCreditCardPaySafeFlow(
            String cardNum,
            String cardHolder,
            String expireMonth,
            String expireYear,
            String cvv
    ) {
        driver.switchTo().frame(0);
        cardNumberInput.sendKeys(cardNum);
        cardHolderInput.sendKeys(cardHolder);
        cardExpireMonthInput.sendKeys(expireMonth);
        cardExpireYearInput.sendKeys(expireYear);
        cvvInput.click();
        cvvInput.sendKeys(cvv);

        payButton.click();
        driver.switchTo().defaultContent();
        return new DepositPage(driver);
    }
}
