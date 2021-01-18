package utilities;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
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
    static int implicitWaitDefault = 10;
    MobileDriver driver;

    public ActionUtilities(MobileDriver driver) {
        this.driver = driver;
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String name) {
        return name;
    }

    public MobileElement find(By locator) {
        MobileElement element = null;
        try {
            driver.findElement(locator);
            log.info("Element found");
        } catch (Exception e) {
            log.error(e, "Unable to find element");
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

    public void sendKeys(MobileElement element, String text) {
        try {
            element.sendKeys(text);
            log.info("Text entered successfully");
        } catch (Exception e) {
            log.error(e, "Unable to send keys to element");
        }
    }

    public String getText(MobileElement element) {
        String text = "";
        try {
            text = element.getText();
            log.info("Text fetched successfully");
        } catch (Exception e) {
            log.error(e, "Unable to get element text");
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

    public void waitForElementToBeVisible(MobileElement element) {
        try {
            new WebDriverWait(driver, explicitWaitDefault)
                    .until(ExpectedConditions.visibilityOf(element));
            log.info("Element now visible");
        } catch (Exception e) {
            log.error(e, "Element not visible");
        }
    }

    public void implicitlyWait() {
        driver.manage().timeouts().implicitlyWait(implicitWaitDefault, TimeUnit.SECONDS);
    }

    public void implicitlyWait(int secs) {
        driver.manage().timeouts().implicitlyWait(secs * 1000, TimeUnit.SECONDS);
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

        String testname = getTestName();

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

    public static enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    public MobileElement findElementBy(String loc){
        return (MobileElement) driver.findElementByXPath(loc);
    }

    public void clearTextField(MobileElement textfield){
        textfield.clear();
    }

}
