package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties configProperties;
    private static Properties testDataProperties;

    /**
     * Load config.properties file
     */
    public static Properties getConfigProperties() {
        if (configProperties == null) {
            configProperties = new Properties();
            try {
                FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
                configProperties.load(fis);
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException("Could not load config.properties file: " + e.getMessage());
            }
        }
        return configProperties;
    }

    /**
     * Load testdata.properties file
     */
    public static Properties getTestDataProperties() {
        if (testDataProperties == null) {
            testDataProperties = new Properties();
            try {
                FileInputStream fis = new FileInputStream("src/test/resources/testdata/testdata.properties");
                testDataProperties.load(fis);
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException("Could not load testdata.properties file: " + e.getMessage());
            }
        }
        return testDataProperties;
    }

    /**
     * Get config property value by key
     */
    public static String getConfigProperty(String key) {
        return getConfigProperties().getProperty(key);
    }

    /**
     * Get test data property value by key
     */
    public static String getTestData(String key) {
        return getTestDataProperties().getProperty(key);
    }
}
