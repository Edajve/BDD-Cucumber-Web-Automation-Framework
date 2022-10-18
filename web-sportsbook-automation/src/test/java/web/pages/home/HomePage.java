package web.pages.home;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.PageObject;
import web.pages.account.accountModel.MyAccountPage;
import web.pages.onboarding.login.LoginModel;
import web.pages.onboarding.registration.SignUpPage;
import web.utils.HelperUtils;

import java.util.List;

public class HomePage extends PageObject {

    @FindBy(xpath = "//*[text()='Login']/parent::*")
    public WebElement loginButton;

    @FindBy(xpath = "//*[text()='Sign Up']")
    public WebElement sighUpButton;

    @FindBy(xpath = "//*[text()='Last Login']")
    public WebElement usersLastLoginText;

    @FindBy(css = "div[class='ciindbehd6u891-punterName-1 cik11ib0roqg81-nameContainer-1']")
    public WebElement personalUserAccountButton;

    @FindBy(css = "div[class='Cell_Fubo-cell-1 Cell_Fubo-cellFixedVertical-1 Cell_Fubo-cellTall-1 Cell_Fubo-cellFixed-1']")
    public WebElement firstOddSelection;

    @FindBy(css = "div[data-tracking-name='PriceButtonAdded']")
    public List<WebElement> selections;

    @FindBy(css = "input[placeholder='Stake']")
    public WebElement wagerInputFieldForSingleBet;

    @FindBy(css = "input[placeholder='Stake']")
    public List<WebElement> wagerInputFieldsList;

    @FindBy(css = "div[class='react-switch-handle']")
    public WebElement acceptAllLineChangesOrOddsChangesToggle;

    @FindBy(xpath = "//*[text()='Place bet']/parent::*")
    public WebElement placeBetButton;

    @FindBy(xpath = "//*[@id=\"body-content\"]/div/div[2]/div/div/div[2]/div/div[3]/div/button")
    public WebElement addFundsButton;

    @FindBy(xpath = "//*[@id=\"betslip-scroll-container\"]/div/div[1]/div[1]/div[1]/h5")
    public WebElement betPlacedText;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginModel navigateToLoginPage() {
        HelperUtils.waitForElementVisible(driver, loginButton);
        loginButton.click();
        return new LoginModel(driver);
    }

    public SignUpPage navigateToSignUpPage() {
        HelperUtils.waitForElementClickable(driver, sighUpButton);
        sighUpButton.click();
        return new SignUpPage(driver);
    }

    public HomePage selectASingleOdd() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(500);
        HelperUtils.waitForElementVisible(driver, firstOddSelection);
        for (int i = 0; i < selections.size(); i++) {
            if (selections.get(i).isEnabled()) {
                selections.get(i).click();
                break;
            }
        }
        return new HomePage(driver);
    }

    public HomePage fillOutSingleBetInBetSlip(String wagerAmount) {
        HelperUtils.waitForElementClickable(driver, wagerInputFieldForSingleBet);
        wagerInputFieldForSingleBet.click();
        wagerInputFieldForSingleBet.sendKeys(wagerAmount);
        HelperUtils.checkCheckbox(true, acceptAllLineChangesOrOddsChangesToggle);
        HelperUtils.waitForElementVisible(driver, placeBetButton);
        placeBetButton.click();
        return new HomePage(driver);
    }

    public HomePage selectMultipleOdds(String howManyWagersPlaced) throws InterruptedException {
        HelperUtils.waitForElementVisible(driver, firstOddSelection);
        Thread.sleep(1000);
        for (int i = 0; i < Integer.parseInt(howManyWagersPlaced); i++) {
            for (WebElement selection : selections) {
                if (!selection.getAttribute("class").contains("disabled")
                        && !selection.getAttribute("class").contains("empty")) {
                    selection.click();
                    Thread.sleep(1000);
                    break;
                } else if (!selection.getAttribute("class").contains("disabled")
                        && !selection.getAttribute("class").contains("empty")
                        && !selection.getAttribute("class").contains("selected")) {
                    selection.click();
                    Thread.sleep(1000);
                    break;
                }
            }
        }
        Thread.sleep(1000);
        return new HomePage(driver);
    }

    public HomePage fillOutMultipleBetsInBetSlip(String wagerAmounts) {
        HelperUtils.waitForElementsVisible(driver, wagerInputFieldsList);
        for (WebElement element : wagerInputFieldsList) {
            element.click();
            element.sendKeys(wagerAmounts);
        }
        HelperUtils.checkCheckbox(true, acceptAllLineChangesOrOddsChangesToggle);
        placeBetButton.click();
        return new HomePage(driver);
    }

    public void clickAddFundsButton() {
        addFundsButton.click();
        new MyAccountPage(driver);
    }
}
