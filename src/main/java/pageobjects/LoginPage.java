package pageobjects;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

    MobileDriver driver;

    public LoginPage(MobileDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/spbtn_submit")
    @iOSXCUITFindBy(id = "")
    public MobileElement btnNext;

    @AndroidFindBy(id = "ph.com.globe.mybusiness:id/spbtn_pin_key_1")
    @iOSXCUITFindBy(id = "")
    public MobileElement btn1;

    public void clickBtnNext(){
        btnNext.click();
    }

    public void clickBtn1(){
        btn1.click();
    }









}


