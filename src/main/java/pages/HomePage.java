package pages;

import static utils.DriverFactory.getDriver;
import static utils.PropertyUtil.getUrlProperty;
import static utils.ExecuteJavaScript.scrollIntoView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Chris Wade
 */

public class HomePage extends BasePage {
    
    private static final Logger log = LogManager.getLogger();
    private static String url = getUrlProperty("prodUrl");
    private String expectedTitleText = "Amazon.ca: Online shopping in Canada";
    
    @FindBy(id = "a-autoid-0-announce")
    private WebElement signInSecurelyBtn;
    
    @FindBy(css = "div[id='gw-sign-in-bottom'] a")
    private WebElement startHereLink; 
    
    public HomePage(WebDriver driver) {
        super(driver);
        
        if (!pageTitleContains(expectedTitleText)) {
            log.error(getPageLoadError());
            log.error(getCurrentPageTitle());
            throw new IllegalStateException(getPageLoadError());
        }
    }
    
    public static HomePage open() {
        load(url);
        return pageFactoryObject(getDriver(), HomePage.class);
    }    
    
    public PageHeader header() {
        return pageFactoryObject(getDriver(), PageHeader.class);
    }
    
    public PageFooter footer() {
        return pageFactoryObject(getDriver(), PageFooter.class);
    }
    
    public SignInPage goToSignInPage() {
        scrollIntoView(signInSecurelyBtn);
        click(signInSecurelyBtn);
        waitUntilTitleContains("Sign In");
        return pageFactoryObject(getDriver(), SignInPage.class);
    }
    
    public CreateAccountPage goToCreateAccountPage() {
        scrollIntoView(startHereLink);
        click(startHereLink);
        waitUntilTitleContains("Registration");
        return pageFactoryObject(getDriver(), CreateAccountPage.class);
    }
    
    public String getStartHereLinkCssValue(String cssProperty) {
        scrollIntoView(startHereLink);
        return getElementCssValue(startHereLink, cssProperty);
    }
    
}
