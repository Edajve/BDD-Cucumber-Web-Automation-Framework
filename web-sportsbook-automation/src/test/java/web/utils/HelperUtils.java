package web.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.steps.Hooks;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

public class HelperUtils {

    static int timeoutForExplicitWait = Integer.parseInt(ConfigReader.getProperty("timeoutForExplicitWait"));

    public static void waitForElementVisible(WebDriver driver, WebElement element) {
        wait(driver).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementsVisible(WebDriver driver, List<WebElement> elements) {
        wait(driver).until(ExpectedConditions.visibilityOf(elements.get(0)));
    }


    public static void waitForElementClickable(WebDriver driver, WebElement element) {
        wait(driver).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForUrlVisible(WebDriver driver, String currentUrl) {
        wait(driver).until(ExpectedConditions.urlToBe(currentUrl));
    }

    public static WebDriverWait wait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutForExplicitWait));
    }

    /**
     * @param checked set checked to true if you want to select toggle, set false to unselect
     * @param element element is toggle element want to be checked or unchecked
     */
    public static void checkCheckbox(boolean checked, WebElement element) {
        WebElement checkbox = element;
        if (checked != checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public static String fuboEmailGenerator(String initialEmail) {
        StringBuilder currentNumbers = new StringBuilder();
        try {
            String firstHalf = initialEmail.trim().substring(0, 7);
            String lastHalf = initialEmail.trim().substring(7);
            Random rand = new Random();
            for (int i = 0; i < 6; i++) {
                int randomInt = rand.nextInt(10);
                currentNumbers.append(randomInt);
            }
            return firstHalf + "+" + currentNumbers + lastHalf;
        } catch (StringIndexOutOfBoundsException e) {
            return null;
        }
    }

    public static String fuboSBPasswordGenerator() {
        String frontHalfOfPassword = "FuboTV#";

        Date today = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        //The Testing env is using london server time for the passwords
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        String londonDate = dateFormat.format(today);
        return frontHalfOfPassword + londonDate;
    }

    public static StringBuilder generatePhoneNumber() {
        StringBuilder currentNumbers = new StringBuilder();
        StringBuilder phoneNumber = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            int randomInt = rand.nextInt(10);
            currentNumbers.append(randomInt);
            phoneNumber.append(currentNumbers);
        }
        return phoneNumber;
    }

    public static String generateFourSSN() {
        StringBuilder sSN = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            String randomInt = Integer.toString(rand.nextInt(10));
            sSN.append(randomInt);
        }
        return sSN.toString();
    }

    public static void addQueryToEndOfUrl(String appendToUrl) {
        WebDriver driver = Hooks.driver;
        String CurrentUrl = driver.getCurrentUrl();
        driver.get(CurrentUrl.concat(appendToUrl));
    }

    public static void scrollUpUsingKeys(WebDriver driver, int howManyBackBtnClicks){
        for (int i = 0; i < howManyBackBtnClicks; i++) {
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.ARROW_UP);
        }
    }
}