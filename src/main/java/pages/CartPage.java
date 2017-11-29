package pages;

import java.util.List;
import java.util.Collections;
import static utils.DriverFactory.getDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Chris Wade
 */

public class CartPage extends BasePage {
    
    private static final Logger log = LogManager.getLogger();
    private String expectedPageTitle = "Amazon.ca Shopping Cart";
    private String expectedUrlPart = "/gp/cart/view.html";
    
    @FindBy(css = "input[name='proceedToCheckout']")
    private WebElement proceedToCheckoutBtn;
    
    @FindBy(css = "a[class*='sc-product-link'] span")
    private List<WebElement> cartContentsList;
    
    @FindBy(css = "input[type=submit][value='Delete']")
    private List<WebElement> deleteBtnsList;
    
    @FindBy(css = "div[data-action='delete'][style=''] span a")
    private WebElement deletedProductLink;
    
    @FindBy(css = "select[name='quantity']")
    private List<WebElement> quantityBtnsList;
    
    public CartPage(WebDriver driver) {
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
    
    public CartPage deleteFromCart(String product) {
        clickListButton(deleteBtnsList, "aria-label", product);
        waitUntilElementInnerTextContains(deletedProductLink, product);
        return this;
    }
    
    public CartPage updateQuantity(String product, String quantity) {
        int index = getCartContents().indexOf(product);
        WebElement quantityBtn = getElementAtIndex(quantityBtnsList, index);
        selectDropdownOption(quantityBtn, quantity);
        waitUntilRefreshedElementIsVisible(quantityBtn);
        return this;
    }
    
    public String getSelectedQuantity(String product) {
        int index = getCartContents().indexOf(product);
        WebElement quantityBtn = getElementAtIndex(quantityBtnsList, index);
        return getSelectedDropdownOptionValue(quantityBtn);
    }
    
    public List<String> getCartContents() {
        
        if (elementsAreDisplayed(cartContentsList)) {
            return getElementsInnerText(cartContentsList);
        }
        else {
            List<String> emptyList = Collections.emptyList();
            return emptyList;
        }
    }
    
    public boolean cartContains(String product) {
        return getCartContents().contains(product);
    }
    
    public String getExpectedPageTitle() {
        return expectedPageTitle;
    }
    
}
