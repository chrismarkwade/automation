package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Chris Wade
 */

public class PropertyUtil {
    
    private static final Logger log = LogManager.getLogger();
    private static String urlFilePath = "./src/test/resources/properties/url.properties";
    private static String accountFilePath = "./src/test/resources/properties/account.properties";
    private static Properties properties;
    
    private PropertyUtil() {
        // Suppress default constructor for noninstantiability
    }
    
    private static void loadProperties(String filePath) {
        
        File file = new File(filePath);
        properties = new Properties();
        
        try (FileReader reader = new FileReader(file)) {
            properties.load(reader);
        }
        catch (IOException ex) {
            log.error(ex);
        }
    }
    
    public static String getProperty(String filePath, String key) {
        loadProperties(filePath);
        return properties.getProperty(key);
    }
    
    public static String getUrlProperty(String key) {
        return getProperty(urlFilePath, key);
    }
    
    public static String getAccountProperty(String key) {
        return getProperty(accountFilePath, key);
    }
    
}
