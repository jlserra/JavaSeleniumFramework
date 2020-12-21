package pageobjects;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LoginPage extends BasePage{

    public LoginPage(MobileDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/spbtn_submit")
    @iOSXCUITFindBy(id = "a")
    private MobileElement btnNext;

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/spbtn_pin_key_1")
    @iOSXCUITFindBy(id = "a")
    private MobileElement btn1;

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/spet_acct")
    @iOSXCUITFindBy(id = "a")
    private MobileElement txtfieldMobileNumber;

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/sptxt_title")
    @iOSXCUITFindBy(id = "a")
    private MobileElement txtWelcome;

    @Step("Step: Click Button Next")
    public void clickBtnNext() {
        log.info("Step: Click Button Next");
        action.click(btnNext);
    }

    @Step("Step: Enter Prepaid Number")
    public void enterPrepaidNumber(String text) throws IOException {
        log.info("Step: Enter Prepaid Number");
        action.sendKeys(txtfieldMobileNumber, text);
        action.takeSnapShot("Enter Prepaid Number");
    }

    @Step("Step: Verify if Mobile Number is Entered Correctly")
    public Boolean verifyIfMobileNumberIsEntered(String text) throws IOException {
        log.info("Step: Verify if Mobile Number is Entered Correctly");
        return action.getText(txtfieldMobileNumber).equals(text);
    }

    @Step("Step: Verify if Button Next is Enabled")
    public Boolean verifyIfBtnNextIsEnabled(){
        log.info("Step: Verify if Button Next is Enabled");
        return action.isEnabled(btnNext);

    }

    @Step("Step: Verify if User is in Login Page")
    public Boolean verifyIfLoginPage(){
        return action.isDisplayed(txtWelcome);
    }

}





