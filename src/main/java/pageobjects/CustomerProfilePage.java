package pageobjects;

import io.appium.java_client.MobileDriver;

import io.qameta.allure.Step;
import utilities.ConfigUtilities;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class CustomerProfilePage extends BasePage {

    MobileDriver driver;

    public CustomerProfilePage(MobileDriver driver) {
        this.driver = driver;
    }

    @Step("Step: Click Button Next")
    public void clickBtnNext(){
        log.info("Step: Click Button Next");
        action.click("btnNext");
    }

    @Step("Step: Click Button Skip")
    public void clickBtnSkip() {
        log.info("Step: Click Button Skip");
        action.click("btnSkip");
    }

    @Step("Step: Click Button Skip")
    public Boolean verifyIfBtnNextIsEnabled(){
        log.info("Step: Verify if Button Next is Enabled");
        return action.isEnabled("btnNext");
    }

    @Step("Step: Enter Nickname")
    public void enterNickname(String text) throws IOException {
        log.info("Step: Enter Prepaid Number");
        action.sendKeys("txtfieldNickname", text);
        action.takeSnapShot("Enter nickname");
    }

    @Step("Step: Verify if user is in Customer Profile Page")
    public Boolean verifyIfCustomerProfilePage(){
        log.info("Step: Verify if user is in Customer Profile Page");
        assertTrue(action.waitForElementToBeVisible("txtCustomerProfile", ConfigUtilities.Timers.slow));
        return action.isDisplayed("txtCustomerProfile");
    }


//
////    Select Modem Page
//
//    @AndroidFindBy(xpath = "//*[@resource-id='ph.com.globe.mybusiness:id/rv_modems']//descendant::android.widget.LinearLayout[1]")
//    @iOSXCUITFindBy(id = "a")
//    private MobileElement modem1;
//
//    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/tv_title")
//    @iOSXCUITFindBy(id = "a")
//    private MobileElement txtChooseModem;
//
//    @Step("Step: Select Modem")
//    public void clickModem1() throws IOException {
//        log.info("Step: Select Modem");
//        action.takeSnapShot("Select modem");
//        action.click(modem1);
//    }
//
//    public Boolean verifyIfChooseModemPage(){
//        log.info("Step: Verify is user is in choose modem page");
//        return action.isDisplayed(txtChooseModem);
//    }
//
////    Connect phone to my business
//
//    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/tv_autoverif_msg")
//    @iOSXCUITFindBy(id = "a")
//    private MobileElement txtConnectPhone;
//
//    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/btn_already_connected")
//    @iOSXCUITFindBy(id = "a")
//    private MobileElement btnAlreadyConnected;
//
//    public Boolean verifyIfConnectPhonetoMyBusinessPage(){
//        log.info("Step: Verify if user is in connect phone to my business prepaid page");
//        return action.isDisplayed(txtConnectPhone);
//    }
//
//    public void clickBtnImAlreadyConnected(){
//        log.info("Step: Click button Im Already Verified");
//        action.click(btnAlreadyConnected);
//    }
//
////      Not Connected to myBusiness Page
//
//    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/tv_autoverif_msg")
//    @iOSXCUITFindBy(id = "a")
//    private MobileElement txtNotConnected;
//
//    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/btn_manual_verif")
//    @iOSXCUITFindBy(id = "a")
//    private MobileElement btnManualVerifyAccount;
//
//    @Step("Step: Verify if user is in phone not connected page")
//    public Boolean verifyIfNotConnectedPage() throws InterruptedException, IOException {
//        log.info("Step: Verify if user is in phone not connected page");
//        new CustomerProfilePage(driver);
//        Thread.sleep(3000);
//        action.waitForElementToBeVisible(txtNotConnected);
//        action.takeSnapShot("Phone not connected");
//        return action.isDisplayed(txtNotConnected);
//    }
//
//    public void clickBtnManualVerifyAccount(){
//        log.info("Step: Click button Im Already Verified");
//        action.click(btnManualVerifyAccount);
//    }
//
////  OTP Verification Page
//
//    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/sptxt_skip")
//    @iOSXCUITFindBy(id = "a")
//    private MobileElement btnSkip;
//
//    public void clickBtnSkip() throws IOException {
//        log.info("Step: Click Btn Skip");
//        action.takeSnapShot("OTP Screen");
//        action.click(btnSkip);
//    }
//
//    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/btn_positive")
//    @iOSXCUITFindBy(id = "a")
//    private MobileElement btnProceed;
//
//    @Step("Step: Click Button Proceed")
//    public void clickBtnProceed() throws IOException {
//        log.info("Step: Click Button Proceed");
//        action.click(btnProceed);
//    }

}
