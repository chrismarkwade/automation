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

public class SignInPage extends BasePage {
    
    private static final Logger log = LogManager.getLogger();
    private String expectedPageTitle = "Amazon Sign In";
    private String expectedUrlPart = "/ap/signin";
    
    @FindBy(css = "a[href='/ref=ap_frn_logo']")
    private WebElement logoLink;
    
    @FindBy(id = "ap_email")
    private WebElement emailTextBox;
    
    @FindBy(id = "ap_password")
    private WebElement passwordTextBox;
    
    @FindBy(id = "signInSubmit")
    private WebElement signInBtn;
    
    @FindBy(css = "div[id='auth-password-missing-alert'] div[class='a-alert-content']")
    private WebElement enterPasswordErrorMsg;
    
    public SignInPage(WebDriver driver) {
        super(driver);
        
        if (!pageUrlContains(expectedUrlPart)) {
            log.error(getPageLoadError());
            log.error(getCurrentPageTitle());
            throw new IllegalStateException(getPageLoadError());
        }
    }
    
    private SignInPage typeEmail(String email) {
        type(emailTextBox, email);
        return this;
    }
    
    private SignInPage typePassword(String password) {
        type(passwordTextBox, password);
        return this;
    }
    
    private <T extends BasePage> T submitSignIn() {
        click(signInBtn);
        
        if((pageUrlContains("/ref=gw_sgn_ib/")) || (pageUrlContains("ref_=nav_signin&"))) {
            return cast(pageFactoryObject(getDriver(), SignedInHomePage.class));
        }
        else if(pageUrlContains("/s/")) {
            return cast(pageFactoryObject(getDriver(), SearchResultsPage.class));
        }
        else if(pageUrlContains("/dp/")) {
            return cast(pageFactoryObject(getDriver(), ProductPage.class));
        }
        else if(pageUrlContains("/spc/")) {
            return cast(pageFactoryObject(getDriver(), PlaceOrderPage.class));
        }
        else {
            return null;
        }
    }
    
    private SignInPage submitSignInExpectingError() {
        click(signInBtn);
        return this;
    }
    
    public HomePage goToHomePage() {
        waitAndClick(logoLink);
        waitUntilUrlContains("logo");
        return pageFactoryObject(getDriver(), HomePage.class);
    }
    
    public <T extends BasePage> T signIn(String email, String password) {
        typeEmail(email);
        typePassword(password);
        return submitSignIn();
    }
    
    public SignInPage signInExpectingError(String email) {
        typeEmail(email);
        return submitSignInExpectingError();  
    }
    
    public String getEnterPasswordErrorMsg() {
        return getElementInnerText(enterPasswordErrorMsg);
    }
    
    public String getExpectedEnterPasswordErrorMsg() {
        return "Enter your password";
    }
    
    public String getExpectedPageTitle() {
        return expectedPageTitle;
    }
    
}
