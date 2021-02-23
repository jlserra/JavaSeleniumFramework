package utilities;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.BasePage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ActionUtilities extends BasePage {

    static int explicitWaitDefault = 10;
    MobileDriver driver;

    public ActionUtilities(MobileDriver driver) {
        this.driver = driver;
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String name) {
        return name;
    }

    public MobileElement getElement(String key) {
        MobileElement element = null;
        String[] locator = excel.getLocator(key);
        String locatorType = locator[0];
        String locatorValue = locator[1];

        log.info("Finding element by " + locatorType + " : " + locatorValue);

        try {
            switch (locatorType) {
                case "id":
                    element = (MobileElement) driver.findElementById(locatorValue);
                    break;
                case "xpath":
                    element = (MobileElement) driver.findElementByXPath(locatorValue);
                    break;
                case "classname":
                    element = (MobileElement) driver.findElementByClassName(locatorValue);
                    break;
                case "cssselector":
                    element = (MobileElement) driver.findElementByCssSelector(locatorValue);
                    break;
                case "name":
                    element = (MobileElement) driver.findElementByName(locatorValue);
                    break;
                case "linktext":
                    element = (MobileElement) driver.findElementByLinkText(locatorValue);
                    break;
                case "partiallinktext":
                    element = (MobileElement) driver.findElementByPartialLinkText(locatorValue);
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

    public void click(MobileElement element) {
        try {
            element.click();
            log.info("Element clicked");
        } catch (Exception e) {
            log.error(e, "Unable to click element");
        }
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

    public void sendKeys(MobileElement element, String text) {
        try {
            element.sendKeys(text);
            log.info("Text entered successfully");
        } catch (Exception e) {
            log.error(e, "Unable to send keys to element");
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

    public String getText(MobileElement element) {
        String text = "";
        try {
            text = element.getText().replaceAll("[\n\r]", "");
            log.info("Text fetched successfully");
        } catch (Exception e) {
            log.error(e, "Unable to get element text");
        }
        return text;
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

    public Boolean isDisplayed(MobileElement element) {
        boolean isDisplayed = false;
        try {
            isDisplayed = element.isDisplayed();
            log.info("Element displayed");
        } catch (Exception e) {
            log.error(e, "Element not displayed");
        }
        return isDisplayed;
    }

    public Boolean isDisplayed(String locator) {
        boolean isDisplayed = false;
        try {
            isDisplayed = new WebDriverWait(driver, ConfigUtilities.Timers.appStandard.getValue())
                    .until(ExpectedConditions.visibilityOf(getElement(locator))).isDisplayed();
            log.info("Element displayed with locator " + locator);
        } catch (Exception e) {
            log.error(e, "Element not displayed with locator " + locator);
        }
        return isDisplayed;
    }

    public Boolean isEnabled(MobileElement element) {
        boolean isEnabled = false;
        try {
            isEnabled = element.isEnabled();
            log.info("Element enabled");
        } catch (Exception e) {
            log.error(e, "Element not enabled");
        }
        return isEnabled;
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

    public void waitForElementToBeVisible(MobileElement element) {
        try {
            new WebDriverWait(driver, explicitWaitDefault)
                    .until(ExpectedConditions.visibilityOf(element));
            log.info("Element now visible");
        } catch (Exception e) {
            log.error(e, "Element not visible");
        }
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

    public void waitForElementToBeClickable(MobileElement element) {
        try {
            new WebDriverWait(driver, explicitWaitDefault)
                    .until(ExpectedConditions.elementToBeClickable(element));
            log.info("Element now clickable");
        } catch (Exception e) {
            log.error(e, "Element not clickable");
        }
    }

    public Boolean waitForElementToBeClickable(String key, ConfigUtilities.Timers timer) {
        boolean clickable = false;
        try {
            log.info("Waiting for element to be clickable " + key);
            clickable = new WebDriverWait(driver, timer.getValue())
                    .until(ExpectedConditions.elementToBeClickable(getElement(key)))
                    .isEnabled();
            log.info("Element now clickable");
        } catch (Exception e) {
            log.error(e, "Element not clickable");
        }
        return clickable;
    }

    public void waitForElementToBeSelected(MobileElement element) {
        try {
            new WebDriverWait(driver, explicitWaitDefault)
                    .until(ExpectedConditions.elementToBeSelected(element));
            log.info("Element now selected");
        } catch (Exception e) {
            log.error(e, "Element not selected");
        }
    }

    public void waitForElementToBeInvisible(MobileElement element) {
        try {
            new WebDriverWait(driver, explicitWaitDefault)
                    .until(ExpectedConditions.invisibilityOf(element));
            log.info("Element now invisible");
        } catch (Exception e) {
            log.error(e, "Element not invisible");
        }
    }

    public void takeSnapShot(String description) throws IOException {

        String testname = config.testcase;

        // Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        // Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        // Move image file to new destination
        File DestFile = new File("./screenshots/" + testname + "/" + testname + " - " + description + ".png");
        // Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);

        saveScreenshotPNG();
        saveTextLog(testname + "-" + description);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public void swipe(Direction dir) {

        Dimension dim = driver.manage().window().getSize();
        int height = dim.getHeight();
        int width = dim.getWidth();
        int startx = 0, starty = 0, endx = 0, endy = 0;

        switch (dir) {
            case UP:
                startx = (int) (width * .50);
                starty = (int) (height * .95);
                endx = (int) (width * .50);
                endy = (int) (height * .05);
                break;
            case DOWN:
                startx = (int) (width * .50);
                starty = (int) (height * .05);
                endx = (int) (width * .50);
                endy = (int) (height * .95);
                break;
            case LEFT:
                startx = (int) (width * .95);
                starty = (int) (height * .50);
                endx = (int) (width * .05);
                endy = (int) (height * .50);
                break;
            case RIGHT:
                startx = (int) (width * .05);
                starty = (int) (height * .50);
                endx = (int) (width * .95);
                endy = (int) (height * .50);
                break;
            default:
                break;
        }

        TouchAction action = new TouchAction(driver);
        action.longPress(PointOption.point(startx, starty)).moveTo(PointOption.point(endx, endy)).release().perform();
        log.info("Swiping " + dir);

    }

    public MobileElement findElementBy(String loc) {
        return (MobileElement) driver.findElementByXPath(loc);
    }

    public void clearTextField(MobileElement textfield) {
        textfield.clear();
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }


}
