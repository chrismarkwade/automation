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

public class SignedInHomePage extends BasePage {
    
    private static final Logger log = LogManager.getLogger();
    private String expectedTitleText = "Amazon.ca: Online shopping in Canada";
    
    @FindBy(css = "div[class*='first-carousel'] span[class='feed-scrollbar']")
    private WebElement firstCarouselScrollbar;
    
    @FindBy(css = "div[class*='first-carousel'] span[class='feed-scrollbar-thumb']")
    private WebElement firstCarouselScrollbarThumb;
    
    @FindBy(css = "div[class*='first-carousel'] ul li a")
    private List<WebElement> firstCarouselProductImageLinks;
    
    public SignedInHomePage(WebDriver driver) {
        super(driver);
        
        if (!pageTitleContains(expectedTitleText)) {
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
    
    public void moveFirstCarouselScrollbarBy(int xOffset) {
        scrollIntoView(firstCarouselScrollbar);
        clickAndDrag(firstCarouselScrollbarThumb, xOffset, 0);
    }
    
    public ProductPage goToFirstCarouselProductPageAtIndex(int index) {
        WebElement productImageLink = getElementAtIndex(firstCarouselProductImageLinks, index);
        click(productImageLink);
        return pageFactoryObject(getDriver(), ProductPage.class);
    }
    
}
