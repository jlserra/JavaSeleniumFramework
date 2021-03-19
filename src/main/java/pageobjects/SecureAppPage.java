package pageobjects;


import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigUtilities;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;


public class SecureAppPage extends BasePage {

    public SecureAppPage(MobileDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//*[@resource-id='ph.com.globe.mybusiness:id/ll_secure_app']//descendant::android.widget.TextView[1]")
    @iOSXCUITFindBy( id = "a" )
    private MobileElement txtSecureApp;

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/pet_pin")
    @iOSXCUITFindBy(id = "placeholder")
    public MobileElement txtSecurePin;

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/sptxt_pin_error_spiel")
    @iOSXCUITFindBy(id = "placeholder")
    private MobileElement unmatchedSecurePin;

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/pbtn_pin_submit")
    @iOSXCUITFindBy(id= "a")
    private MobileElement btnNext;

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/spbtn_pin_key_1")
    @iOSXCUITFindBy(id= "a")
    private MobileElement btn1;

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/spbtn_pin_key_2")
    @iOSXCUITFindBy(id= "a")
    private MobileElement btn2;

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/spbtn_pin_key_3")
    @iOSXCUITFindBy(id= "a")
    private MobileElement btn3;

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/spbtn_pin_key_4")
    @iOSXCUITFindBy(id= "a")
    private MobileElement btn4;

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/spbtn_pin_key_clear")
    @iOSXCUITFindBy(id= "a")
    private MobileElement btnDelete;

    @Step("Step: Verify if User is in Secure Application Page")
    public Boolean verifyIfSecurePage(){
        log.info("Step: Verify if User is in Secure Application Page");
        action.waitForElementToBeVisible(txtSecureApp);
        assertTrue(action.getText(txtSecureApp).equalsIgnoreCase("Secure the app"));
        return action.isDisplayed(txtSecureApp);
    }

    @Step("Step: Verify if User is in Secure Application Confirmation Page")
    public Boolean verifyIfSecurePageConfirmation() throws InterruptedException {
        log.info("Step: Verify if User is in Secure Application Confirmation Page");
        new SecureAppPage(driver);
        Thread.sleep(1000);
        assertFalse(action.getText(txtSecureApp).equalsIgnoreCase("Secure the app"));
        return action.isDisplayed(txtSecureApp);
    }

    @Step("Step: Verify error spiel for invalid number")
    public boolean verifyUnmatchedSecurePinErrorSpiel(String errorSpiel){
        action.waitForElementToBeVisible(unmatchedSecurePin);
        return action.getText(unmatchedSecurePin).equals(errorSpiel);
    }

    @Step("Step: Click Button Next")
    public void clickBtnNext(){
        log.info("Step: Click Button Next");
        action.click(btnNext);
        action.implicitlyWait(ConfigUtilities.Timers.quick);
    }

    @Step("Step: Verify if Button next is enabled")
    public void verifyIfBtnNextIsEnabled(){
        log.info("Step: Verify if Button next is enabled");
        action.waitForElementToBeClickable(btnNext);
    }

    @Step("Step: Delete Secure Pin")
    public void clickBtnDelete(){
        log.info("Step: Step: Delete Secure Pin");
            action.click(btnDelete);
    }

    @Step("Step: Enter Pin")
    public void enterPin(String text) {
        for (char c: text.toCharArray()){
            log.info("Step: Enter Pin" + c);
            switch(c){
                case '1':
                    action.click(btn1);
                    break;
                case '2':
                    action.click(btn2);
                    break;
                case '3':
                    action.click(btn3);
                    break;
                case '4':
                    action.click(btn4);
                    break;
            }
            action.implicitlyWait(ConfigUtilities.Timers.fast);
        }
    }










}
