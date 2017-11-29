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

public class PlaceOrderPage extends BasePage {
    
    private static final Logger log = LogManager.getLogger();
    private String expectedPageTitle = "Place Your Order - Amazon.ca Checkout";
    private String expectedUrlPart = "/gp/buy/spc/handlers/display.html";
    
    @FindBy(name = "placeYourOrder1")
    private WebElement placeYourOrderBtn;
    
    @FindBy(css = "input[type='radio'][title]")
    private List<WebElement> deliveryOptionRadioBtns;
    
    @FindBy(css = "input[type='radio'][name='shipsplit0']")
    private List<WebElement> shippingPreferenceRadioBtns;
    
    @FindBy(css = "table td[class*='a-text-left']")
    private List<WebElement> orderSummaryLines;
    
    @FindBy(xpath = "//td[contains(text(), 'FREE')]")
    private WebElement orderSummaryFreeShippingText;
    
    @FindBy(css = "span[data-promisetype='delivery']")
    private WebElement deliveryPromiseText;
    
    public PlaceOrderPage(WebDriver driver) {
        super(driver);
        
        if (!pageUrlContains(expectedUrlPart)) {
            log.error(getPageLoadError());
            log.error(getCurrentPageTitle());
            throw new IllegalStateException(getPageLoadError());
        }
    }
    
    public PlaceOrderPage selectFreeShipping() {
        clickRadioButton(deliveryOptionRadioBtns, "econ-ca");
        waitUntilElementIsVisible(orderSummaryFreeShippingText);
        return this;
    }
    
    public PlaceOrderPage selectStandardShipping() {
        clickRadioButton(deliveryOptionRadioBtns, "standard-ca");
        waitUntilAllElementsAreVisible(shippingPreferenceRadioBtns);
        return this;
    }
    
    public PlaceOrderPage selectTwoDayShipping() {
        clickRadioButton(deliveryOptionRadioBtns, "second-ca");
        waitUntilElementInnerTextContains(deliveryPromiseText, "Guaranteed");
        return this;
    }
    
    public PlaceOrderPage selectOneDayDelivery() {
        clickRadioButton(deliveryOptionRadioBtns, "same-ca");
        waitUntilElementInnerTextContains(deliveryPromiseText, "Guaranteed");
        return this;
    }
    
    public ThankYouPage placeOrder() {
        waitAndClick(placeYourOrderBtn);
        waitUntilUrlContains("thankyou");
        return pageFactoryObject(getDriver(), ThankYouPage.class);  
    }
    
    public List<String> getDeliveryOptions() {                            
        return getElementsAttributeValue(deliveryOptionRadioBtns, "title");
    }
    
    public String getDeliveryPromiseText() {
        return getElementInnerText(deliveryPromiseText);
    }
    
    public List<String> getOrderSummaryLines() {            
        return getElementsInnerText(orderSummaryLines);
    }
    
    public String getExpectedPageTitle() {
        return expectedPageTitle;
    }
    
}
