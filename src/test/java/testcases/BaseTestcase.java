package testcases;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import pageobjects.BasePage;
import pageobjects.GetStartedPage;
import pageobjects.LoginPage;
import pageobjects.SamplePage;

import utilities.ActionUtilities;
import utilities.ConfigReader;
import utilities.LoggerUtilities;

public class BaseTestcase {

    public URL url;
    public DesiredCapabilities capabilities;
    public MobileDriver<MobileElement> driver;
    public BasePage basePage;
    public ConfigReader configReader;
    public SamplePage samplePage;
    public ActionUtilities action;
    public LoggerUtilities log;
    public LoginPage loginPage;
    public GetStartedPage getStartedPage;

    // Pages Initialization
    public void initializePages() {
//        Initialize Utilities
//        configReader = new ConfigReader();
        action = new ActionUtilities(driver);
        log = new LoggerUtilities();

//        Initialize Pages
        basePage = new BasePage(driver, action, log);
        samplePage = new SamplePage(driver);
        loginPage = new LoginPage(driver);
        getStartedPage = new GetStartedPage(driver);
    }

    @BeforeSuite
    public void setupAppium() throws IOException {
        log.debug("XXXXXXXXXXXXXXXXXXXXXXX             -  SUITE START -             XXXXXXXXXXXXXXXXXXXXXX");
//        initializeIOSCapabilities();
        initializeAndroidCapabilities();
        log.debug("XXXXXXXXXXXXXXXXXXXXXXX             -  OPENING APPLICATION -             XXXXXXXXXXXXXXXXXXXXXX");
        initializePages();
    }

    // Android Capabilities
    public void initializeAndroidCapabilities() throws MalformedURLException {

//        Appium Server
        final String URL_STRING = "http://0.0.0.0:4723/wd/hub";
        url = new URL(URL_STRING);

//        File filePath = new File(System.getProperty("user.dir"));
//        File appDir = new File(filePath, "src/test/resources");
//        File app = new File(appDir, "selendroid.apk");
//        Installation for fresh APK
//        capabilities.setCapability("app", app.getAbsolutePath());

//        Android Capability Configurations
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "ph.com.globe.mybusiness");
        capabilities.setCapability("appActivity", "ph.com.globe.mybusiness.SplashScreenActivity");
        capabilities.setCapability("noReset", false);
//        Selendroid test app
//        capabilities.setCapability("appPackage", "io.selendroid.testapp");
//        capabilities.setCapability("appActivity", "io.selendroid.testapp.HomeScreenActivity");

        //Send desired capabilities to appium
        driver = new AndroidDriver<MobileElement>(url, capabilities);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

//    IOS Capabilities
    public void initializeIOSCapabilities() throws MalformedURLException {

//        Appium Server
        final String URL_STRING = "http://0.0.0.0:4723/wd/hub";
        url = new URL(URL_STRING);

        String udid_iphone6 = "EAFB7812-E678-41D7-B8F0-1C1DB16539CA";
        String udid_iphoneXr = "";

//        IOS Capability Configurations
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "IOS");
        capabilities.setCapability("deviceName", "iphone 6s");
        capabilities.setCapability("platformVersion", "14.0");
        capabilities.setCapability("udid", udid_iphone6);
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("bundleId", "com.example.apple-samplecode.UICatalog");
        capabilities.setCapability("noReset", true);

//        Setup for Real Devices
//        capabilities.setCapability("xcodeOrgId", "7MD4DRE548");
//        capabilities.setCapability("xcodeSigningId", "iPhone Developer");

        //Send desired capabilities to appium
        driver = new IOSDriver<MobileElement>(url, capabilities);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void closeApp() {
        driver.closeApp();
        log.debug("XXXXXXXXXXXXXXXXXXXXXXX             -  SUITE END -             XXXXXXXXXXXXXXXXXXXXXX");
    }

}


