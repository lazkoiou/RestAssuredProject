package helpers;


import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Helper class to handle the properties
 */
@Slf4j
public class PropertiesManager {

    public static Properties properties = new Properties();


     /** Loads property files
     * @param filePath : the path where the property file is located
     */
    public static void loadPropertyFile(String filePath) {
        log.info("Filepath is: " + filePath);
        InputStream stream;
        try {
            stream = new FileInputStream(filePath);
            properties.load(new InputStreamReader(stream, "iso-8859-7")); // used to read Greek
        }
        catch (IOException e) {
            e.printStackTrace();
            log.error("Error loading properties files!");
            throw new RuntimeException();
        }
    }

    /**
     * Load the appropriate properties files
     */
    public static void loadProperties() {
        log.info("Loading property files...");
        loadPropertyFile("src/main/resources/apiUrls.properties");
        log.info("Property files were successfully loaded.");
    }

    /**
     * Returns the contents of the property we specify
     * @param propertyName : the name of the property we want the contents of
     * @return : the contents of the property
     */
    public static String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }


}
