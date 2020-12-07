package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    public Properties properties;

    public ConfigReader() {

        File src = new File("src/test/resources/android.properties");

        try {
            FileInputStream fileInput = new FileInputStream(src);
            properties = new Properties();
            properties.load(fileInput);
        } catch (Exception e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
