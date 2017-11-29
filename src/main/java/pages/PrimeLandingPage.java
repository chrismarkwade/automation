package pages;

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

public class PrimeLandingPage extends BasePage {
    
    private static final Logger log = LogManager.getLogger();
    private String expectedPageTitle = "Amazon.ca: Amazon Prime";
    private String expectedUrlPart = "/prime/";
    
    @FindBy(xpath = "//h1[contains(text(), 'Video')]/following-sibling::a")
    private WebElement primeVideoLink;
    
    public PrimeLandingPage(WebDriver driver) {
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
    
    public String getPrimeVideoUrl() {
        scrollIntoView(primeVideoLink);
        return getElementAttributeValue(primeVideoLink, "href");
    }
    
    public String getExpectedPageTitle() {
        return expectedPageTitle;
    }
    
}
