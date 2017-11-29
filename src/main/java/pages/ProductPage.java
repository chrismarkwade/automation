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

public class ProductPage extends BasePage {
    
    private static final Logger log = LogManager.getLogger();
    private String expectedUrlPart = "p/";
    
    @FindBy(css = "input[id='add-to-cart-button']")
    private WebElement addToCartBtn;
    
    @FindBy(css = "select[id='quantity']")
    private WebElement quantityDropdownBox;
    
    @FindBy(css = "span[id*='Title']")
    private WebElement productTitle;
    
    @FindBy(css = "span[class*='author'] a[href^='/s/']")
    private WebElement authorLink;
    
    @FindBy(xpath = "//span[text()='Format: ']/following-sibling::span")
    private WebElement videoFormat;
    
    @FindBy(css = "span[id*='priceblock'], span[class*='offer-price']")
    private WebElement productOfferPrice;
    
    public ProductPage(WebDriver driver) {
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
    
    public AddedToCartPage addToCart() {
        waitAndClick(addToCartBtn);
        waitUntilTitleContains("Cart");
        return pageFactoryObject(getDriver(), AddedToCartPage.class);
    }
    
    public ProductPage selectQuantity(String quantity) {
        click(quantityDropdownBox);
        selectDropdownOption(quantityDropdownBox, quantity);
        waitUntilRefreshedElementIsVisible(quantityDropdownBox);
        return this;
    }
    
    public SearchResultsPage goToSearchResultsPageForAuthor() {
        waitAndClick(authorLink);
        return pageFactoryObject(getDriver(), SearchResultsPage.class);
    }
    
    public String getProductTitle() {
        return getElementInnerText(productTitle);
    }
    
    public String getAuthor() {
        return getElementInnerText(authorLink);
    }
    
    public String getVideoFormat() {
        return getElementInnerText(videoFormat);
    }
    
    public double getProductOfferPrice() {
        String s = getElementInnerText(productOfferPrice).substring(5);
        return Double.parseDouble(s);
    }
    
    public String getAuthorLinkCssValue(String cssProperty) {
        return getElementCssValue(authorLink, cssProperty);
    }
    
}
