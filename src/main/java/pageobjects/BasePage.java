package pageobjects;

import org.openqa.selenium.WebDriver;
import utilities.ActionUtilities;
import utilities.ConfigUtilities;
import utilities.ExcelUtilities;
import utilities.LoggerUtilities;

public class BasePage {

    public static WebDriver driver;
    public static ActionUtilities action;
    public static ConfigUtilities config;
    public static ExcelUtilities excel;
    public static LoggerUtilities log;

    public BasePage(WebDriver driver, ActionUtilities action, LoggerUtilities log, ConfigUtilities config, ExcelUtilities excel) {
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
