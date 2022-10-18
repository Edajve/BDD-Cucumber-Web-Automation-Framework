package web.pages.verificationPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.PageObject;

public class verificationAfterKYCPage extends PageObject {
    public verificationAfterKYCPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div[class=\"Verification__cta-text\"]")
    public WebElement registeredButNotVerifiedText;
}
