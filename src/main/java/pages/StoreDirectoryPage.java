package pages;

import java.util.List;
import static utils.DriverFactory.getDriver;
import static utils.ExecuteJavaScript.scrollIntoView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Chris Wade
 */

public class StoreDirectoryPage extends BasePage {
    
    private static final Logger log = LogManager.getLogger();
    private String expectedPageTitle = "Amazon.ca - Earth's Biggest Selection";
    private String expectedUrlPart = "/site-directory/";
    
    @FindBy(xpath = "//td[1]/div[5]/ul/li/a")
    private List<WebElement> moviesAndTvShowsLinks;
    
    @FindBy(xpath = "//td[2]/div[1]/ul/li/a")
    private List<WebElement> electronicsLinks;
    
    public StoreDirectoryPage(WebDriver driver) {
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
    
    private boolean allCategoryLinksOk(List<WebElement> linkList) {
        
        for (int x = 0; x < linkList.size(); x++) {
            WebElement link = getElementAtIndex(linkList, x);
            scrollIntoView(link);
            click(link);
            
            if (!pageUrlContains("/b")) {
                log.error(getPageUrl() + " - " + "Not OK");
                return false;
            }
            log.info(getPageUrl() + " - " + "OK");
            navigateBack();
        }
        return true;
    }
    
    public boolean allMoviesAndTvShowsLinksOk() {
        return allCategoryLinksOk(moviesAndTvShowsLinks);
    }
    
    public boolean allElectronicsLinksOk() {
        return allCategoryLinksOk(electronicsLinks);
    }
    
    public String getExpectedPageTitle() {
        return expectedPageTitle;
    }
    
}
