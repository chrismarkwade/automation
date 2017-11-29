package pages;

import java.util.List;
import static utils.DriverFactory.getDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Chris Wade
 */

public class DepartmentPage extends BasePage {
    
    private static final Logger log = LogManager.getLogger();
    private String expectedUrlPart = "/b";
    
    @FindBy(css = "div h1")
    private WebElement departmentName;
    
    @FindBy(css = "div[class*='pageBanner'] a, div[class*='bxw-pageheader'] a")
    private List<WebElement> departmentHeaderLinks;
    
    public DepartmentPage(WebDriver driver) {
        super(driver);            
        
        if (!pageUrlContains(expectedUrlPart)) {
            log.error(getPageLoadError());
            log.error(getCurrentPageTitle());
            throw new IllegalStateException(getPageLoadError());
        }
    }
    
    public PageHeader header() { 
        return pageFactoryObject(getDriver(), PageHeader.class);
    }
    
    public PageFooter footer() { 
        return pageFactoryObject(getDriver(), PageFooter.class);
    }
    
    public PageLeftNav leftNav() {
        return pageFactoryObject(getDriver(), PageLeftNav.class);
    }
    
    public String getDepartmentName() {
        return getElementInnerText(departmentName);
    }
    
    public List<String> getDepartmentHeaderLinks() {
        return getElementsInnerText(departmentHeaderLinks);   
    }
    
}
