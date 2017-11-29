package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

/**
 * @author Chris Wade
 */

public class DriverFactory {
    
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    
    private DriverFactory() {
        // Suppress default constructor for noninstantiability
    }
    
    public static WebDriver createDriver(String browser) {
        
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
                return new ChromeDriver();
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "./src/test/resources/drivers/geckodriver.exe");
                return new FirefoxDriver();
            case "ie":
                System.setProperty("webdriver.ie.driver", "./src/test/resources/drivers/IEDriverServer.exe");
                InternetExplorerOptions options = new InternetExplorerOptions()
                        .requireWindowFocus()
                        .enablePersistentHovering()
                        .destructivelyEnsureCleanSession();
                return new InternetExplorerDriver(options);
            default:
                throw new IllegalArgumentException("Unknown broswer");
        }
    }
    
    public static void setDriver(WebDriver driver) {
        webDriver.set(driver);
    }
    
    public static WebDriver getDriver() {
        return webDriver.get();
    }
    
    public static void quitDriver() {
        if (webDriver.get() != null) {
            webDriver.get().quit();
            webDriver.remove();
        }
    }
    
}
