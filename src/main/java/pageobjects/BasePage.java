package pageobjects;

import io.appium.java_client.MobileDriver;

import utilities.ActionUtilities;
import utilities.ConfigUtilities;
import utilities.LoggerUtilities;

public class BasePage {

    public static MobileDriver driver;
    public static LoggerUtilities log;
    public static ActionUtilities action;
    public static ConfigUtilities config;
    public static String testName = "";

    public BasePage(){
        super();
    }

    public BasePage(MobileDriver driver, ActionUtilities action, LoggerUtilities log, ConfigUtilities config) {
        BasePage.driver = driver;
        BasePage.log = log;
        BasePage.action = action;
        BasePage.config = config;
    }

    public static String getTestName() {
        return testName;
    }

    public static void setTestName(String testName) {
        BasePage.testName = testName;
    }




}
