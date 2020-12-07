package pageobjects;

import io.appium.java_client.MobileDriver;

import org.apache.log4j.Logger;

public class BasePage {

    protected MobileDriver driver;

    static Logger log = Logger.getLogger("test");

    public BasePage() {
    }

    public BasePage(MobileDriver driver) {
        this.driver = driver;
    }

}
