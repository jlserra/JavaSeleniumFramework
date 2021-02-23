package utilities;

import pageobjects.BasePage;

public class ConfigUtilities extends BasePage {

    public String platform;
    public String testcase;

    public ConfigUtilities() {

        try {
            if (System.getProperty("platform") != null) {
                setPlatform(System.getProperty("platform"));
            } else {
                //set android as default
                setPlatform("android");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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
        private int value;

        Timers(int value) {
            this.value = value;
        }
    }



}
