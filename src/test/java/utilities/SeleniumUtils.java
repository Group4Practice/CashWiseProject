package utilities;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class SeleniumUtils {
    /**
     * This method clicks on the given element, switches to the newly opened tab
     * and prints its url.
     * @param driver - is used to open web application
     * @param element - is clicked to open new tab
     */
    public static void switchToNewTab(WebDriver driver, WebElement element){
        String mainWindow = driver.getWindowHandle();

        element.click();

        for (String windowHandle : driver.getWindowHandles()){
            if (!windowHandle.equals(mainWindow)){
                driver.switchTo().window(windowHandle);
            }
        }

        System.out.println("Currently, the driver is on: " + driver.getCurrentUrl());
    }


    /**
     * This method waits for element to be clickable
     * before clicking on it
     * @param driver - is used to open web application
     * @param element - to be clicked
     */
    public static void click(WebDriver driver, WebElement element){
        FluentWait wait = new FluentWait(driver)
                .ignoring(ElementClickInterceptedException.class)
                .withTimeout(Duration.ofSeconds(30));

        wait.until(ExpectedConditions.elementToBeClickable(element));

        element.click();
    }
    //This method accept alert, if alert not there it ignore the exception
    public static void acceptAlert(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.alertIsPresent());
        try{
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e){
            System.out.println("Alert doesn't exist");
            e.printStackTrace();
        }
    }
    //This method dismiss alert, if alert not there it ignore the exception
    public static void dismissAlert(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.alertIsPresent());
        try{
            driver.switchTo().alert().dismiss();
        } catch (NoAlertPresentException e){
            System.out.println("Alert doesn't exist");
            e.printStackTrace();
        }
    }
    /**
     * This method waits for text to apear in the element
     * then verify if it matches with expected text
     * @param nomOfSec to wait for
     * @param expectedText to verify text
     * @param driver - is used to open web application
     * @param element - to be clicked
     */
    public static void verifyTextInElement(WebDriver driver, int nomOfSec, WebElement element, String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(nomOfSec));
        wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));

        Assertions.assertEquals(expectedText, element.getText());

    }
    public static void clearAndSendKeys(WebDriver driver, WebElement element, String textToSend){
        Actions actions = new Actions(driver);

        actions.keyDown(element, Keys.COMMAND).sendKeys("a");
        actions.keyUp(element, Keys.COMMAND);
        actions.keyDown(element, Keys.BACK_SPACE);
        actions.keyUp(element, Keys.BACK_SPACE);

        actions.build().perform();

        element.sendKeys(textToSend);
    }


}
