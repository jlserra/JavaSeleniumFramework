package testcases;

import java.io.*;
import java.lang.reflect.Method;
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
import utilities.LoggerUtilities;

public class BaseTestcase {

//    Appium and Driver
    public URL url;
    public DesiredCapabilities capabilities;
    public MobileDriver<MobileElement> driver;

//    Pages
    public BasePage basePage;
    public LoginPage loginPage;
    public GetStartedPage getStartedPage;
    public SecureAppPage secureAppPage;
    public CustomerProfilePage customerProfilePage;

//    Utilities
    ActionUtilities action;
    ConfigUtilities config = new ConfigUtilities();
    LoggerUtilities log = new LoggerUtilities();

//    Pages Initialization
    public void initializePages() {
//    Initialize Utilities
        action = new ActionUtilities(driver);

//        Initialize Pages
        basePage = new BasePage(driver, action, log, config);
        loginPage = new LoginPage(driver);
        getStartedPage = new GetStartedPage(driver);
        secureAppPage = new SecureAppPage(driver);
        customerProfilePage = new CustomerProfilePage(driver);
    }

    @BeforeSuite
    public void setupAppium() throws IOException {

        log.startSuite();

        if (config.getPlatform().equalsIgnoreCase("android")) {
            initializeAndroidCapabilities();
        } else {
            initializeIOSCapabilities();
        }

        initializePages();
    }

    @AfterSuite
    public void closeApp() {
        driver.closeApp();
        driver.quit();
        log.endSuite();
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
        File filePath = new File(System.getProperty("user.dir"));
        File appDir = new File(filePath, "src/main/resources");
        File app = new File(appDir, "release.apk");
        capabilities.setCapability("app", app.getAbsolutePath());

//        B.) APK Pre-Installed
//        capabilities.setCapability("appPackage", "ph.com.globe.mybusiness");
//        capabilities.setCapability("appActivity", "ph.com.globe.mybusiness.SplashScreenActivity");

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
        capabilities.setCapability("noReset", true);

//        IOS Application Configuration
        capabilities.setCapability("bundleId", "com.example.apple-samplecode.UICatalog");

//        Setup for Real Devices
//        capabilities.setCapability("xcodeOrgId", "7MD4DRE548");
//        capabilities.setCapability("xcodeSigningId", "iPhone Developer");

        //Send desired capabilities to appium
        driver = new IOSDriver<MobileElement>(url, capabilities);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @BeforeTest
    public void beforeTest() {
        log.startTestCase(getClass().getName());
    }

    @AfterTest
    public void afterTest() {
        log.endTestCase(getClass().getName());
    }
}


