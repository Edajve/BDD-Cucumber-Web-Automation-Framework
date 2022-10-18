package web.steps;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.pages.home.HomePage;
import web.pages.onboarding.registration.KYCPage;
import web.pages.onboarding.registration.SignUpPage;
import web.pages.verificationPages.verificationAfterKYCPage;
import web.utils.HelperUtils;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class RegistrationSteps {

    WebDriver driver = Hooks.driver;
    HomePage homePage = new HomePage(driver);
    WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(32));
    SignUpPage signUpPage = new SignUpPage(driver);
    web.pages.verificationPages.verificationAfterKYCPage verificationAfterKYCPage = new verificationAfterKYCPage(driver);
    KYCPage kycPage = new KYCPage(driver);

    public RegistrationSteps() {
    }

    @Given("^user signs up using this information$")
    public void user_signs_up_using_this_information(DataTable signUpData) {
        //datable coming from the feature file
        List<Map<String, String>> dataMap = signUpData.asMaps(String.class, String.class);
        homePage.navigateToSignUpPage();
        signUpPage.signUpToSportsbook(
                HelperUtils.fuboEmailGenerator(dataMap.get(0).get("Email")),
                HelperUtils.fuboSBPasswordGenerator(),
                dataMap.get(0).get("Zip Code")
        );
    }

    @Given("^completes KYC with this information$")
    public void completes_KYC_with_this_information(DataTable kycData) throws Exception {
        //datable coming from the feature file
        List<Map<String, String>> kycDataMap = kycData.asMaps(String.class, String.class);

        kycPage.completeKYC(
                kycDataMap.get(0).get("First Name"),
                kycDataMap.get(0).get("Last Name"),
                kycDataMap.get(0).get("Date Of Birth"),
                kycDataMap.get(0).get("House Number"),
                kycDataMap.get(0).get("Street Name"),
                kycDataMap.get(0).get("City"),
                kycDataMap.get(0).get("State"),
                kycDataMap.get(0).get("Zip Code"),
                kycDataMap.get(0).get("Promotional Code")
        );
        HelperUtils.waitForElementClickable(driver, kycPage.submitButton);
        kycPage.submitKYC();
    }

    @Then("^the user should be registered but not verified to the sportsbook$")
    public void theUserShouldBeRegisteredButNotVerifiedToTheSportsbook() {
        wait.until(ExpectedConditions.urlContains("auth/user-verification"));
        HelperUtils.waitForElementVisible(driver, verificationAfterKYCPage.registeredButNotVerifiedText);
        HelperUtils.waitForUrlVisible(driver, driver.getCurrentUrl());
        Assert.assertTrue("auth/user-verification", driver.getCurrentUrl().contains("auth/user-verification"));
    }

    @Given("completes KYC with date of birth information under twenty-one")
    public void completes_kyc_with_date_of_birth_information_under_twenty_one(io.cucumber.datatable.DataTable underTwentyOneDOB) throws InterruptedException {
        //datable coming from the feature file
        List<Map<String, String>> kycDataMap = underTwentyOneDOB.asMaps(String.class, String.class);

        kycPage.completeKycWithInvalidDOB(
                kycDataMap.get(0).get("First Name"),
                kycDataMap.get(0).get("Last Name"),
                kycDataMap.get(0).get("Date Of Birth"),
                kycDataMap.get(0).get("House Number"),
                kycDataMap.get(0).get("Street Name"),
                kycDataMap.get(0).get("City"),
                kycDataMap.get(0).get("State"),
                kycDataMap.get(0).get("Zip Code"),
                kycDataMap.get(0).get("Promotional Code")
        );
    }

    @Then("the sportsbook should give a {string} error message and not allowed to sign up to the sportsbook")
    public void the_sportsbook_should_give_a_error_message_and_not_allowed_to_sign_up_to_the_sportsbook(String errorMessage) throws InterruptedException {
        //scroll back up the page to verify DOB error message
        HelperUtils.scrollUpUsingKeys(Hooks.driver, 18);
        Thread.sleep(800);
        Assert.assertEquals(errorMessage, kycPage.getDobErrorMessageText());
    }
}
