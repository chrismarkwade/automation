package pages;

import static utils.DriverFactory.getDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Chris Wade
 */

public class AddedToCartPage extends BasePage {
    
    private static final Logger log = LogManager.getLogger();
    private String expectedPageTitle = "Amazon.ca Shopping Cart";
    private String expectedUrlPart = "/gp/huc/view.html";
    
    @FindBy(id = "hlb-view-cart-announce")
    private WebElement cartBtn;
    
    @FindBy(id = "hlb-ptc-btn-native")
    private WebElement proceedToCheckoutBtn;
    
    public AddedToCartPage(WebDriver driver) {
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
    
    public CartPage goToCart() {
        waitAndClick(cartBtn);
        waitUntilUrlContains("/cart/");
        return pageFactoryObject(getDriver(), CartPage.class);
    }
    
    public <T extends BasePage> T proceedToCheckout() {
        
        if (header().getHelloGreeting().contains("Sign in")) {
            waitAndClick(proceedToCheckoutBtn);
            waitUntilTitleContains("Sign In");
            return cast(pageFactoryObject(getDriver(), SignInPage.class));
        }
        else {
            waitAndClick(proceedToCheckoutBtn);
            waitUntilTitleContains("Place Your Order");
            return cast(pageFactoryObject(getDriver(), PlaceOrderPage.class));
        }
    }
    
    public String getExpectedPageTitle() {
        return expectedPageTitle;
    }
    
}
