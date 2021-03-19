package testcases;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import pageobjects.*;

import utilities.ActionUtilities;
import utilities.ConfigUtilities;
import utilities.ExcelUtilities;
import utilities.LoggerUtilities;

public class BaseTestcase {

//    Appium and Driver
    public URL url;
    public DesiredCapabilities capabilities;
    public MobileDriver<MobileElement> driver;

//    Pages
    public BasePage basePage;
    public WelcomePage welcomePage;
    public GetStartedPage getStartedPage;
    public SecureAppPage secureAppPage;
    public CustomerProfilePage customerProfilePage;
    public ChooseModemPage chooseModemPage;

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
        welcomePage = new WelcomePage(driver);
        getStartedPage = new GetStartedPage(driver);
        secureAppPage = new SecureAppPage(driver);
        customerProfilePage = new CustomerProfilePage(driver);
        chooseModemPage = new ChooseModemPage(driver);

    }

    public void setupAppium() throws IOException {
        if (config.getPlatform().equalsIgnoreCase("android")) {
            initializeAndroidCapabilities();
        } else {
            initializeIOSCapabilities();
        }
    }

    //    Android Capabilities
    public void initializeAndroidCapabilities() throws MalformedURLException {

//        Appium Server Configuration
        final String URL_STRING = "http://0.0.0.0:4723/wd/hub";
        url = new URL(URL_STRING);

//        Android Capability Configuration
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("noReset", false);

//        Application Capability Configuration
//        A.) APK Fresh Installation
        File app = new File(config.resourceDirectory, "release.apk");
        capabilities.setCapability("app", app.getAbsolutePath());

//        B.) APK Pre-Installed
//        capabilities.setCapability("appPackage", "ph.com.globe.mybusiness");
//        capabilities.setCapability("appActivity", "ph.com.globe.mybusiness.SplashScreenActivity");

//        Get capabilities from JSON
//        for(Object key : config.capabilities.keySet()) {
//            Object value = config.capabilities.get(key);
//            if(key.equals("app")){
//                File app = new File(config.resourceDirectory, (String) value);
//                capabilities.setCapability((String) key, app.getAbsolutePath());
//            } else{
//                capabilities.setCapability((String) key, value);
//            }
//        }

//        Send desired capabilities to appium
        driver = new AndroidDriver<MobileElement>(url, capabilities);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    //    IOS Capabilities
    public void initializeIOSCapabilities() throws MalformedURLException {

//        Appium Server
        final String URL_STRING = "http://0.0.0.0:4723/wd/hub";
        url = new URL(URL_STRING);

        String udid_iphone6 = "EAFB7812-E678-41D7-B8F0-1C1DB16539CA";

//        IOS Capability Configurations
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "IOS");
        capabilities.setCapability("deviceName", "iphone 6s");
        capabilities.setCapability("platformVersion", "14.0");
        capabilities.setCapability("udid", udid_iphone6);
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("noReset", false);

//        IOS Application Configuration
        capabilities.setCapability("bundleId", "com.example.apple-samplecode.UICatalog");

//        Setup for Real Devices
//        capabilities.setCapability("xcodeOrgId", "7MD4DRE548");
//        capabilities.setCapability("xcodeSigningId", "iPhone Developer");

        //Send desired capabilities to appium
        driver = new IOSDriver<MobileElement>(url, capabilities);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void beforeTest() throws IOException {
        setupAppium();
        initializePages();
    }

    @AfterMethod
    public void afterTest() throws InterruptedException {
        action.saveTextLog(LoggerUtilities.testlog);
        driver.quit();
        Thread.sleep(1000);
    }

    @BeforeSuite
    public void beforeSuite() throws IOException {
        excel.readTestdata();
        excel.readLocators();
    }
}


