package utilities;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActionUtilities {

    static WebDriver driver;
    static ConfigUtilities config;
    static ExcelUtilities excel;
    static LoggerUtilities log;

    public ActionUtilities(WebDriver driver, LoggerUtilities log, ConfigUtilities config, ExcelUtilities excel) {
        ActionUtilities.driver = driver;
        ActionUtilities.config = config;
        ActionUtilities.excel = excel;
        ActionUtilities.log = log;
    }

    @Attachment(value = "Logs", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    public By getLocator(String key) throws Exception {
        By by;
        String[] locator = excel.getLocator(key);
        String locatorType = locator[0];
        String locatorValue = locator[1];

        log.info("Finding element by " + locatorType + " : " + locatorValue);

        try {
            switch (locatorType) {
                case "id":
                    by = By.id(locatorValue);
                    break;
                case "xpath":
                    by = By.xpath(locatorValue);
                    break;
                case "classname":
                    by = By.className(locatorValue);
                    break;
                case "cssselector":
                    by = By.cssSelector(locatorValue);
                    break;
                case "name":
                    by = By.name(locatorValue);
                    break;
                case "linktext":
                    by = By.linkText(locatorValue);
                    break;
                case "partiallinktext":
                    by = By.partialLinkText(locatorValue);
                    break;
                default:
                    log.error("Invalid locator type: " + locatorType);
                    throw new Exception("Invalid locator type: " + locatorType);
            }
        } catch (Exception e) {
            log.error(e, "Unable to find element " + locatorValue);
            throw new Exception("Invalid locator type: " + locatorType);
        }
        return by;
    }

    public WebElement getElement(String locator) throws Exception {
        WebElement webElement;
        try {
            webElement = new WebDriverWait(driver, ConfigUtilities.Timers.appStandard.getValue())
                    .until(ExpectedConditions.visibilityOfElementLocated(getLocator(locator)));
            log.info("Successfully got element : " + locator);
        } catch (Exception e) {
            log.error(e, "Unable to get element " + locator);
            throw new Exception("Unable to get element " + locator);
        }
        return webElement;
    }

    public List<WebElement> getElements(String locator) throws Exception {
        List<WebElement> webElements;
        try {
            webElements = new WebDriverWait(driver, ConfigUtilities.Timers.appStandard.getValue())
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getLocator(locator)));
            log.info("Successfully got elements : " + locator);
        } catch (Exception e) {
            log.error(e, "Unable to get elements " + locator);
            throw new Exception("Unable to get elements " + locator);
        }
        return webElements;
    }

    public void click(String locator) throws Exception {
        try {
            new WebDriverWait(driver, ConfigUtilities.Timers.appStandard.getValue())
                    .until(ExpectedConditions.elementToBeClickable(getLocator(locator)))
                    .click();
            log.info("Successfully clicked element : " + locator);
        } catch (Exception e) {
            log.error(e, "Unable to click element " + locator);
            throw new Exception("Unable to click element " + locator);
        }
    }

    public void sendKeys(String locator, String text) throws Exception {
        try {
            new WebDriverWait(driver, ConfigUtilities.Timers.appStandard.getValue())
                    .until(ExpectedConditions.visibilityOfElementLocated(getLocator(locator)))
                    .sendKeys(text);
            log.info("Able to enter text successfully " + text);
        } catch (Exception e) {
            log.error(e, "Unable to enter text successfully " + text);
            throw new Exception("Unable to enter text successfully " + text);
        }
    }

    public String getText(String locator) throws Exception {
        String text = "";
        try {
            text = new WebDriverWait(driver, ConfigUtilities.Timers.appStandard.getValue())
                    .until(ExpectedConditions.visibilityOfElementLocated(getLocator(locator)))
                    .getText().replaceAll("[\n\r]", "");
            log.info("Able to get text successfully " + text);
        } catch (Exception e) {
            log.error(e, "Unable to get text from locator " + locator);
            throw new Exception("Unable to get text from locator " + locator);
        }
        return text;
    }

    public Boolean isDisplayed(String locator) throws Exception {
        boolean isDisplayed = false;
        try {
            isDisplayed = new WebDriverWait(driver, ConfigUtilities.Timers.slow.getValue())
                    .until(ExpectedConditions.visibilityOfElementLocated(getLocator(locator))).isDisplayed();
            log.info("Element displayed with locator " + locator);
        } catch (Exception e) {
            log.error(e, "Element not displayed with locator " + locator);
            throw new Exception("Element not displayed with locator " + locator);
        }
        return isDisplayed;
    }

    public Boolean isEnabled(String locator) throws Exception {
        boolean isEnabled = false;
        try {
            isEnabled = new WebDriverWait(driver, ConfigUtilities.Timers.appStandard.getValue())
                    .until(ExpectedConditions.visibilityOfElementLocated(getLocator(locator))).isEnabled();
            log.info("Element is enabled with locator " + locator);
        } catch (Exception e) {
            log.error(e, "Element is not enabled with locator " + locator);
            throw new Exception("Element is not enabled with locator " + locator);
        }
        return isEnabled;
    }

    public Boolean waitForElementToBeVisible(String locator, ConfigUtilities.Timers timers) throws Exception {
        boolean visible = false;
        try {
            log.info(String.valueOf(timers.getValue()));
            log.info("Waiting for element to be now visible");
            visible = new WebDriverWait(driver, timers.getValue())
                    .until(ExpectedConditions.visibilityOfElementLocated(getLocator(locator)))
                    .isDisplayed();
            log.info("Element now visible with " + locator);
        } catch (Exception e) {
            log.error(e, "Element not visible with " + locator);
            throw new Exception("Element not visible with " + locator);
        }
        return visible;
    }

    public void implicitlyWait() {
        driver.manage().timeouts().implicitlyWait(ConfigUtilities.Timers.appStandard.getValue(), TimeUnit.MILLISECONDS);
    }

    public void implicitlyWait(ConfigUtilities.Timers timer) {
        driver.manage().timeouts().implicitlyWait(timer.getValue(), TimeUnit.MILLISECONDS);
    }

    public Boolean waitForElementToBeClickable(String locator, ConfigUtilities.Timers timer) throws Exception {
        boolean clickable = false;
        try {
            log.info("Waiting for element to be clickable " + locator);
            clickable = new WebDriverWait(driver, timer.getValue())
                    .until(ExpectedConditions.elementToBeClickable(getLocator(locator)))
                    .isEnabled();
            log.info("Element now clickable");
        } catch (Exception e) {
            log.error(e, "Element not clickable" + locator);
            throw new Exception("Element not clickable " + locator);
        }
        return clickable;
    }

    public void takeSnapShot(String description) throws IOException {

        String testName = config.getTestcase();
        String suiteName = config.getSuiteName();

        // Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        // Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        // Move image file to new destination
        File DestFile = new File("./screenshots/" + suiteName + "/" + testName + "/" + testName + " - " + description + ".png");
        // Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);

        saveScreenshotPNG();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public void scrollIntoElement(String locator) throws Exception {

        Boolean isVisible = waitForElementToBeVisible(locator, ConfigUtilities.Timers.appStandard);

        if(isVisible){
            log.info("Scrolling into element with locator: " + locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
        }
    }

    public void mouseHover(String locator) throws Exception {

        Boolean isVisible = waitForElementToBeVisible(locator, ConfigUtilities.Timers.appStandard);

        if(isVisible){
            log.info("Mouse Hovering into element with locator: " + locator);
            Actions action = new Actions(driver);
            action.moveToElement(getElement(locator)).perform();
        }
    }


}
