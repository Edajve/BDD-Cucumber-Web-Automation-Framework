package web.pages.onboarding.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.PageObject;
import web.pages.home.HomePage;
import web.utils.HelperUtils;

public class LoginModel extends PageObject {

    @FindBy(xpath = "(//input[@class='Input__input'])[1]")
    public WebElement emailAddressInputBox;

    @FindBy(xpath = "//input[@type='password']")
    public WebElement passwordInputBox;

    @FindBy(xpath = "//button[@class='Button Button--orange']")
    public WebElement loginButton;

    @FindBy(xpath = "//*[text()='That email and password combination is not valid.']")
    public WebElement loginErrorMessage;

    @FindBy(css = "div[class='Banner Banner--danger']>div")
    public WebElement loginErrorMessageDiv;

    @FindBy(xpath = "//*[text()='Sign Up']")
    public WebElement signUpLink;

    public LoginModel(WebDriver driver) {
        super(driver);
    }

    public HomePage login(String emailAddress, String password) throws Exception {
        HelperUtils.waitForElementVisible(driver, emailAddressInputBox);
        emailAddressInputBox.sendKeys(emailAddress);
        passwordInputBox.sendKeys(password);
        loginButton.click();
        return new HomePage(driver);
    }

    public String getErrorMessageText() {
        return loginErrorMessageDiv.getText();
    }

    public void clickSignUpLink(){
        signUpLink.click();
    }
}
