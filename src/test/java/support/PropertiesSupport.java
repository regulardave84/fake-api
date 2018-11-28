package support;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesSupport {
    private static final String CONFIG_FILES_PATH = "src/test/resources/config/";

    public String getValueFromFile(String propertyFileName, String key) {
        String value = null;

        try {
            File file = new File(CONFIG_FILES_PATH + propertyFileName);
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            value = properties.getProperty(key);
            fileInput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

}