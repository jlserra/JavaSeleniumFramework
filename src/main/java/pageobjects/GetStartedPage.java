package pageobjects;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;
import utilities.ActionUtilities;

import static org.testng.Assert.assertTrue;

public class GetStartedPage extends BasePage {

    @AndroidFindBy(xpath = "//*[@resource-id='ph.com.globe.mybusiness:id/btn_walkthrough_start']")
    @iOSXCUITFindBy(id = "a")
    private MobileElement btnGetStarted;

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/title")
    private MobileElement txtGetStartedTitle;

    public GetStartedPage(MobileDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Step: Click Get Started button")
    public void clickBtnGetStarted() throws Exception {
        log.info("Step: Click Get Started button");
        action.takeSnapShot("User clicked get started button");
        action.click(btnGetStarted);
    }

    @Step("Step: Verify if user is in Get Started Page")
    public boolean verifyIfGetStartedPage() throws Exception {
        log.info("Step: Verify if user is in Get Started Page");
        action.waitForElementToBeVisible(btnGetStarted);
        action.takeSnapShot("User is in Get Started Page");
        return action.isDisplayed(btnGetStarted);
    }

    @Step("Step: Verify Get Started Pages")
    public void verifyGetStartedPages() throws Exception {

        log.info("Step: Verify Get Started Pages");
        action.waitForElementToBeVisible(txtGetStartedTitle);
        assertTrue(action.getText(txtGetStartedTitle).equals("Welcome\nto Globe myBusiness"));
        action.takeSnapShot( "Welcome to Globe myBusiness");
        action.swipe(ActionUtilities.Direction.LEFT);
        new GetStartedPage(driver);
        Thread.sleep(2000);

        action.waitForElementToBeVisible(txtGetStartedTitle);
        assertTrue(action.getText(txtGetStartedTitle).equals("Manage your myBusiness Prepaid Internet number"));
        action.takeSnapShot( "Manage your myBusiness Prepaid Internet number");
        action.swipe(ActionUtilities.Direction.LEFT);
        new GetStartedPage(driver);
        Thread.sleep(2000);

        action.waitForElementToBeVisible(txtGetStartedTitle);
        assertTrue(action.getText(txtGetStartedTitle).equals("Track your data usage"));
        action.takeSnapShot( "Track your data usage");
        action.swipe(ActionUtilities.Direction.LEFT);
        new GetStartedPage(driver);
        Thread.sleep(2000);

        action.waitForElementToBeVisible(txtGetStartedTitle);
        assertTrue(action.getText(txtGetStartedTitle).equals("Get more data"));
        action.takeSnapShot("Get More Data");
    }


}
