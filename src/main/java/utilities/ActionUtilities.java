package utilities;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActionUtilities {

    static MobileDriver driver;
    static int explicitWaitDefault = 10;
    static int implicitWaitDefault = 10;

    public ActionUtilities(MobileDriver driver) {
        this.driver = driver;
    }

    public static void waitForElementToBeVisible(MobileElement element) {
        new WebDriverWait(driver, explicitWaitDefault)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void implicitlyWait() {
        driver.manage().timeouts().implicitlyWait(implicitWaitDefault, TimeUnit.SECONDS);
    }

    public static void waitForElementToBeClickable(MobileElement element) {
        new WebDriverWait(driver, explicitWaitDefault)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementToBeSelected(MobileElement element) {
        new WebDriverWait(driver, explicitWaitDefault)
                .until(ExpectedConditions.elementToBeSelected(element));
    }


    public static void waitForElementToBeInvisible(MobileElement element) {
        new WebDriverWait(driver, explicitWaitDefault)
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public static void takeSnapShot(String name) throws IOException {

            // Convert web driver object to TakeScreenshot

            TakesScreenshot scrShot = ((TakesScreenshot) driver);

            // Call getScreenshotAs method to create image file

            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

            // Move image file to new destination

            File DestFile = new File("./screenshots/" + name + ".png");

            // Copy file at destination

            FileUtils.copyFile(SrcFile, DestFile);

    }

}
