package utilities;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pageobjects.BasePage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConfigUtilities extends BasePage {

    File filePath = new File(System.getProperty("user.dir"));
    public File resourceDirectory = new File(filePath, "src/main/resources");
    public String platform;
    public String testcase;
    public JSONObject capabilities;

    public ConfigUtilities() {

        try {
            if (System.getProperty("platform") != null) {
                setPlatform(System.getProperty("platform"));
            } else {
                //set android as default
                setPlatform("android");
            }

            getCapabilities();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getCapabilities() throws IOException, ParseException {

        File filePath = new File(System.getProperty("user.dir"));
        File fileDir = new File(filePath, "src/main/resources");
        File path = new File(fileDir, "capabilities.json");

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(path.getAbsolutePath());

        Object obj = jsonParser.parse(reader);

        JSONObject list = (JSONObject) obj;
        capabilities = (JSONObject) list.get(getPlatform());

    }

    public String getTestcase() {
        return testcase;
    }

    public void setTestcase(String testcase) {
        this.testcase = testcase;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
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

        public int getValue() {
            return value;
        }
        private final int value;

        Timers(int value) {
            this.value = value;
        }
    }



}
