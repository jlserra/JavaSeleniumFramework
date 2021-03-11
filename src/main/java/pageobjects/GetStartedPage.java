package pageobjects;

import io.appium.java_client.MobileDriver;
import io.qameta.allure.Step;
import utilities.ActionUtilities;
import utilities.ConfigUtilities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GetStartedPage extends BasePage {

    MobileDriver driver;

    public GetStartedPage(MobileDriver driver) {
        this.driver = driver;
    }

    @Step("Step: Click Get Started button")
    public void clickBtnGetStarted() throws Exception {
        log.info("Step: Click Get Started button");
        action.takeSnapShot("User clicked get started button");
        action.click("btnGetStarted");
    }

    @Step("Step: Verify if user is in Get Started Page")
    public boolean verifyIfGetStartedPage() throws Exception {
        log.info("Step: Verify if user is in Get Started Page");
        assertTrue(action.waitForElementToBeVisible("btnGetStarted", ConfigUtilities.Timers.slow));
        action.takeSnapShot("User is in Get Started Page");
        return action.isDisplayed("btnGetStarted");
    }

    @Step("Step: Verify Get Started Pages")
    public void verifyGetStartedPages() throws Exception {

        log.info("Step: Verify Get Started Pages");
        verifyIfGetStartedPage();
        assertEquals(excel.getTestdata("text1"), action.getText("txtGetStartedTitle"));
        action.takeSnapShot(excel.getTestdata("text1"));
        action.swipe(ActionUtilities.Direction.LEFT);
        new GetStartedPage(driver);
        Thread.sleep(2000);

        action.waitForElementToBeVisible("txtGetStartedTitle", ConfigUtilities.Timers.appStandard);
        assertEquals(excel.getTestdata("text2"), action.getText("txtGetStartedTitle"));
        action.takeSnapShot(excel.getTestdata("text2"));
        action.swipe(ActionUtilities.Direction.LEFT);
        new GetStartedPage(driver);
        Thread.sleep(2000);

        action.waitForElementToBeVisible("txtGetStartedTitle", ConfigUtilities.Timers.appStandard);
        assertEquals(excel.getTestdata("text3"), action.getText("txtGetStartedTitle"));
        action.takeSnapShot(excel.getTestdata("text3"));
        action.swipe(ActionUtilities.Direction.LEFT);
        new GetStartedPage(driver);
        Thread.sleep(2000);

        action.waitForElementToBeVisible("txtGetStartedTitle", ConfigUtilities.Timers.appStandard);
        assertEquals(excel.getTestdata("text4"), action.getText("txtGetStartedTitle"));
        action.takeSnapShot(excel.getTestdata("text4"));
    }

}
