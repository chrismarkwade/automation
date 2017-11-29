package pages;

import static utils.DriverFactory.getDriver;
import static utils.ExecuteJavaScript.scrollIntoView;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Chris Wade
 */

public class PageFooter extends BasePage {
    
    @FindBy(css = "a[href='/ref=footer_logo']")
    private WebElement footerLogoLink;
    
    @FindBy(css = "a[href='/gp/prime/ref=footer_prime']")
    private WebElement amazonPrimeLink;
    
    public PageFooter(WebDriver driver) {
        super(driver);
    }
    
    public HomePage goToHomePage() {
        scrollIntoView(footerLogoLink);
        click(footerLogoLink);
        waitUntilUrlContains("footer");
        return pageFactoryObject(getDriver(), HomePage.class);
    }
    
    public PrimeLandingPage goToPrimeLandingPage() { 
        scrollIntoView(amazonPrimeLink);
        click(amazonPrimeLink);
        waitUntilTitleContains("Prime");
        return pageFactoryObject(getDriver(), PrimeLandingPage.class);  
    }
    
}
