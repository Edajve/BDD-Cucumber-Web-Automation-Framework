package web.pages.onboarding.registration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.PageObject;
import web.utils.HelperUtils;

public class SignUpPage extends PageObject {

    @FindBy(xpath = "(//input[@class='Input__input'])[1]")
    public WebElement emailAddressInputBox;

    @FindBy(xpath = "//input[@type='password']")
    public WebElement passwordInputBox;

    @FindBy(css = "#body-content > div > div > div.FocusLayout__main-section > div.FocusLayout__content > div > div > form > div.CreateAccount__input-fields > div:nth-child(3) > div > div > input")
    public WebElement zipCodeInputField;

    @FindBy(css = "button[type='submit']")
    public WebElement createAccountButton;

    @FindBy(xpath = "//*[text()='Create a Fubo account']")
    public WebElement createAFuboAccount;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public KYCPage signUpToSportsbook(String email, String pass, String zip) {
        HelperUtils.waitForElementClickable(driver, emailAddressInputBox);
        emailAddressInputBox.sendKeys(email);
        passwordInputBox.sendKeys(HelperUtils.fuboSBPasswordGenerator());
        zipCodeInputField.sendKeys(zip);
        createAccountButton.click();
        return new KYCPage(driver);
    }

    public SignUpPage getSignUpHeaderText(){
        createAFuboAccount.getText();
        return new SignUpPage(driver);
    }
}