package utils;

import java.io.IOException;
import java.util.Properties;

public class SuiteConfiguration {
    private static final String propsUrl = "/config.properties";
    private final Properties properties;

    public SuiteConfiguration() throws IOException {
        properties = new Properties();
        properties.load(SuiteConfiguration.class.getResourceAsStream(propsUrl));
    }

    public String getBrowserProperty() {
        return properties.getProperty("browser");
    }
}