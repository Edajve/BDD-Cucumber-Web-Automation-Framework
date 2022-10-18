package web.pages.limits;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.PageObject;

public class LimitPage extends PageObject {

    public LimitPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(@class,'-status-1')]")
    public WebElement depositLimitErrorMessageDiv;

    public String getDepositLimitErrorMessage() {
        if (depositLimitErrorMessageDiv.getText().contains("daily")) {
            return depositLimitErrorMessageDiv.getText().substring(0, 42);
        } else if (depositLimitErrorMessageDiv.getText().contains("weekly")) {
            return depositLimitErrorMessageDiv.getText().substring(0, 43);
        } else if (depositLimitErrorMessageDiv.getText().contains("monthly")) {
            return depositLimitErrorMessageDiv.getText().substring(0, 44);
        } else return depositLimitErrorMessageDiv.getText();
    }
}

