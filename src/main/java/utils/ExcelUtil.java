package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFCell;

/**
 * @author Chris Wade
 */

public class ExcelUtil {
    
    private static final Logger log = LogManager.getLogger();
    private static String homePageCssFilePath = "./src/test/resources/tables/HomePage_ElementCssValues.xls";
    private static String productPageCssFilePath = "./src/test/resources/tables/ProductPage_ElementCssValues.xls";
    private static HSSFWorkbook workbook;
    private static HSSFSheet sheet;
    private static HSSFCell cell;
    
    private ExcelUtil() {
        // Suppress default constructor for noninstantiability
    }
    
    private static void loadExcelFile(String filePath) {
        
        File file = new File(filePath);
        
        try (FileInputStream inputStream = new FileInputStream(file)) {
            workbook = new HSSFWorkbook(inputStream);
        }
        catch (IOException ex) {
            log.error(ex);
        }
    }
    
    private static String getCellValue(String sheetName, int row, int column) {
        sheet = workbook.getSheet(sheetName);
        cell = sheet.getRow(row - 1).getCell(column - 1);
        return cell.getStringCellValue();
    }
    
    public static String getHomePageElementCssValueAt(int row, int column) {
        loadExcelFile(homePageCssFilePath);
        return getCellValue("Sheet1", row, column);
    }
    
    public static String getProductPageElementCssValueAt(int row, int column) {
        loadExcelFile(productPageCssFilePath);
        return getCellValue("Sheet1", row, column);
    }
    
}
