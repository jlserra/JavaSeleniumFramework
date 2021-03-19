package utilities;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ActionUtilities {

    static WebDriver driver;
    static ConfigUtilities config;
    static ExcelUtilities excel;
    static LoggerUtilities log;

    public ActionUtilities(WebDriver driver, LoggerUtilities log, ConfigUtilities config, ExcelUtilities excel ) {
        ActionUtilities.driver = driver;
        ActionUtilities.config = config;
        ActionUtilities.excel = excel;
        ActionUtilities.log = log;
    }

    public WebElement getElement(String key) {
        WebElement element = null;
        String[] locator = excel.getLocator(key);
        String locatorType = locator[0];
        String locatorValue = locator[1];

        log.info("Finding element by " + locatorType + " : " + locatorValue);

        try {
            switch (locatorType) {
                case "id":
                    element = driver.findElement(By.id(locatorValue));
                    break;
                case "xpath":
                    element = driver.findElement(By.xpath(locatorValue));
                    break;
                case "classname":
                    element = driver.findElement(By.className(locatorValue));
                    break;
                case "cssselector":
                    element = driver.findElement(By.cssSelector(locatorValue));
                    break;
                case "name":
                    element = driver.findElement(By.name(locatorValue));
                    break;
                case "linktext":
                    element = driver.findElement(By.linkText(locatorValue));
                    break;
                case "partiallinktext":
                    element = driver.findElement(By.partialLinkText(locatorValue));
                    break;
                default:
                    log.error("Invalid locator type: " + locatorType);
                    throw new Exception("Invalid locator type: " + locatorType);
            }
        } catch (Exception e) {
            log.error(e, "Unable to find element " + locatorValue);
        }
        return element;
    }

    public void click(String locator) {
        try {
            new WebDriverWait(driver, ConfigUtilities.Timers.appStandard.getValue())
                    .until(ExpectedConditions.elementToBeClickable(getElement(locator)))
                    .click();
            log.info("Successfully clicked element : " + locator);
        } catch (Exception e) {
            log.error(e, "Unable to click element " + locator);
        }
    }

    public void sendKeys(String locator, String text) {
        try {
            new WebDriverWait(driver, ConfigUtilities.Timers.appStandard.getValue())
                    .until(ExpectedConditions.visibilityOf(getElement(locator)))
                    .sendKeys(text);
            log.info("Able to enter text successfully " + text);
        } catch (Exception e) {
            log.error(e, "Unable to enter text successfully " + text);
        }
    }

    public String getText(String locator) {
        String text = "";
        try {
            text = new WebDriverWait(driver, ConfigUtilities.Timers.appStandard.getValue())
                    .until(ExpectedConditions.visibilityOf(getElement(locator)))
                    .getText().replaceAll("[\n\r]", "");
            log.info("Able to get text successfully " + text);
        } catch (Exception e) {
            log.error(e, "Unable to get text from locator " + locator);
        }
        return text;
    }

    public Boolean isDisplayed(String locator) {
        boolean isDisplayed = false;
        try {
            isDisplayed = new WebDriverWait(driver, ConfigUtilities.Timers.slow.getValue())
                    .until(ExpectedConditions.visibilityOf(getElement(locator))).isDisplayed();
            log.info("Element displayed with locator " + locator);
        } catch (Exception e) {
            log.error(e, "Element not displayed with locator " + locator);
        }
        return isDisplayed;
    }

    public Boolean isEnabled(String locator) {
        boolean isEnabled = false;
        try {
            isEnabled = new WebDriverWait(driver, ConfigUtilities.Timers.appStandard.getValue())
                    .until(ExpectedConditions.visibilityOf(getElement(locator))).isEnabled();
            log.info("Element is enabled with locator " + locator);
        } catch (Exception e) {
            log.error(e, "Element is not enabled with locator " + locator);
        }
        return isEnabled;
    }

    public Boolean waitForElementToBeVisible(String locator, ConfigUtilities.Timers timers) {
        boolean visible = false;
        try {
            log.info("Waiting for element to be now visible");
            visible = new WebDriverWait(driver, timers.getValue())
                    .until(ExpectedConditions.visibilityOf(getElement(locator)))
                    .isDisplayed();
            log.info("Element now visible with " + locator);
        } catch (Exception e) {
            log.error(e, "Element not visible with " + locator);
        }
        return visible;
    }

    public void implicitlyWait() {
        driver.manage().timeouts().implicitlyWait(ConfigUtilities.Timers.appStandard.getValue(), TimeUnit.MILLISECONDS);
    }

    public void implicitlyWait(ConfigUtilities.Timers timer) {
        driver.manage().timeouts().implicitlyWait(timer.getValue(), TimeUnit.MILLISECONDS);
    }

    public Boolean waitForElementToBeClickable(String locator, ConfigUtilities.Timers timer) {
        boolean clickable = false;
        try {
            log.info("Waiting for element to be clickable " + locator);
            clickable = new WebDriverWait(driver, timer.getValue())
                    .until(ExpectedConditions.elementToBeClickable(getElement(locator)))
                    .isEnabled();
            log.info("Element now clickable");
        } catch (Exception e) {
            log.error(e, "Element not clickable");
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
    public byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Logs", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

}
