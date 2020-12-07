package testcases;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.ios.IOSDriver;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.annotations.Test;
import pageobjects.BasePage;
import pageobjects.UiCatalogPage;

import utilities.ActionUtilities;
import utilities.ConfigReader;

public class BaseTestcase {

    static Logger log = Logger.getLogger("test");
    public URL url;
    public DesiredCapabilities capabilities;
    public MobileDriver<MobileElement> driver;
    public BasePage basePage;
    public ConfigReader configReader;
    public UiCatalogPage uiCatalogPage;
    public ActionUtilities actionUtilities;

    //1
    @BeforeSuite
    public void setupAppium() throws IOException {

//        initializeIOSCapabilities();
        initializeAndroidCapabilities();
        initializePages();

    }


    public void initializeAndroidCapabilities() throws MalformedURLException {

        final String URL_STRING = "http://0.0.0.0:4723/wd/hub";
        url = new URL(URL_STRING);

//        File filePath = new File(System.getProperty("user.dir"));
//        File appDir = new File(filePath, "src/test/resources");
//        File app = new File(appDir, "selendroid.apk");
//        Installation for fresh APK
//        capabilities.setCapability("app", app.getAbsolutePath());

        //3
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "io.selendroid.testapp");
        capabilities.setCapability("appActivity", "io.selendroid.testapp.HomeScreenActivity");
        capabilities.setCapability("noReset", true);

        // GlobeApp
//        capabilities.setCapability("appPackage", "ph.com.globe.mybusiness");
//        capabilities.setCapability("appActivity", "ph.com.globe.mybusiness.SplashScreenActivity");


        //4 Send desired capabilities to appium
        driver = new AndroidDriver<MobileElement>(url, capabilities);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public void initializeIOSCapabilities() throws MalformedURLException {

        final String URL_STRING = "http://0.0.0.0:4723/wd/hub";
        url = new URL(URL_STRING);

        String udid_iphone6 = "EAFB7812-E678-41D7-B8F0-1C1DB16539CA";

        //3
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "IOS");
        capabilities.setCapability("deviceName", "iphone 6s");
        capabilities.setCapability("platformVersion", "14.0");
        capabilities.setCapability("udid", udid_iphone6);
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("bundleId", "com.example.apple-samplecode.UICatalog");
        capabilities.setCapability("noReset", true);

        //Setup for Real Devices
//        capabilities.setCapability("xcodeOrgId", "7MD4DRE548");
//        capabilities.setCapability("xcodeSigningId", "iPhone Developer");

        //4 Send desired capabilities to appium
        driver = new IOSDriver<MobileElement>(url, capabilities);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    // Pages Initialization
    public void initializePages() {
        configReader = new ConfigReader();
        actionUtilities = new ActionUtilities(driver);
        basePage = new BasePage(driver);
        uiCatalogPage = new UiCatalogPage(driver);
    }

    //5
    @AfterSuite
    public void uninstallApp() {
        driver.quit();
    }

}


