package pageobjects;

import io.appium.java_client.MobileDriver;

import utilities.ActionUtilities;
import utilities.ConfigUtilities;
import utilities.ExcelUtilities;
import utilities.LoggerUtilities;

public class BasePage extends LoggerUtilities {

    public static MobileDriver driver;
    public static ActionUtilities action;
    public static ConfigUtilities config;
    public static ExcelUtilities excel;
    public static LoggerUtilities log;

    public BasePage(MobileDriver driver, ActionUtilities action, LoggerUtilities log, ConfigUtilities config, ExcelUtilities excel) {
        BasePage.driver = driver;
        BasePage.action = action;
        BasePage.config = config;
        BasePage.excel = excel;
        BasePage.log = log;
    }

    public BasePage() {
        super();
    }
}
