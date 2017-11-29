package utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Chris Wade
 */

public class FileUtil {
    
    private static final Logger log = LogManager.getLogger();
    private static String searchDepartmenListPath = "./src/test/resources/lists/PageHeader_SearchDepartmentList.txt";
    private static String shopCategoryListPath = "./src/test/resources/lists/PageHeader_ShopCategoryList.txt";
    private static String deliveryOptionsPath = "./src/test/resources/lists/PlaceOrderPage_DeliveryOptions.txt";
    private static List<String> list;
    
    private FileUtil() {
        // Suppress default constructor for noninstantiability
    }
    
    public static List<String> readFile(String filePath) {
        
        File file = new File(filePath);
        try {
            list = FileUtils.readLines(file, "UTF-8");
        }
        catch (IOException ex) {
            log.error(ex);
        }
        return list;
    }
    
    public static void copyFile(File srcFile, File destFile) {
        try {
            FileUtils.copyFile(srcFile, destFile);
        }
        catch (IOException ex) {
            log.error(ex);
        }
    }
    
    public static List<String> getHeaderSearchDepartmentList() {
        return readFile(searchDepartmenListPath);
    }
    
    public static List<String> getHeaderShopCategoryList() {
        return readFile(shopCategoryListPath);
    }
    
    public static List<String> getPlaceOrderDeliveryOptions() {
        return readFile(deliveryOptionsPath);
    }
    
}
