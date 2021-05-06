package utilities;

import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import pageobjects.BasePage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ConfigUtilities extends BasePage {

    private static String URL;
    private static URL excelFileURL;
    private static final File filePath = new File(System.getProperty("user.dir"));
    public static File resourceDirectory = new File(filePath, "src/main/resources");
    private static String browser;
    private static String testcase;
    private static String suiteName;

    public ConfigUtilities() {

        try {
            getProperty();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static java.net.URL getExcelFileURL() {
        return excelFileURL;
    }

    public static void setExcelFileURL(String excelFileURL) throws MalformedURLException {
        ConfigUtilities.excelFileURL = new URL(excelFileURL);
    }

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        ConfigUtilities.URL = URL;
    }

    public void getProperty() throws MalformedURLException {


        setURL(System.getProperty("url"));
        if (System.getProperty("browser") != null) {
            setBrowser(System.getProperty("browser").toLowerCase());
        } else {
            //set chrome as default
            setBrowser("chrome");
        }

        setURL(System.getProperty("url"));
        setExcelFileURL(System.getProperty("excelFileUrl"));

    }

    public String getTestcase() {
        return testcase;
    }

    public void setTestcase(String testcase) {
        ConfigUtilities.testcase = testcase;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        ConfigUtilities.suiteName = suiteName;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        ConfigUtilities.browser = browser;
    }

    public enum Timers {
        superFast(500),
        fast(1000),
        realQuick(2000),
        quick(4000),
        normal(6000),
        appStandard(12000),
        slow(24000),
        verySlow(48000);

        private final int value;

        public int getValue() {
            return value;
        }

        Timers(int value) {
            this.value = value;
        }
    }

    public void setTestConfiguration(String suiteName, String testcase){

        setSuiteName(suiteName);
        setTestcase(testcase);
        LoggerUtilities.clearLog();

        System.setProperty("suite", suiteName);
        System.setProperty("filename", testcase);
        File path = new File(resourceDirectory, "log4j.properties");
        PropertyConfigurator.configure(path.getAbsolutePath());

    }


}
