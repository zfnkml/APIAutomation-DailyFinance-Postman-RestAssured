package net.roadtocareer.dailyfinance.setup;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/*
 ** 2025, January 14, Tuesday, 11:38 AM
 */
public class Setup {
    static String fileName = "./src/test/resources/config.properties";
    public Properties properties;

    @BeforeMethod
    @BeforeEach
    public void loadProperties() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(fileName));
    }

    public static void setProperties(String key, String value) throws ConfigurationException {
        PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration(fileName);
        propertiesConfiguration.setProperty(key, value);
        propertiesConfiguration.save();
    }
}
