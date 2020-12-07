package pageobjects;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;
import utilities.ActionUtilities;

public class GetStartedPage extends BasePage {

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/btn_walkthrough_start")
    @iOSXCUITFindBy(id = "")
    public MobileElement btnGetStarted;
    MobileDriver driver;

    public GetStartedPage(MobileDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Step: Click Get Started button")
    public void clickBtnGetStarted() {
        btnGetStarted.click();
        log.debug("Step: Get Started button clicked");
    }

    @Step("Step: Check if Get Started button is visible")
    public boolean verifyBtnGetStarted() throws Exception {
        action.waitForElementToBeVisible(btnGetStarted);
        log.debug("Step: Get Started button visible");
        action.takeSnapShot("Button Get Started");
        return btnGetStarted.isDisplayed();
    }


}
