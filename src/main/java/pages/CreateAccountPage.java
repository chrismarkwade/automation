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

public class CreateAccountPage extends BasePage {
    
    private static final Logger log = LogManager.getLogger();
    private String expectedPageTitle = "Amazon Registration";
    private String expectedUrlPart = "/ap/register";
    
    @FindBy(css = "a[href='/ref=ap_frn_logo']")
    private WebElement logoLink;
    
    @FindBy(id = "ap_customer_name")
    private WebElement yourNameTextBox;
    
    @FindBy(id = "ap_email")
    private WebElement emailTextBox;
    
    @FindBy(id = "ap_password")
    private WebElement passwordTextBox;
    
    @FindBy(id = "ap_password_check")
    private WebElement passwordAgainTextBox;
    
    @FindBy(id = "continue")
    private WebElement createAccountBtn;
    
    public CreateAccountPage(WebDriver driver) {
        super(driver);
        
        if (!pageUrlContains(expectedUrlPart)) {
            log.error(getPageLoadError());
            log.error(getCurrentPageTitle());
            throw new IllegalStateException(getPageLoadError());
        }
    }
    
    private CreateAccountPage typeYourName(String yourName) {
        type(yourNameTextBox, yourName);
        return this;
    }
    
    private CreateAccountPage typeEmail(String email) {
        type(emailTextBox, email);
        return this;
    }
    
    private CreateAccountPage typePassword(String password) {
        type(passwordTextBox, password);
        return this;
    }
    
    private CreateAccountPage typePasswordAgain(String password) {
        type(passwordAgainTextBox, password);
        return this;
    }
    
    private CreateAccountPage submitCreateAccountExpectingError() {
        click(createAccountBtn);
        return this;
    }
    
    public HomePage goToHomePage() {
        waitAndClick(logoLink);
        waitUntilUrlContains("logo");
        return pageFactoryObject(getDriver(), HomePage.class);
    }
    
    public CreateAccountPage createAccountExpectingError(String yourName, String email, String password) {
        typeYourName(yourName);
        typeEmail(email);
        typePassword(password);
        typePasswordAgain("passwordsDoNotMatch");
        return submitCreateAccountExpectingError();
    }
     
    public String getExpectedPageTitle() {
        return expectedPageTitle;
    }
    
}
