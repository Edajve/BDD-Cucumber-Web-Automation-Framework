package web.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import web.pages.home.HomePage;
import web.pages.onboarding.login.LoginModel;
import web.pages.onboarding.registration.SignUpPage;
import web.utils.ConfigReader;
import web.utils.HelperUtils;

public class LoginSteps {
    WebDriver driver = Hooks.driver;
    LoginModel loginModel = new LoginModel(driver);
    HomePage homePage = new HomePage(driver);
    SignUpPage signUpPage = new SignUpPage(driver);

    public LoginSteps() throws Exception {
    }

    @When("^user logs in with correct credentials$")
    public void userLogsInWithCorrectCredentials() throws Exception {
        homePage.navigateToLoginPage();
        loginModel.login(ConfigReader.getProperty("correctEmail"), ConfigReader.getProperty("correctPassword"));
    }

    @Then("^user should be logged into their account$")
    public void userShouldBeLoggedIntoTheirAccount() {
        HelperUtils.waitForElementVisible(driver, homePage.usersLastLoginText);
        Assert.assertTrue(homePage.usersLastLoginText.isDisplayed());
    }

    @Then("user should receive an error message not allowing them to login")
    public void userShouldReceiveAnErrorMessageNotAllowingThemToLogin() {
        HelperUtils.waitForElementVisible(driver, loginModel.loginErrorMessageDiv);
        Assert.assertEquals("That email and password combination is not valid.", loginModel.getErrorMessageText());
    }

    @And("user logs in with {string}")
    public void userLogsInWith(String accountCondition) throws Exception {
        homePage.navigateToLoginPage();
        switch (accountCondition) {
            case "correct credentials":
                loginModel.login(ConfigReader.getProperty("correctEmail"), ConfigReader.getProperty("correctPassword"));
                break;
            case "incorrect credentials":
                loginModel.login(ConfigReader.getProperty("incorrectEmailAddress"), ConfigReader.getProperty("incorrectPassword"));
                break;
            case "first time deposit account":
                loginModel.login(ConfigReader.getProperty("newUserEmailAddress"), ConfigReader.getProperty("newUserPassword"));
                break;
            case "a account that has daily deposit limit set":
                loginModel.login(ConfigReader.getProperty("emailWIthDailyLimitSet"), ConfigReader.getProperty("passwordWithDailyLimitSet"));
                break;
            case "a account that has weekly deposit limit set":
                loginModel.login(ConfigReader.getProperty("emailWithWeeklyDepositLimit"), ConfigReader.getProperty("passwordWithWeeklyDepositLimit"));
                break;
            case "a account that has monthly deposit limit set":
                loginModel.login(ConfigReader.getProperty("emailWithMonthlyDepositLimit"), ConfigReader.getProperty("passwordWithMonthlyDepositLimit"));
                break;
            case "suspended account":
                loginModel.login(ConfigReader.getProperty("amelcoSuspendedEmail"), ConfigReader.getProperty("amelcoSuspendedPass"));
                break;
            case "deactivated account":
                loginModel.login(ConfigReader.getProperty("deactivatedSportsbookEmail"), ConfigReader.getProperty("deactivatedSportsbookPassword"));
                break;
            default:
                throw new Exception("No account condition has been chosen");
        }
    }

    @Then("user should receive an login error message of {string} not allowing them to login")
    public void userShouldReceiveAnLoginErrorMessageOfNotAllowingThemToLogin(String loginErrorMessage) throws InterruptedException {
        Thread.sleep(8000);
        HelperUtils.waitForElementVisible(driver, loginModel.loginErrorMessageDiv);
        Assert.assertEquals(loginErrorMessage, loginModel.getErrorMessageText());
    }

    @Then("user should be navigated to the sign up model")
    public void userShouldBeNavigatedToTheSignUpModel() throws InterruptedException {
        HelperUtils.waitForElementVisible(driver, signUpPage.createAFuboAccount);
        Thread.sleep(2000);
        Assert.assertEquals("These message are not the same", "Create A Fubo Account", signUpPage.getSignUpHeaderText());
        //Assert.assertEquals("Create A Fubo Account", signUpPage.getSignUpHeaderText());
    }

    @And("clicks {string} link")
    public void clicksLink(String linkText) throws InterruptedException {
        HelperUtils.waitForElementVisible(driver, loginModel.signUpLink);
        loginModel.clickSignUpLink();
    }
}
