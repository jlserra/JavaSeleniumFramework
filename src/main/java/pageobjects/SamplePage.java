package pageobjects;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

import utilities.ActionUtilities;

public class SamplePage extends BasePage {

    @AndroidFindBy( xpath = "//android.widget.Button[@content-desc='buttonTestCD']" )
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Activity Indicators']")
    public MobileElement itemActivityIndicators;

    MobileDriver driver;

    public SamplePage(MobileDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Step: Click Item")
    public void clickItemActivityIndicators() {
        itemActivityIndicators.click();
        log.debug("Item clicked");
    }

    @Step("Step: Verify Item Exist")
    public void verifyItemActivityIndicators() throws Exception {

        ActionUtilities.waitForElementToBeVisible(itemActivityIndicators);
        ActionUtilities.takeSnapShot("testing");
        log.debug("Item Exists");

    }

}
