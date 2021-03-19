package testcases;

import java.io.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import pageobjects.*;

import utilities.ActionUtilities;
import utilities.ConfigUtilities;
import utilities.ExcelUtilities;
import utilities.LoggerUtilities;

public class BaseTestcase {

    public WebDriver driver;

    //    Pages
    public BasePage basePage;
    public LoginPage loginPage;

    //    Utilities
    ActionUtilities action;
    ConfigUtilities config = new ConfigUtilities();
    LoggerUtilities log = new LoggerUtilities();
    ExcelUtilities excel = new ExcelUtilities(log, config);

    //    Pages Initialization
    public void initializePages() {
//    Initialize Utilities
        log = new LoggerUtilities();
        excel = new ExcelUtilities(log, config);
        action = new ActionUtilities(driver, log, config, excel);

//        Initialize Pages
        basePage = new BasePage(driver, action, log, config, excel);
        loginPage = new LoginPage(driver);

    }

    public void initializeBrowserDriver() {
        String browser = config.getBrowser();
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            default:
                log.error("Invalid browser: " + browser);
                log.error("Initializing default browser: chrome");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
        driver.get((ConfigUtilities.URL));
    }


    @BeforeMethod
    public void beforeTest() {
        initializeBrowserDriver();
        initializePages();
    }

    @AfterMethod
    public void afterTest() throws InterruptedException {
        ActionUtilities.saveTextLog(LoggerUtilities.testlog);
        driver.quit();
        Thread.sleep(1000);
    }

    @BeforeSuite
    public void beforeSuite() throws IOException {
        excel.readTestdata();
        excel.readLocators();
    }
}


