package pageobjects;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;

import org.openqa.selenium.support.PageFactory;

import java.io.IOException;


public class CustomerProfilePage extends BasePage {

    public CustomerProfilePage(MobileDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/spet_customize_profile_nickname")
    @iOSXCUITFindBy(id = "a")
    private MobileElement txtfieldNickname;

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/pbtn_customize_profile_submit")
    @iOSXCUITFindBy(id = "a")
    private MobileElement btnNext;

    @AndroidFindBy(xpath = "//*[@text='Customize your profile']")
    @iOSXCUITFindBy(xpath = "a")
    private MobileElement txtCustomerProfile;

    public void clickBtnNext() {
        log.info("Step: Click Button Next");
        action.click(btnNext);
    }

    public Boolean verifyIfBtnNextIsEnabled(){
        log.info("Step: Verify if Button Next is Enabled");
        return action.isEnabled(btnNext);
    }

    @Step("Step: Enter Nickname")
    public void enterNickname(String text) throws IOException {
        log.info("Step: Enter Prepaid Number");
        action.sendKeys(txtfieldNickname, text);
        action.takeSnapShot("Enter nickname");
    }

    public Boolean verifyIfCustomerProfilePage(){
        log.info("Step: Verify if user is in Customer Profile Page");
        return action.isDisplayed(txtCustomerProfile);
    }

//    Select Modem Page

    @AndroidFindBy(xpath = "//*[@resource-id='ph.com.globe.mybusiness:id/rv_modems']//descendant::android.widget.LinearLayout[1]")
    @iOSXCUITFindBy(id = "a")
    private MobileElement modem1;

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/tv_title")
    @iOSXCUITFindBy(id = "a")
    private MobileElement txtChooseModem;

    @Step("Step: Select Modem")
    public void clickModem1() throws IOException {
        log.info("Step: Select Modem");
        action.takeSnapShot("Select modem");
        action.click(modem1);
    }

    public Boolean verifyIfChooseModemPage(){
        log.info("Step: Verify is user is in choose modem page");
        return action.isDisplayed(txtChooseModem);
    }

//    Connect phone to my business

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/tv_autoverif_msg")
    @iOSXCUITFindBy(id = "a")
    private MobileElement txtConnectPhone;

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/btn_already_connected")
    @iOSXCUITFindBy(id = "a")
    private MobileElement btnAlreadyConnected;

    public Boolean verifyIfConnectPhonetoMyBusinessPage(){
        log.info("Step: Verify if user is in connect phone to my business prepaid page");
        return action.isDisplayed(txtConnectPhone);
    }

    public void clickBtnImAlreadyConnected(){
        log.info("Step: Click button Im Already Verified");
        action.click(btnAlreadyConnected);
    }

//      Not Connected to myBusiness Page

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/tv_autoverif_msg")
    @iOSXCUITFindBy(id = "a")
    private MobileElement txtNotConnected;

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/btn_manual_verif")
    @iOSXCUITFindBy(id = "a")
    private MobileElement btnManualVerifyAccount;

    @Step("Step: Verify if user is in phone not connected page")
    public Boolean verifyIfNotConnectedPage() throws InterruptedException, IOException {
        log.info("Step: Verify if user is in phone not connected page");
        new CustomerProfilePage(driver);
        Thread.sleep(3000);
        action.waitForElementToBeVisible(txtNotConnected);
        action.takeSnapShot("Phone not connected");
        return action.isDisplayed(txtNotConnected);
    }

    public void clickBtnManualVerifyAccount(){
        log.info("Step: Click button Im Already Verified");
        action.click(btnManualVerifyAccount);
    }

//  OTP Verification Page

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/sptxt_skip")
    @iOSXCUITFindBy(id = "a")
    private MobileElement btnSkip;

    public void clickBtnSkip() throws IOException {
        log.info("Step: Click Btn Skip");
        action.takeSnapShot("OTP Screen");
        action.click(btnSkip);
    }

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/btn_positive")
    @iOSXCUITFindBy(id = "a")
    private MobileElement btnProceed;

    @Step("Step: Click Button Proceed")
    public void clickBtnProceed() throws IOException {
        log.info("Step: Click Button Proceed");
        action.click(btnProceed);
    }

}
