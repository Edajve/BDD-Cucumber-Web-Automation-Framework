package web.pages.account.wallet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.pages.PageObject;
import web.pages.account.wallet.deposit.DepositPage;
import web.utils.HelperUtils;

import java.time.Duration;

public class TrustlyLightBoxModel extends PageObject {

    @FindBy(id = "slider-button")
    public WebElement okGotItButton;

    @FindBy(name = "username")
    public WebElement trustlyEmailInputField;

    @FindBy(name = "password")
    public WebElement trustlyPasswordInputField;

    @FindBy(id = "lbx-formLogin-submit")
    public WebElement trustlyLoginButton;

    @FindBy(id = "lbx-wdt-bankTiles-openBank200005501")
    public WebElement demoBankIcon;

    @FindBy(xpath = "//*[@id=\"lbx-accountList-submit\"]")
    public WebElement continueButtonAfterChoosingBankForTrustly;

    public TrustlyLightBoxModel(WebDriver driver) {
        super(driver);
    }

    public TrustlyLightBoxModel logIntoTrustly(String email, String password) throws InterruptedException {
        demoBankIcon.click();
        HelperUtils.waitForElementClickable(driver, okGotItButton);
        okGotItButton.click();
        Thread.sleep(1500);
        driver.switchTo().frame(0);
        trustlyEmailInputField.click();
        trustlyEmailInputField.sendKeys(email);
        trustlyPasswordInputField.click();
        trustlyPasswordInputField.sendKeys(password);
        trustlyLoginButton.click();
        return new TrustlyLightBoxModel(driver);
    }

    public DepositPage loginIntoTrustlyDemoBankAndChooseABank() throws InterruptedException {
        logIntoTrustly("test", "test");
        return new DepositPage(driver);
    }

    public DepositPage chooseTrustlyBank() {
        HelperUtils.waitForElementVisible(driver, continueButtonAfterChoosingBankForTrustly);
        continueButtonAfterChoosingBankForTrustly.click();
        return new DepositPage(driver);
    }

    public void waitUntilTrustlyLightBoxIsOpen() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                WebElement button = driver.findElement(By.id("paywithmybank-lightbox"));
                String enabled = button.getAttribute("style");
                if (enabled.equals("display: block;"))
                    return true;
                else
                    return false;
            }
        });
    }
}
