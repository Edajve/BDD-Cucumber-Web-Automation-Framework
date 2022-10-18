package web.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalAccountModel {
    public PersonalAccountModel(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[text()='Logout']")
    public WebElement logoutButton;

    @FindBy(xpath = "a[class='c8dfnlpkfds48-link-1 c8dfnlpkfds48-linkActive-1']")
    public WebElement depositButtonInMyAccount;
}
