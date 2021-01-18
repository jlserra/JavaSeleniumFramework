package pageobjects;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class WelcomePage extends BasePage{

    public WelcomePage(MobileDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private String keypadPartialExpath = "//android.widget.Button[@resource-id='ph.com.globe.mybusiness:id/spbtn_pin_key_";


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

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/sptxt_error_spiel")
    @iOSXCUITFindBy(id = "placeholder")
    private MobileElement invalidNumberErrorSpiel;

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/img_nominate_account_help")
    @iOSXCUITFindBy(id = "placeholder")
    private MobileElement helpIcon;

    @Step("Step: Click Button Next")
    public void clickBtnNext() {
        log.info("Step: Click Button Next");
        action.click(btnNext);
    }

    @Step("Step: Enter Prepaid Number")
    public void enterPrepaidNumber(String text) throws IOException {
        log.info("Step: Enter Prepaid Number");
//        action.sendKeys(txtfieldMobileNumber, text);
        char[] keys = text.toCharArray();
        for(char key : keys){
            this.clickButton(key);
        }
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

    @Step("Step: Verify error spiel for invalid number")
    public boolean verifyInvalidNumberErrorSpiel(String errorSpiel){
        action.waitForElementToBeVisible(invalidNumberErrorSpiel);
        return action.getText(invalidNumberErrorSpiel).equals(errorSpiel);
    }

    @Step("Step: Verify if user is at Welcome page")
    public boolean isAt(){
        return action.isDisplayed(txtWelcome) &&
                action.isDisplayed(txtfieldMobileNumber) &&
                action.isDisplayed(helpIcon) &&
                action.isDisplayed(btn1);
    }

    @Step("Step: Delete entered number")
    public void deleteEnteredNumber(){
        action.clearTextField(txtfieldMobileNumber);
    }


    private void clickButton(char key){
        String loc = keypadPartialExpath + key + "']";
        action.click(action.findElementBy(loc));
    }



}





