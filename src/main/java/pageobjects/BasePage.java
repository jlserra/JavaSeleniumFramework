package pageobjects;

import io.appium.java_client.MobileDriver;
import utilities.ActionUtilities;
import utilities.LoggerUtilities;

public class BasePage {

    static MobileDriver driver;
    static LoggerUtilities log;
    static ActionUtilities action;

    public BasePage(){
        super();
    }

    public BasePage(MobileDriver driver, ActionUtilities action, LoggerUtilities log) {
        this.driver = driver;
        this.log = log;
        this.action = action;
    }

}
