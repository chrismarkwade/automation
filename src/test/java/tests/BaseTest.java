package tests;

import pages.*;
import listeners.*;
import static utils.DriverFactory.*;
import static utils.PropertyUtil.*;
import org.testng.annotations.*;

/**
 * @author Chris Wade
 */

@Listeners({SuiteListener.class, TestListener.class, ClassListener.class})
public abstract class BaseTest {
    
    HomePage homePage;
    PageHeader pageHeader;
    PageFooter pageFooter;
    SignedInHomePage signedInHomePage;
    SearchResultsPage searchResultsPage;
    ProductPage productPage;
    DepartmentPage departmentPage;
    StoreDirectoryPage storeDirectoryPage;
    PrimeLandingPage primeLandingPage;
    AddedToCartPage addedToCartPage;
    CartPage cartPage;
    CreateAccountPage createAccountPage;
    SignInPage signInPage;
    PlaceOrderPage placeOrderPage;
    ThankYouPage thankYouPage;
    
    String firstName = getAccountProperty("name").substring(0, 4);
    String email = getAccountProperty("email");
    String password = getAccountProperty("password");
    
    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        setDriver(createDriver(browser));
        getDriver().manage().window().maximize();
    }
    
    @AfterMethod
    public void tearDown() {
        quitDriver();
    }
    
}
