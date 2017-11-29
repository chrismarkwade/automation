package utils;

import java.io.File;
import static utils.DriverFactory.getDriver;
import static utils.FileUtil.copyFile;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * @author Chris Wade
 */

public class TakeScreenshot {
    
    private static String destPath = "target/screenshots/";
    private static String fileFormat = ".png";
    
    private TakeScreenshot() {
        // Suppress default constructor for noninstantiability
    }
    
    public static void takeScreenshot(String name) {
        
        TakesScreenshot screenshot = (TakesScreenshot) getDriver();
        
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(destPath + name + fileFormat);
        
        copyFile(srcFile, destFile);
    }
    
}
