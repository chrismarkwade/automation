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

public class ThankYouPage extends BasePage {
    
    private static final Logger log = LogManager.getLogger();
    private String expectedPageTitle = "Amazon.ca Thanks You";
    private String expectedUrlPart = "/thankyou/";
    
    @FindBy(css = "h2[class='a-color-success']")
    private WebElement thankYouMsg;
    
    @FindBy(css = "span[id*='order-number']")
    private List<WebElement> orderNumber;
    
    @FindBy(css = "a[href='/ref=co_typ']")
    private WebElement continueShoppingBtn;
    
    public ThankYouPage(WebDriver driver) {
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
    
    public SignedInHomePage goToSignedInHomePage() {
        scrollIntoView(continueShoppingBtn);
        click(continueShoppingBtn);
        waitUntilUrlContains("typ");
        return pageFactoryObject(getDriver(), SignedInHomePage.class);
    }
    
    public String getThankYouMsg() {
        return getElementInnerText(thankYouMsg);
    }
    
    public String getExpectedThankYouMsg() {
        return "Thank you, your order has been placed.";
    }
    
    public boolean orderNumberIsDisplayed() {
        return elementIsDisplayed(orderNumber);
    }
    
    public String getExpectedPageTitle() {
        return expectedPageTitle;
    }
    
}
