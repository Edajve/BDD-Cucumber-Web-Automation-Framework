package web.pages.onboarding.registration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import web.pages.PageObject;
import web.pages.verificationPages.verificationAfterKYCPage;
import web.utils.HelperUtils;

import java.util.List;

public class KYCPage extends PageObject {

    @FindBy(name = "firstName")
    public WebElement firstName;

    @FindBy(name = "lastName")
    public WebElement lastName;

    @FindBy(name = "month")
    public WebElement monthDropDown;

    @FindBy(name = "day")
    public WebElement dayDropDown;

    @FindBy(name = "year")
    public WebElement yearDropDown;

    @FindBy(name = "address")
    public WebElement houseNumber;
    
    @FindBy(name = "address2")
    public WebElement streetName;

    @FindBy(name = "city")
    public WebElement city;

    @FindBy(name = "state")
    public WebElement stateDropDown;

    @FindBy(name = "zipCode")
    public WebElement zipCode;

    @FindBy(css = "input[type='tel']")
    public WebElement phoneNumber;

    @FindBy(css = "input[name='ssnLast4Digits']")
    public WebElement ssn;

    @FindBy(css = "input[name='c']")
    public WebElement promoCode;

    @FindBy(css = "label[class='Checkbox__label']")
    public List<WebElement> kycCheckBoxes;

    @FindBy(css = "button[type='submit']")
    public WebElement submitButton;

    @FindBy(css = "div[class='KYC__field-invalid-text']")
    public WebElement kycDobErrorMessage;

    public KYCPage(WebDriver driver) {
        super(driver);
    }

    public KYCPage completeKYC(
            String first_name,
            String last_name,
            String date_of_birth,
            String house_number,
            String street_name,
            String city,
            String state,
            String zip_code,
            String promotional_code) throws Exception {
        Select month = new Select(monthDropDown);
        Select day = new Select(dayDropDown);
        Select year = new Select(yearDropDown);
        Select stateDropdown = new Select(stateDropDown);

        HelperUtils.waitForElementClickable(driver, firstName);
        firstName.sendKeys(first_name);
        lastName.sendKeys(last_name);
        month.selectByIndex(Integer.parseInt(date_of_birth.substring(0, 1)));
        day.selectByVisibleText(date_of_birth.substring(2, 4));
        year.selectByVisibleText(date_of_birth.substring(5));
        houseNumber.sendKeys(house_number);
        streetName.sendKeys(street_name);
        this.city.sendKeys(city);
        houseNumber.click();
        stateDropdown.selectByVisibleText(state);
        zipCode.sendKeys(zip_code);
        phoneNumber.sendKeys(HelperUtils.generatePhoneNumber());
        this.ssn.click();
        this.ssn.sendKeys(HelperUtils.generateFourSSN());

        for (WebElement kycCheckBox : kycCheckBoxes) {
            kycCheckBox.click();
        }

        return new KYCPage(driver);
    }

    public KYCPage completeKycWithInvalidDOB(
            String first_name,
            String last_name,
            String date_of_birth,
            String house_number,
            String street_name,
            String city,
            String state,
            String zip_code,
            String promotional_code) throws InterruptedException {
        Select month = new Select(monthDropDown);
        Select day = new Select(dayDropDown);
        Select year = new Select(yearDropDown);
        Select stateDropdown = new Select(stateDropDown);

        HelperUtils.waitForElementClickable(driver, firstName);
        firstName.sendKeys(first_name);
        lastName.sendKeys(last_name);
        month.selectByIndex(Integer.parseInt(date_of_birth.substring(0, 1)));
        day.selectByVisibleText(date_of_birth.substring(2, 4));
        year.selectByVisibleText(date_of_birth.substring(5));
        houseNumber.sendKeys(house_number);
        streetName.sendKeys(street_name);
        this.city.sendKeys(city);
        houseNumber.click();
        stateDropdown.selectByVisibleText(state);
        zipCode.sendKeys(zip_code);
        phoneNumber.sendKeys(HelperUtils.generatePhoneNumber());
        this.ssn.click();
        this.ssn.sendKeys(HelperUtils.generateFourSSN());

        for (WebElement kycCheckBox : kycCheckBoxes) {
            kycCheckBox.click();
        }
        return new KYCPage(driver);
    }

    public verificationAfterKYCPage submitKYC(){
        submitButton.click();
        return new verificationAfterKYCPage(driver);
    }

    public String getDobErrorMessageText(){
        return kycDobErrorMessage.getText();
    }
}
