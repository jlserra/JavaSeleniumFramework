package utilities;

import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pageobjects.BasePage;

import java.io.*;

public class ConfigUtilities extends BasePage{

    public static String URL = "https://demo.openmrs.org/openmrs/login.htm";
    private static File filePath = new File(System.getProperty("user.dir"));
    public static File resourceDirectory = new File(filePath, "src/main/resources");
    private static String browser;
    private static String testcase;
    private static String suiteName;

    public static JSONObject capabilities;

    public ConfigUtilities() {

        try {
            getProperty();
            getCapabilities();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getProperty(){
        if (System.getProperty("browser") != null) {
            setBrowser(System.getProperty("browser").toLowerCase());
        } else {
            //set chrome as default
            setBrowser("chrome");
        }
    }

    public void getCapabilities() throws IOException, ParseException {

        File path = new File(resourceDirectory, "capabilities.json");

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(path.getAbsolutePath());

        Object obj = jsonParser.parse(reader);

        JSONObject list = (JSONObject) obj;
        capabilities = (JSONObject) list.get(getBrowser());

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
